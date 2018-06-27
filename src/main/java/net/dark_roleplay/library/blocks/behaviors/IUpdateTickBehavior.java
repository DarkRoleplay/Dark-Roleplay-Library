package net.dark_roleplay.library.blocks.behaviors;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created: 31.05.2018
 * Last edit: 31.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: Experimental
 */
public interface IUpdateTickBehavior {
	
	public void execute(World world, BlockPos pos, IBlockState state, Random rand);
	
}