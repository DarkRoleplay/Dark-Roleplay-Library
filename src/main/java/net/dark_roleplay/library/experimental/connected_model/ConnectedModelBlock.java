package net.dark_roleplay.library.experimental.connected_model;

import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.CENTER_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.CENTER_RIGHT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.HORIZONTAL;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_CENTER;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.NORTH_RIGHT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_CENTER;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_LEFT;
import static net.dark_roleplay.library.experimental.connected_model.ConnectedModelBlockStates.SOUTH_RIGHT;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class ConnectedModelBlock extends Block{

	public ConnectedModelBlock() {
		super(Material.WOOD);
//		this.setRegistryName("table_test");
		this.setDefaultState(
			this.getDefaultState()
		);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new ExtendedBlockState(this, new IProperty[0], HORIZONTAL);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		if(!(state instanceof IExtendedBlockState)) return state;

		IExtendedBlockState stateCopy = (IExtendedBlockState) state;

		stateCopy = stateCopy.withProperty(NORTH_LEFT, world.getBlockState(pos.north().west()).getBlock() == this);
		stateCopy = stateCopy.withProperty(NORTH_CENTER, world.getBlockState(pos.north()).getBlock() == this);
		stateCopy = stateCopy.withProperty(NORTH_RIGHT, world.getBlockState(pos.north().east()).getBlock() == this);
		stateCopy = stateCopy.withProperty(SOUTH_LEFT, world.getBlockState(pos.south().west()).getBlock() == this);
		stateCopy = stateCopy.withProperty(SOUTH_CENTER, world.getBlockState(pos.south()).getBlock() == this);
		stateCopy = stateCopy.withProperty(SOUTH_RIGHT, world.getBlockState(pos.south().east()).getBlock() == this);
		stateCopy = stateCopy.withProperty(CENTER_LEFT, world.getBlockState(pos.west()).getBlock() == this);
		stateCopy = stateCopy.withProperty(CENTER_RIGHT, world.getBlockState(pos.east()).getBlock() == this);

		return stateCopy;
	}

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
	@Deprecated
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
	public int getMetaFromState(IBlockState state){
    	return 0;
    }
}
