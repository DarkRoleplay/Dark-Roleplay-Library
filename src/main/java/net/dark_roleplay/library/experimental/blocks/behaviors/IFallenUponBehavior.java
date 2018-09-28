package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IFallenUponBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, Entity entity, float fallDistance);

	public static class Multiple extends MultiBehavior<IFallenUponBehavior> implements IFallenUponBehavior{

		@Override
		public void execute(World world, BlockPos pos, Entity entity, float fallDistance) {
			for(IFallenUponBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, entity, fallDistance);
			}
		}
	}
}
