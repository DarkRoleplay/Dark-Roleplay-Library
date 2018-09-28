package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBreakingBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, IBlockState state);

	public static class Multiple extends MultiBehavior<IBreakingBehavior> implements IBreakingBehavior{

		@Override
		public void execute(World world, BlockPos pos, IBlockState state) {
			for(IBreakingBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, state);
			}
		}
	}
}
