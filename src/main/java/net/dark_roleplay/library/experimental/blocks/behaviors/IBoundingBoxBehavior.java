package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IBoundingBoxBehavior extends IBlockBehavior{

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos);

	public static class SimpleImpl implements IBoundingBoxBehavior{

		AxisAlignedBB aabb;

		public SimpleImpl(AxisAlignedBB aabb) {
			this.aabb = aabb;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return this.aabb;
		}
	}
}
