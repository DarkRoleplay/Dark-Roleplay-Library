package net.dark_roleplay.library.blocks.behaviors;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created: 31.05.2018
 * Last edit: 31.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: Experimental
 */
public interface IWalkedUpon {

	public void execute(World world, BlockPos pos, Entity entity);

}
