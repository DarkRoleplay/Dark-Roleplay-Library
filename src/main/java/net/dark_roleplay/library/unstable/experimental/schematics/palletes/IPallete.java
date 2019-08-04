package net.dark_roleplay.library.unstable.experimental.schematics.palletes;

import java.util.Set;

import net.dark_roleplay.library.unstable.experimental.schematics.exception.NotContainedInPalleteException;
import net.minecraft.block.BlockState;

/**
 * @author JTK222
 * @version 1.0
 */
public interface IPallete {

	/**
	 * This Method, returns the ID belonging to an BlockState within this
	 * IPallete.<br>
	 * And throws an exception if it doesn't exist.
	 * 
	 * @param state the BlockState which ID you want
	 * @return The ID of the passed in BlockState within this pallete.
	 * @throws NotContainedInPalleteException In case the State isn't contained
	 *                                        in the pallete
	 */
	public int getIDForState(BlockState state) throws NotContainedInPalleteException;

	/**
	 * This Method returns, the ID of an BlockState within the pallete,<br>
	 * the BlockState is added to the pallete in case it was missing.
	 * 
	 * @param state the BlockState which ID you want
	 * @return The ID of the passed in BlockState within this pallete.
	 */
	public int getOrAddIDForState(BlockState state);

	/**
	 * This Method, returns the BlockState belonging to an ID within this
	 * IPallete.<br>
	 * And throws an exception if it doesn't exist.
	 * 
	 * @param id the ID of which BlockState you want
	 * @return The ID of the passed in BlockState within this pallete.
	 * @throws NotContainedInPalleteException In case the State isn't contained
	 *                                        in the pallete
	 */
	public BlockState getStateForID(int id) throws NotContainedInPalleteException;

	/**
	 * @return The amount of Required Bits, to represent every single entry in
	 *         this pallete.
	 */
	public byte getRequiredBitsAmounts();

	/**
	 * @return The Set of required mods for this Pallete
	 */
	public Set<String> getRequiredMods();

	/**
	 * @return The ID of the BlockState, that is supposed to be used as
	 *         Structure Void
	 */
	public int getStructureVoidID();

	/**
	 * @return The ID of the BlockState, that is supposed to be used as
	 *         Structure Solid,<br>
	 *         which is similar to the Structure Void, but it will place the
	 *         BlockState if if the position would be an non solid block
	 *         otherwise.
	 */
	public int getStructureSolidID();

}
