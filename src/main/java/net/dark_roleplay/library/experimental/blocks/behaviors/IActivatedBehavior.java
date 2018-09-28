package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IActivatedBehavior extends IBlockBehavior{

	public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ);

	public static class Multiple extends MultiBehavior<IActivatedBehavior> implements IActivatedBehavior{

		@Override
		public boolean execute(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			for(IActivatedBehavior behavior : this.behaviors) {
				if(behavior.execute(world, pos, state, player, hand, facing, hitX, hitY, hitZ))
					return true;
			}
			return false;
		}
	}
}
