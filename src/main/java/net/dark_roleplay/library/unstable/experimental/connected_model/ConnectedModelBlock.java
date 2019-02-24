package net.dark_roleplay.library.unstable.experimental.connected_model;

import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.CENTER_LEFT;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.CENTER_RIGHT;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.HORIZONTAL;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.NORTH_CENTER;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.NORTH_LEFT;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.NORTH_RIGHT;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.SOUTH_CENTER;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.SOUTH_LEFT;
import static net.dark_roleplay.library.unstable.experimental.connected_model.ConnectedModelBlockStates.SOUTH_RIGHT;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.property.IExtendedBlockState;

public class ConnectedModelBlock extends Block{

	public ConnectedModelBlock(Block.Properties properties) {
		super(properties);
//		this.setRegistryName("table_test");
		this.setDefaultState(
			this.getDefaultState()
		);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
//		builder.add(HORIZONTAL);
	}

	//PORT TO 1.13
//	@Override
//	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
//		if(!(state instanceof IExtendedBlockState)) return state;
//
//		IExtendedBlockState stateCopy = (IExtendedBlockState) state;
//
//		stateCopy = stateCopy.withProperty(NORTH_LEFT, world.getBlockState(pos.north().west()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(NORTH_CENTER, world.getBlockState(pos.north()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(NORTH_RIGHT, world.getBlockState(pos.north().east()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(SOUTH_LEFT, world.getBlockState(pos.south().west()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(SOUTH_CENTER, world.getBlockState(pos.south()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(SOUTH_RIGHT, world.getBlockState(pos.south().east()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(CENTER_LEFT, world.getBlockState(pos.west()).getBlock() == this);
//		stateCopy = stateCopy.withProperty(CENTER_RIGHT, world.getBlockState(pos.east()).getBlock() == this);
//
//		return stateCopy;
//	}
}
