package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface INeighborChangedBehavior extends IBlockBehavior{

	public void execute(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos);

	public static class Multiple extends MultiBehavior<INeighborChangedBehavior> implements INeighborChangedBehavior{

		@Override
		public void execute(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
			for(INeighborChangedBehavior behavior : this.behaviors) {
				behavior.execute(state, world, pos, block, fromPos);
			}
		}
	}
}
