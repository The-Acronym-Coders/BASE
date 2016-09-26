package com.acronym.base.api.materials;

import com.acronym.base.config.ConfigMaterials;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


public class MaterialRegistry {

    private static Map<MutablePair<String, Integer>, MaterialType> MATERIALS = new LinkedHashMap<>();

    public static boolean registerMaterial(int ID, MaterialType materialType) {
        if (getMaterials().containsKey(materialType.getName())) return false;
        getMaterials().put(new MutablePair<>(materialType.getName(), ID), materialType);
        return true;
    }

    public static boolean registerNativeMaterial(int ID, MaterialType materialType) {
        if(!ConfigMaterials.materialMap.get(materialType)){
            return false;
        }
        if (getMaterials().containsKey(materialType.getName())) return false;
        getMaterials().put(new MutablePair<>(materialType.getName(), ID), materialType);
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

    public static Map<MutablePair<String, Integer>, MaterialType> getMaterials() {
        return MATERIALS;
    }

    public static MaterialType getFromID(int id) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getRight() == id) {
                return ent.getValue();
            }
        }
        return null;
    }

    public static MaterialType getFromName(String name) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getLeft().equalsIgnoreCase(name)) {
                return ent.getValue();
            }
        }
        return null;
    }

    public static int getIDFromName(String name) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getLeft().equalsIgnoreCase(name)) {
                return ent.getKey().getRight();
            }
        }
        return -1;
    }

    public static int getIDFromMaterial(MaterialType mat) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if(ent.getValue().equals(mat)){
                return ent.getKey().getValue();
            }
        }
        return -1;
    }

    public static String getNameFromID(int id) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getRight() == id) {
                return ent.getKey().getLeft();
            }
        }
        return null;
    }

    public static LinkedList<Integer> getIDList(){
        LinkedList<Integer> retList = new LinkedList();
        getMaterials().keySet().forEach(ent ->{
            retList.add(ent.getRight());
        });
        return retList;
    }

}
