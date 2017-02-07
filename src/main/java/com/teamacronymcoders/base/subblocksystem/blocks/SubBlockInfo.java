package com.teamacronymcoders.base.subblocksystem.blocks;

import java.util.HashMap;
import java.util.Map;

public class SubBlockInfo {
    private Map<Integer, Map<Integer, String>> savedSubBlockNames;

    public SubBlockInfo() {
        this.savedSubBlockNames = new HashMap<>();
    }

    public Map<Integer, Map<Integer, String>> getSavedSubBlockNames() {
        return savedSubBlockNames;
    }

    public void setSavedSubBlocksName(Map<Integer, Map<Integer, String>> savedSubBlocks) {
        this.savedSubBlockNames = savedSubBlocks;
    }

    public void setSavedSubBlocks(Map<Integer, Map<Integer, ISubBlock>> savedSubBlocks) {
        Map<Integer, Map<Integer, String>> savedNames = new HashMap<>();
        savedSubBlocks.forEach((blockNumber, subBlocksForBlock) -> {
            Map<Integer, String> subBlockNames = new HashMap<>();
            subBlocksForBlock.forEach((subBlockNumber, subBlock) -> subBlockNames.put(subBlockNumber, subBlock.getName()));
            savedNames.put(blockNumber, subBlockNames);
        });
        this.setSavedSubBlocksName(savedNames);
    }
}
