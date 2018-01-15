package com.tyhone.arcanacraft.common.util;

import java.util.List;

import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStack {

	
	private String ore;
	private int count;

	public OreStack(String ore, int count){
		this.ore = ore;
		this.count = count;
	}

	public int getCount(){
		return this.count;
	}
	
	public String getOre(){
		return this.ore;
	}
	
	public static boolean isItemStackFromOre(OreStack oreStack, ItemStack stack){
		if(oreStack.getCount()==stack.getCount()){
			List<ItemStack> ores = OreDictionaryHandler.getOreDictionaryEntries(oreStack.getOre());
			for(ItemStack ore : ores){
				if(ore.getMetadata() == OreDictionary.WILDCARD_VALUE){
					if(ItemStackUtil.simpleAreStacksEqual(ore, stack, true)){
						return true;
					}
				}
				else if(ItemStackUtil.simpleAreStacksEqual(ore, stack)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static ItemStack getOreDictionaryEntryForOreStack(OreStack oreStack){
		
		return OreDictionaryHandler.getOreDictionaryEntry(oreStack.getOre());
	}
	
	public static List<ItemStack> getOreDictionaryEntriesForOreStack(OreStack oreStack){
		
		List<ItemStack> list = OreDictionaryHandler.getOreDictionaryEntries(oreStack.getOre());
		if(list.size()>0){
			return list;
		}
		return null;
	}
}