package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IHarvestedBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player);

	public static class Multiple extends MultiBehavior<IHarvestedBehavior> implements IHarvestedBehavior{

		@Override
		public void execute(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
			for(IHarvestedBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, state, player);
			}
		}
	}
}
