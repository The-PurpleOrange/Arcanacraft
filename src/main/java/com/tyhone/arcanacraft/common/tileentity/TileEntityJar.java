package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.api.registries.TinktureType;
import com.tyhone.arcanacraft.api.util.TinktureStackUtil;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityJar extends ModTileEntityBase {
	
	private final String TAG_TYPE = "type";
	private final String TAG_AMOUNT = "amount";
	
	private final int MAX_FLUID = 32;
	private TinktureStack tinktureStack = TinktureStack.EMPTY;
	
	public int getTinktureAmount(){
		return tinktureStack.getAmount();
	}

	public TinktureStack getTinktureStack(){
		return tinktureStack;
	}
	
	public TinktureType getTinktureType(){
		return tinktureStack.getTinktureType();
	}
	
	public int getMaxFluid(){
		return MAX_FLUID;
	}
	
	public void setTinktureType(TinktureType type){
		tinktureStack.setTinktureType(type);
		markForClean();
	}
	
	public boolean removeTinktureAmount(int amount){
		if((tinktureStack.getAmount() - amount) < 0){
			return false;
		}
		else{
			tinktureStack.modifyAmount(-amount);
			markForClean();
			return true;
		}
	}
	
	public int removeTinktureAmountPartial(int amount){
		if((tinktureStack.getAmount() - amount) < 0){
			int remainder = ((tinktureStack.getAmount() - amount) * -1);
			tinktureStack.setAmount(0);
			return remainder;
		}
		else{
			tinktureStack.modifyAmount(-amount);
			markForClean();
			return 0;
		}
	}
	
	public boolean addRinktureAmount(TinktureType type, int amount){
		if(tinktureStack.getTinktureType() == ModTinktureTypes.EMPTY || tinktureStack.getTinktureType() == type){
			if((tinktureStack.getAmount() + amount) > MAX_FLUID){
				Arcanacraft.logger.info("Failed adding tinkture (Not enough space): " + type.getTinktureName() + ", Current Amount: "+ this.getTinktureAmount());
				return false;
			}
			else{
				if(tinktureStack.getTinktureType() == ModTinktureTypes.EMPTY){
					tinktureStack.setTinktureType(type);
				}
				tinktureStack.modifyAmount(amount);
				Arcanacraft.logger.info("Added tinkture: " + type.getTinktureName() + ", Current Amount: "+ this.getTinktureAmount());
				markForClean();
				return true;
			}
		}
		Arcanacraft.logger.info("Failed adding tinkture (Iincorrect type):  " + type.getTinktureName() + ", Current Amount: "+ this.getTinktureAmount());
		return false;
	}
	
	public int addTinktureAmountPartial(TinktureType type, int amount){
		if(tinktureStack.getTinktureType() == ModTinktureTypes.EMPTY || tinktureStack.getTinktureType() == type){
			if(tinktureStack.getAmount() >= MAX_FLUID){
				return amount;
			}
			else{
				markForClean();
				if((tinktureStack.getAmount() + amount) >= MAX_FLUID){
					int amountLeft = (tinktureStack.getAmount() + amount) - MAX_FLUID;
					tinktureStack.setAmount(MAX_FLUID);
					return amountLeft;
				}
				else{
					tinktureStack.modifyAmount(amount);
					return 0;
				}
			}
		}
		return amount;
	}

	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        
    	
        if(compound.hasKey(TAG_TYPE)){
        	Arcanacraft.logger.info("Compoud load: " + compound);
        	TinktureType type = TinktureStackUtil.getTinktureTypeFromString(compound.getString(TAG_TYPE));
        	int amount = compound.getInteger(TAG_AMOUNT);
            tinktureStack = new TinktureStack(type, amount);
        }
        else{
        	Arcanacraft.logger.info("Compoud failed to load");
        }
        
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        if(!tinktureStack.isEmpty()){
        	compound.setString(TAG_TYPE, tinktureStack.getTinktureType().getTinktureName());
        	compound.setInteger(TAG_AMOUNT, tinktureStack.getAmount());

        	Arcanacraft.logger.info("Compoud save: " + compound);
        }
        else{
        	Arcanacraft.logger.info("Compoud did not save (empty)");
        }
        return compound;
    }
}
