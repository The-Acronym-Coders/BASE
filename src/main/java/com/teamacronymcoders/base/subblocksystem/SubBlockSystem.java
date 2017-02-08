package com.teamacronymcoders.base.subblocksystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.savesystem.SaveLoader;
import com.teamacronymcoders.base.subblocksystem.blocks.BlockSubBlockHolder;
import com.teamacronymcoders.base.subblocksystem.blocks.ISubBlock;
import com.teamacronymcoders.base.subblocksystem.blocks.MissingSubBlock;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockInfo;
import com.teamacronymcoders.base.util.Platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SubBlockSystem {
    private static Map<String, Map<String, ISubBlock>> SUB_BLOCKS = new HashMap<>();
    public static final ISubBlock MISSING_SUB_BLOCK = new MissingSubBlock();
    public static void registerSubBlock(ISubBlock iSubBlock) {
        IBaseMod mod = Platform.getCurrentMod();
        if(mod != null) {
            SUB_BLOCKS.computeIfAbsent(mod.getID(), value -> new HashMap<>());
            SUB_BLOCKS.get(mod.getID()).put(iSubBlock.getName(), iSubBlock);
        }

    }

    public static void createBlocks(IBaseMod mod) {
        Map<String, ISubBlock> subBlocksToUse = new HashMap<>();
        subBlocksToUse.putAll(SUB_BLOCKS.get(mod.getID()));
        SubBlockInfo subBlockInfo = SaveLoader.getSavedObject("saved_sub_blocks_" + mod.getID(), SubBlockInfo.class);
        Map<Integer, Map<Integer, String>> listOfBlockNames = subBlockInfo.getSavedSubBlockNames();
        Map<Integer, Map<Integer, ISubBlock>> listOfSubBlocks = new HashMap<>();
        if(listOfBlockNames.size() > 0) {
            for(Entry<Integer, Map<Integer, String>> blockNames : listOfBlockNames.entrySet()) {
                Map<Integer, ISubBlock> subBlocksForBlock = new HashMap<>();
                for(Entry<Integer, String> subBlockName : blockNames.getValue().entrySet()) {
                    ISubBlock subBlock = subBlocksToUse.remove(subBlockName.getValue());
                    if(subBlock == null) {
                        mod.getLogger().error("Could not find sub-block: " + subBlockName.getValue());
                    }
                    subBlocksForBlock.put(subBlockName.getKey(), subBlock);
                }
                listOfSubBlocks.put(blockNames.getKey(), subBlocksForBlock);
            }
        }
        int blockToCheck = 0;
        int blockMetaToCheck = 0;
        List<ISubBlock> remainingSubBlocks = subBlocksToUse.values().stream().collect(Collectors.toList());
        while(remainingSubBlocks.size() > 0) {
            listOfSubBlocks.computeIfAbsent(blockToCheck, value -> new HashMap<>());
            listOfSubBlocks.get(blockToCheck).computeIfAbsent(blockMetaToCheck, value -> remainingSubBlocks.remove(0));
            if(++blockMetaToCheck > 15) {
                blockMetaToCheck = 0;
                blockToCheck++;
            }
        }
        subBlockInfo.setSavedSubBlocks(listOfSubBlocks);
        SaveLoader.saveObject("saved_sub_blocks_" + mod.getID(), subBlockInfo);
        BlockRegistry blockRegistry = mod.getRegistryHolder().getRegistry(BlockRegistry.class, "BLOCK");
        listOfSubBlocks.forEach((id, subBlocks) -> blockRegistry.register(new BlockSubBlockHolder(id, subBlocks)));
    }
}
