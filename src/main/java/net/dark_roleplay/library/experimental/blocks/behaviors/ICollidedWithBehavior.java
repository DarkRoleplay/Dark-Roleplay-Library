package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICollidedWithBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, IBlockState state, Entity entity);

	public static class Multiple extends MultiBehavior<ICollidedWithBehavior> implements ICollidedWithBehavior{

		@Override
		public void execute(World world, BlockPos pos, IBlockState state, Entity entity) {
			for(ICollidedWithBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, state, entity);
			}
		}
	}
}
