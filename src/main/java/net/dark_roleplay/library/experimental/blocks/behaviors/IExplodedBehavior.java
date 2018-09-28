package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public interface IExplodedBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, Explosion explosion);

	public static class Multiple extends MultiBehavior<IExplodedBehavior> implements IExplodedBehavior{

		@Override
		public void execute(World world, BlockPos pos, Explosion explosion) {
			for(IExplodedBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, explosion);
			}
		}
	}
}
