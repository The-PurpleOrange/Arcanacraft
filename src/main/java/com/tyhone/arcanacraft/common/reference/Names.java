package com.tyhone.arcanacraft.common.reference;

import java.util.ArrayList;
import java.util.Arrays;

import com.tyhone.arcanacraft.common.items.enums.EnumTinktures;

public class Names {

	public static final class MetaItems{
		
		public static final ArrayList<String> CHALK = al("charcoal", "bone", "blood", "lapis", "gold", "magicite");
        public static final ArrayList<String> ALCHEMIC_DUST = al("mundane", "salient", "lavarice", "avaricle");
        public static final ArrayList<String> SOUL = al("fragment", "complete", "nether", "eldritch");
        public static final ArrayList<String> LENS = al("essence", "soul");
        public static final ArrayList<String> ESSENCE = al("iron", "gold", "magicite", "nether", "eldritch", "prosper", "greed");
        public static final ArrayList<String> SHARD = al("nether", "eldritch", "prosper", "greed");
        public static final ArrayList<String> INGOT = al("magicite", "blood_iron", "alcharium");
        public static final ArrayList<String> DUST = al("blood", "bone", "charcoal", "iron", "gold", "magicite");
        public static final ArrayList<String> CRYSTAL = al("blood", "gold", "iron", "magicite");
        public static final ArrayList<String> ITEM = al("bone_ash", "quartz_dust", "clean_flesh", "blood_drop", "star_shard", "inert_star");
        public static final ArrayList<String> STAR = al("eldritch", "virtuous");
     
	}
	
	private static ArrayList<String> al(String ... strings){
		return new ArrayList<String>(Arrays.asList(strings));
	}
	
}
