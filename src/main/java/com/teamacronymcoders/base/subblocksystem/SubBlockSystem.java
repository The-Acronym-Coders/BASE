package com.teamacronymcoders.base.subblocksystem;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.savesystem.SaveLoader;
import com.teamacronymcoders.base.subblocksystem.blocks.BlockSubBlockHolder;
import com.teamacronymcoders.base.subblocksystem.blocks.ISubBlock;
import com.teamacronymcoders.base.subblocksystem.blocks.MissingSubBlock;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SubBlockSystem {
    private IBaseMod mod;

    private Map<String, ISubBlock> subBlocks = Maps.newHashMap();
    public static final ISubBlock MISSING_SUB_BLOCK = new MissingSubBlock();

    public SubBlockSystem(IBaseMod mod) {
        this.mod = mod;
    }

    public void registerSubBlock(ISubBlock iSubBlock) {
        subBlocks.put(iSubBlock.getName(), iSubBlock);
    }

    public void createBlocks() {
        SubBlockInfo subBlockInfo = SaveLoader.getSavedObject("saved_sub_blocks_" + mod.getID(), SubBlockInfo.class);
        Map<Integer, Map<Integer, String>> listOfBlockNames = subBlockInfo.getSavedSubBlockNames();
        Map<Integer, Map<Integer, ISubBlock>> listOfSubBlocks = new HashMap<>();
        if (listOfBlockNames.size() > 0) {
            for (Entry<Integer, Map<Integer, String>> blockNames : listOfBlockNames.entrySet()) {
                Map<Integer, ISubBlock> subBlocksForBlock = new HashMap<>();
                for (Entry<Integer, String> subBlockName : blockNames.getValue().entrySet()) {
                    ISubBlock subBlock = subBlocks.remove(subBlockName.getValue());
                    if (subBlock == null) {
                        mod.getLogger().error("Could not find sub-block: " + subBlockName.getValue());
                    }
                    subBlocksForBlock.put(subBlockName.getKey(), subBlock);
                }
                listOfSubBlocks.put(blockNames.getKey(), subBlocksForBlock);
            }
        }
        int blockToCheck = 0;
        int blockMetaToCheck = 0;
        List<ISubBlock> remainingSubBlocks = new ArrayList<>(subBlocks.values());
        while (remainingSubBlocks.size() > 0) {
            listOfSubBlocks.computeIfAbsent(blockToCheck, value -> new HashMap<>());
            listOfSubBlocks.get(blockToCheck).computeIfAbsent(blockMetaToCheck, value -> remainingSubBlocks.remove(0));
            if (++blockMetaToCheck > 15) {
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
