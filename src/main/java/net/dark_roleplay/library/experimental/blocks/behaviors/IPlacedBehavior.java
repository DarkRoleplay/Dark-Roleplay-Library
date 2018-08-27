package net.dark_roleplay.library.experimental.blocks.behaviors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created: 31.05.2018
 * Last edit: 31.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: Experimental
 */
public interface IPlacedBehavior {

	public void execute(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack);
	
}
