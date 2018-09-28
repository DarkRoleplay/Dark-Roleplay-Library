package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlacementBehavior extends IBlockBehavior{

	public boolean execute(World world, BlockPos pos, EnumFacing side);

	public static class Multiple extends MultiBehavior<IPlacementBehavior> implements IPlacementBehavior{

		protected boolean shouldUseAnd = true;

		public Multiple(boolean shouldUseAnd) {
			this.shouldUseAnd = shouldUseAnd;
		}

		@Override
		public boolean execute(World world, BlockPos pos, EnumFacing side) {
			boolean canPlace = shouldUseAnd;
			for(IPlacementBehavior behavior : this.behaviors) {
				if(shouldUseAnd)
					canPlace &= behavior.execute(world, pos, side);
				else
					canPlace |= behavior.execute(world, pos, side);
			}

			return canPlace;
		}
	}
}
