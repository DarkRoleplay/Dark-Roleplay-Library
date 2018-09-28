package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlacedBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack);

	public static class Multiple extends MultiBehavior<IPlacedBehavior> implements IPlacedBehavior{

		@Override
		public void execute(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
			for(IPlacedBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, state, placer, stack);
			}
		}
	}
}
