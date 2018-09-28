package net.dark_roleplay.library.experimental.blocks.behaviors;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRandomTickBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, IBlockState state, Random random);

}
