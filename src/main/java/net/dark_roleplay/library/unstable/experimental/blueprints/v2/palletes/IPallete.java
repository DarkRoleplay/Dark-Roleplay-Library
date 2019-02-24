package net.dark_roleplay.library.unstable.experimental.blueprints.v2.palletes;

import java.util.Set;

import net.dark_roleplay.library.unstable.experimental.blueprints.v2.exception.NotContainedInPalleteException;
import net.minecraft.block.state.IBlockState;

/**
 * @author JTK222
 * @version 1.0
 */
public interface IPallete {

	/**
	 * This Method, returns the ID belonging to an IBlockState within this IPallete.<br>
	 * And throws an exception if it doesn't exist.
	 * @param state the IBlockState which ID you want
	 * @return The ID of the passed in IBlockState within this pallete.
	 * @throws NotContainedInPalleteException In case the State isn't contained in the pallete
	 */
	public int getIDForState(IBlockState state) throws NotContainedInPalleteException;

	/**
	 * This Method returns, the ID of an IBlockState within the pallete,<br>
	 * the IBlockState is added to the pallete in case it was missing.
	 * @param state the IBlockState which ID you want
	 * @return The ID of the passed in IBlockState within this pallete.
	 */
	public int getOrAddIDForState(IBlockState state);

	/**
	 * This Method, returns the IBlockState belonging to an ID within this IPallete.<br>
	 * And throws an exception if it doesn't exist.
	 * @param id the ID of which IBlockState you want
	 * @return The ID of the passed in IBlockState within this pallete.
	 * @throws NotContainedInPalleteException In case the State isn't contained in the pallete
	 */
	public IBlockState getStateForID(int id) throws NotContainedInPalleteException;

	/**
	 * @return The amount of Required Bits, to represent every single entry in this pallete.
	 */
	public byte getRequiredBitsAmounts();

	/**
	 * @return The Set of required mods for this Pallete
	 */
	public Set<String> getRequiredMods();

	/**
	 * @return The ID of the IBlockState, that is supposed to be used as Structure Void
	 */
	public int getStructureVoidID();

	/**
	 * @return The ID of the IBlockState, that is supposed to be used as Structure Solid,<br>
	 * which is similar to the Structure Void, but it will place the BlockState if if the position would be an non solid block otherwise.
	 */
	public int getStructureSolidID();

}
