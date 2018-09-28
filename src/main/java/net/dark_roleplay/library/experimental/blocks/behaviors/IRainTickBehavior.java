package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRainTickBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos);

	public static class Multiple extends MultiBehavior<IRainTickBehavior> implements IRainTickBehavior{

		@Override
		public void execute(World world, BlockPos pos) {
			for(IRainTickBehavior behavior : this.behaviors) {
				behavior.execute(world, pos);
			}
		}
	}
}
