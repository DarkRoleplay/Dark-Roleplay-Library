package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IWalkedUponBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, Entity entity);

	public static class Multiple extends MultiBehavior<IWalkedUponBehavior> implements IWalkedUponBehavior{

		@Override
		public void execute(World world, BlockPos pos, Entity entity) {
			for(IWalkedUponBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, entity);
			}
		}
	}
}
