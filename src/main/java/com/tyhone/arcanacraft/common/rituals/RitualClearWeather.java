package com.tyhone.arcanacraft.common.rituals;

import java.util.Random;

import com.tyhone.arcanacraft.api.ritual.RitualBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualClearWeather extends RitualBase{

	public final Random rand = new Random();
	
	
    public RitualClearWeather(String ritualName) {
		super(ritualName);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos Pos) {
		if(!world.isRemote){
			world.getWorldInfo().setRaining(false);
			world.getWorldInfo().setThundering(false);
			world.getWorldInfo().setRainTime(rand.nextInt(168000) + 12000);
			world.getWorldInfo().setThunderTime(rand.nextInt(168000) + 12000);
		}
		return true;
	}

}
