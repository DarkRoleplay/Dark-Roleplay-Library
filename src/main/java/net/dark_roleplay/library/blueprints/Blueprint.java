package net.dark_roleplay.library.blueprints;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Created: 26.05.2018
 * Last edit: 26.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: not completed
 */
public class Blueprint {

	private List<String> requiredMods;
	private short sizeX, sizeY, sizeZ;
	private short palleteSize;
	private IBlockState[] pallete;
	private String name;
	private String[] architects;
	private String[] missingMods;

	private short[][][] structure;
	private NBTTagCompound[] tileEntities;
	private NBTTagCompound[] entities;

	protected Blueprint(short sizeX, short sizeY, short sizeZ, short palleteSize, IBlockState[] pallete,
			short[][][] structure, NBTTagCompound[] tileEntities, List<String> requiredMods) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		this.palleteSize = palleteSize;
		this.pallete = pallete;
		this.structure = structure;
		this.tileEntities = tileEntities;
		this.requiredMods = requiredMods;
	}

	/**
	 * @return the Size of the Structure on the X-Axis (without rotation and/or
	 *         mirroring)
	 */
	public short getSizeX() {
		return sizeX;
	}

	/**
	 * @return the Size of the Structure on the Y-Axis (without rotation and/or
	 *         mirroring)
	 */
	public short getSizeY() {
		return sizeY;
	}

	/**
	 * @return the Size of the Structure on the Z-Axis (without rotation and/or
	 *         mirroring)
	 */
	public short getSizeZ() {
		return sizeZ;
	}

	/**
	 * @return the amount of Blockstates within the pallete
	 */
	public short getPalleteSize() {
		return palleteSize;
	}

	/**
	 * @return the pallete (without rotation and/or mirroring)
	 */
	public IBlockState[] getPallete() {
		return pallete;
	}

	/**
	 * @return the structure (without rotation and/or mirroring)
	 */
	public short[][][] getStructure() {
		return structure;
	}

	/**
	 * @return an array of serialized TileEntities (posX, posY and posZ tags have
	 *         been localized to coordinates within the structure)
	 */
	public NBTTagCompound[] getTileEntities() {
		return tileEntities;
	}

	/**
	 * @return a list of all required mods as modid's
	 */
	public List<String> getRequiredMods() {
		return requiredMods;
	}

	/**
	 * @return the Name of the Structure
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Structure
	 * 
	 * @param name
	 */
	public Blueprint setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return an Array of all architects for this structure
	 */
	public String[] getArchitects() {
		return architects;
	}

	/**
	 * Sets an Array of all architects for this structure
	 * 
	 * @param architects
	 */
	public Blueprint setArchitects(String[] architects) {
		this.architects = architects;
		return this;
	}

	/**
	 * @return An Array of all missing mods that are required to generate this
	 *         structure (only works if structure was loaded from file)
	 */
	public String[] getMissingMods() {
		return this.missingMods;
	}

	/**
	 * Sets the missing mods
	 * 
	 * @param missingMods
	 */
	public Blueprint setMissingMods(String... missingMods) {
		this.missingMods = missingMods;
		return this;
	}

	/**
	 * Checks if no Blocks are overlapping with the structure
	 * 
	 * @param world
	 *            The World in which to check
	 * @param pos
	 *            The Position at which to check
	 * @param mirror
	 *            The Mirroring to Use
	 * @return true if the structure can be generated at given position.
	 */
	public boolean canBuild(World world, BlockPos pos, Mirror mirror) {
		return canBuild(world, pos, Rotation.NONE, mirror);
	}

	/**
	 * Checks if no Blocks are overlapping with the structure
	 * 
	 * @param world
	 *            The World in which to check
	 * @param pos
	 *            The Position at which to check
	 * @return true if the structure can be generated at given position.
	 */
	public boolean canBuild(World world, BlockPos pos) {
		return canBuild(world, pos, Rotation.NONE, Mirror.NONE);
	}

	/**
	 * Checks if no Blocks are overlapping with the structure
	 * 
	 * @param world
	 *            The World in which to check
	 * @param pos
	 *            The Position at which to check
	 * @param rotation
	 *            The Rotation to use
	 * @return true if the structure can be generated at given position.
	 */
	public boolean canBuild(World world, BlockPos pos, Rotation rotation) {
		return canBuild(world, pos, rotation, Mirror.NONE);
	}

	/**
	 * Checks if no Blocks are overlapping with the structure
	 * 
	 * @param world
	 *            The World in which to check
	 * @param pos
	 *            The Position at which to check
	 * @param rotation
	 *            The Rotation to use
	 * @param mirror
	 *            The Mirroring to Use
	 * @return true if the structure can be generated at given position.
	 */
	public boolean canBuild(World world, BlockPos pos, Rotation rotation, Mirror mirror) {
		IBlockState[] pallete = this.getPallete();
		short[][][] structure = this.getStructure();
		for (short y = 0; y < this.getSizeY(); y++) {
			for (short z = 0; z < this.getSizeZ(); z++) {
				for (short x = 0; x < this.getSizeX(); x++) {
					IBlockState state = pallete[structure[y][z][x] & 0xFFFF];
					if (state.getBlock() == Blocks.STRUCTURE_VOID)
						continue;
					if (!world.getBlockState(transformedBlockPos(x, y, z, mirror, rotation)).getBlock()
							.isReplaceable(world, pos.add(x, y, z)))
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * Used to generate a Blueprint with Rotation and Mirroring
	 * 
	 * @param world
	 *            The world to generate the structure
	 * @param pos
	 *            The Position to build the structure
	 * @param rotation
	 *            The Rotation with which the structure should be generated
	 * @param mirror
	 *            The Mirroring with which the structure should be generated
	 */
	public void build(World world, BlockPos pos, Rotation rotation, Mirror mirror) {
		IBlockState[] pallete = new IBlockState[this.pallete.length];
		for (int i = 0; i < pallete.length; i++) {
			pallete[i] = this.pallete[i].withRotation(rotation).withMirror(mirror);
		}
		build(world, pos, pallete, this.structure, rotation, mirror);
	}

	/**
	 * The main method to generate a structure, rotation and mirror don't affect the
	 * pallete, it does just change the structure.
	 */
	protected void build(World world, BlockPos pos, IBlockState[] pallete, short[][][] structure, Rotation rotation,
			Mirror mirror) {
		for (short y = 0; y < structure.length; y++) {
			for (short z = 0; z < structure[0].length; z++) {
				for (short x = 0; x < structure[0][0].length; x++) {

					IBlockState state = pallete[structure[y][z][x] & 0xFFFF];
					if (state.getBlock() == Blocks.STRUCTURE_VOID)
						continue;
					if (state.isFullCube()) {
						BlockPos rotated = transformedBlockPos(x, y, z, mirror, rotation);
						world.setBlockState(pos.add(rotated.getX(), rotated.getY(), rotated.getZ()), state, 2);
					}
				}
			}
		}

		for (short y = 0; y < this.getSizeY(); y++) {
			for (short z = 0; z < this.getSizeZ(); z++) {
				for (short x = 0; x < this.getSizeX(); x++) {
					IBlockState state = pallete[structure[y][z][x]];
					if (state.getBlock() == Blocks.STRUCTURE_VOID)
						continue;
					if (!state.isFullCube()) {
						BlockPos rotated = transformedBlockPos(x, y, z, Mirror.NONE, rotation);
						world.setBlockState(pos.add(rotated.getX(), rotated.getY(), rotated.getZ()), state, 2);
					}
				}
			}
		}

		if (this.getTileEntities() != null) {
			for (NBTTagCompound tag : this.getTileEntities()) {
				TileEntity te = world.getTileEntity(pos.add(tag.getShort("x"), tag.getShort("y"), tag.getShort("z")));
				tag.setInteger("x", pos.getX() + tag.getShort("x"));
				tag.setInteger("y", pos.getY() + tag.getShort("y"));
				tag.setInteger("z", pos.getZ() + tag.getShort("z"));
				te.deserializeNBT(tag);
			}
		}
	}

	public void destroy(World world, BlockPos pos) {
		destroy(world, pos, Rotation.NONE, Mirror.NONE);
	}

	public void destroy(World world, BlockPos pos, Rotation rotation) {
		destroy(world, pos, this.pallete, this.structure, rotation, Mirror.NONE);
	}

	public void destroy(World world, BlockPos pos, Mirror mirror) {
		destroy(world, pos, this.pallete, this.structure, Rotation.NONE, mirror);
	}

	public void destroy(World world, BlockPos pos, Rotation rotation, Mirror mirror) {
		destroy(world, pos, this.pallete, this.structure, rotation, mirror);
	}

	public void destroy(World world, BlockPos pos, IBlockState[] pallete, short[][][] structure, Rotation rotation,
			Mirror mirror) {
		int indexVoid = -1;
		for (short y = 0; y < structure.length; y++) {
			for (short z = 0; z < structure[0].length; z++) {
				for (short x = 0; x < structure[0][0].length; x++) {

					if (indexVoid == -1) {
						IBlockState state = pallete[structure[y][z][x] & 0xFFFF];
						if (state.getBlock() == Blocks.STRUCTURE_VOID) {
							indexVoid = structure[y][z][x];
						} else {
							BlockPos rotated = transformedBlockPos(x, y, z, mirror, rotation);
							world.setBlockState(pos.add(rotated.getX(), rotated.getY(), rotated.getZ()),
									Blocks.AIR.getDefaultState(), 2);
						}
					} else if (structure[y][z][x] == indexVoid) {
						continue;
					} else {
						BlockPos rotated = transformedBlockPos(x, y, z, mirror, rotation);
						world.setBlockState(pos.add(rotated.getX(), rotated.getY(), rotated.getZ()),
								Blocks.AIR.getDefaultState(), 2);
					}
				}
			}
		}
	}

	/**
	 * Transforms a BlockPos corresponding to a rotation and mirror value
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param mirror
	 *            The value of mirroring
	 * @param rotation
	 *            The value to rotate the blockstate
	 * @return a Tranformed BlockPos
	 */
	protected static BlockPos transformedBlockPos(int x, int y, int z, Mirror mirror, Rotation rotation) {
		boolean flag = true;

		switch (mirror) {
		case LEFT_RIGHT:
			z = -z;
			break;
		case FRONT_BACK:
			x = -x;
			break;
		default:
			flag = false;
		}

		switch (rotation) {
		case COUNTERCLOCKWISE_90:
			return new BlockPos(z, y, -x);
		case CLOCKWISE_90:
			return new BlockPos(-z, y, x);
		case CLOCKWISE_180:
			return new BlockPos(-x, y, -z);
		default:
			return flag ? new BlockPos(x, y, z) : new BlockPos(x, y, z);
		}
	}
}
