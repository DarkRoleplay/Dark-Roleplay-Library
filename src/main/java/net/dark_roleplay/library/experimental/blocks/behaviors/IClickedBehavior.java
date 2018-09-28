package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IClickedBehavior extends IBlockBehavior{

	public void execute(World world, BlockPos pos, EntityPlayer player);

	public static class Multiple extends MultiBehavior<IClickedBehavior> implements IClickedBehavior{

		@Override
		public void execute(World world, BlockPos pos, EntityPlayer player) {
			for(IClickedBehavior behavior : this.behaviors) {
				behavior.execute(world, pos, player);
			}
		}
	}
}
