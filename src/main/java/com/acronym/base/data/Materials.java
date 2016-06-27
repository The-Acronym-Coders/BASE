package com.acronym.base.data; 
 
import com.acronym.base.api.materials.Material; 
import com.acronym.base.api.materials.Resource; 
 
import java.util.Arrays; 
 
/** 
 * Created by Jared on 6/27/2016. 
 */ 
public class Materials { 
 
    public static final Material copper = new Material("Copper", 0xFF44FF, Arrays.asList(new Resource[]{new Resource("ingotCopper"), new Resource("dustCopper"), new Resource("oreCopper")})); 
    public static final Material tin = new Material("Tin", 0x44FF44, Arrays.asList(new Resource[]{new Resource("ingotTin"), new Resource("dustTin"), new Resource("oreTin")})); 
 
} 
