package net.dark_roleplay.library.experimental.blocks.behaviors;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IUpdateTickBehavior extends IBlockBehavior{

	public void execute (World world, BlockPos pos, IBlockState state, Random rand);

	public static class Multiple extends MultiBehavior<IUpdateTickBehavior> implements IUpdateTickBehavior{

		@Override
		public void execute (World world, BlockPos pos, IBlockState state, Random rand) {
			for(IUpdateTickBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, state, rand);
			}
		}
	}
}
