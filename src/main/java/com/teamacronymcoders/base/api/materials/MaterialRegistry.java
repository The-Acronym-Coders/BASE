package com.teamacronymcoders.base.api.materials;

import com.teamacronymcoders.base.config.ConfigMaterials;
import com.google.common.collect.HashBiMap;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.Map;


public class MaterialRegistry {

    private static HashBiMap<Integer, MaterialType> MATERIALS = HashBiMap.create();

    public static boolean registerMaterial(int ID, MaterialType materialType) {
        if (getMaterials().containsKey(materialType.getName())) return false;
        getMaterials().put(ID, materialType);
        return true;
    }

    public static boolean registerNativeMaterial(int ID, MaterialType materialType) {
        if (!ConfigMaterials.materialMap.get(materialType)) {
            return false;
        }
        if (getMaterials().containsKey(materialType.getName())) return false;
        getMaterials().put(ID, materialType);
        return true;
    }

    public static boolean unregisterMaterial(String key) {
        MutablePair<String, Integer> pair = new MutablePair<>(key, getIDFromName(key));
        if (!getMaterials().keySet().contains(pair)) {
            return false;
        }
        getMaterials().remove(pair);
        return true;
    }

    public static boolean isRegistered(String key) {
        return getMaterials().containsKey(key);
    }

    public static HashBiMap<Integer, MaterialType> getMaterials() {
        return MATERIALS;
    }

    public static MaterialType getFromID(int id) {
        return getMaterials().get(id);
//        for (Map.Entry<Integer, MaterialType> ent : getMaterials().entrySet()) {
//            if (ent.getKey() == id) {
//                return ent.getValue();
//            }
//        }
//        return null;
    }

    public static MaterialType getFromName(String name) {
        for (Map.Entry<Integer, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getValue().getName().equalsIgnoreCase(name)) {
                return ent.getValue();
            }
        }
        return null;
    }

    public static int getIDFromName(String name) {
        for (Map.Entry<Integer, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getValue().getName().equalsIgnoreCase(name)) {
                return ent.getKey();
            }
        }
        return -1;
    }

    public static int getIDFromMaterial(MaterialType mat) {
        return getMaterials().inverse().get(mat);
//        for (Map.Entry<Integer, MaterialType> ent : getMaterials().entrySet()) {
//            if (ent.getValue().equals(mat)) {
//                return ent.getKey();
//            }
//        }
//        return -1;
    }

    public static LinkedList<Integer> getIDList() {
        return new LinkedList<>(getMaterials().keySet());
//        LinkedList<Integer> retList = new LinkedList();
//        getMaterials().keySet().forEach(ent -> {
//            retList.add(ent.getRight());
//        });
//        return retList;
    }

}
