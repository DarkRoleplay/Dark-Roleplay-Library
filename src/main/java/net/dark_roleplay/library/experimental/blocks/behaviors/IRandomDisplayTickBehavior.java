package net.dark_roleplay.library.experimental.blocks.behaviors;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRandomDisplayTickBehavior extends IBlockBehavior{

	public void execute(IBlockState state, World world, BlockPos pos, Random rand);

	public static class Multiple extends MultiBehavior<IRandomDisplayTickBehavior> implements IRandomDisplayTickBehavior{

		@Override
		public void execute(IBlockState state, World world, BlockPos pos, Random rand) {
			for(IRandomDisplayTickBehavior behavior : this.behaviors) {
				behavior.execute(state, world, pos, rand);
			}
		}
	}
}
