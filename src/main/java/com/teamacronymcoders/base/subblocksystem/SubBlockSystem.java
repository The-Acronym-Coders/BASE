package com.teamacronymcoders.base.subblocksystem;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.savesystem.SaveLoader;
import com.teamacronymcoders.base.subblocksystem.blocks.BlockSubBlockHolder;
import com.teamacronymcoders.base.subblocksystem.blocks.ISubBlock;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SubBlockSystem {
    private static Map<String, ISubBlock> SUB_BLOCKS = new HashMap<>();

    public static void registerSubBlock(ISubBlock iSubBlock) {
        SUB_BLOCKS.put(iSubBlock.getName(), iSubBlock);
    }

    public static void createBlocks() {
        Map<String, ISubBlock> subBlocksToUse = new HashMap<>();
        subBlocksToUse.putAll(SUB_BLOCKS);
        SubBlockInfo subBlockInfo = SaveLoader.getSavedObject("saved_sub_blocks", SubBlockInfo.class);
        Map<Integer, Map<Integer, String>> listOfBlockNames = subBlockInfo.getSavedSubBlockNames();
        Map<Integer, Map<Integer, ISubBlock>> listOfSubBlocks = new HashMap<>();
        if(listOfBlockNames.size() > 0) {
            for(Entry<Integer, Map<Integer, String>> blockNames : listOfBlockNames.entrySet()) {
                Map<Integer, ISubBlock> subBlocksForBlock = new HashMap<>();
                for(Entry<Integer, String> subBlockName : blockNames.getValue().entrySet()) {
                    ISubBlock subBlock = subBlocksToUse.remove(subBlockName.getValue());
                    if(subBlock == null) {
                        Base.instance.getLogger().error("Could not find sub-block: " + subBlockName.getValue());
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
        SaveLoader.saveObject("saved_sub_blocks", subBlockInfo);
        BlockRegistry blockRegistry = Base.instance.getRegistry(BlockRegistry.class, "BLOCK");
        listOfSubBlocks.forEach((id, subBlocks) -> blockRegistry.register(new BlockSubBlockHolder(id, subBlocks)));
    }
}
