package net.dark_roleplay.library.blueprints;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;

/**
 * @since 0.1.0
 *        State: not completed
 * @see <a href="http://dark-roleplay.net/other/blueprint_format.php">Blueprint
 *      V1 Specification</a>
 */
public class BlueprintUtil {

	/**
	 * Generates a Blueprint objects from the world
	 *
	 * @param world The World that is used for the Blueprint
	 * @param pos   The Position of the Blueprint
	 * @param sizeX The Size on the X-Axis
	 * @param sizeY The Size on the Y-Axis
	 * @param sizeZ The Size on the Z-Axis
	 * @return the generated Blueprint
	 */
	public static Blueprint createBlueprint(World world, BlockPos pos, short sizeX, short sizeY, short sizeZ) {
		return createBlueprint(world, pos, sizeX, sizeY, sizeZ, null);
	}

	/**
	 * Generates a Blueprint objects from the world
	 *
	 * @param world
	 *                   The World that is used for the Blueprint
	 * @param pos
	 *                   The Position of the Blueprint
	 * @param sizeX
	 *                   The Size on the X-Axis
	 * @param sizeY
	 *                   The Size on the Y-Axis
	 * @param sizeZ
	 *                   The Size on the Z-Axis
	 * @param name
	 *                   a Name for the Structure
	 * @param architects
	 *                   an Array of Architects for the structure
	 * @return the generated Blueprint
	 */
	public static Blueprint createBlueprint(World world, BlockPos pos, short sizeX, short sizeY, short sizeZ, String name, String... architects) {
		List<BlockState> pallete = new ArrayList<BlockState>();
		// Allways add AIR to Pallete
		pallete.add(Blocks.AIR.getDefaultState());
		short[][][]			structure		= new short[sizeY][sizeZ][sizeX];

		List<CompoundNBT>	tileEntities	= new ArrayList<CompoundNBT>();

		List<String>		requiredMods	= new ArrayList<String>();

		long				time			= System.currentTimeMillis();

		Iterator<BlockPos>	positions		= BlockPos.getAllInBoxMutable(pos, pos.add(sizeX - 1, sizeY - 1, sizeZ - 1)).iterator();
		while(positions.hasNext()) {
			BlockPos	mutablePos	= positions.next();
			BlockState	state		= world.getBlockState(mutablePos);
			String		modName		= state.getBlock().getRegistryName().getNamespace();

			short		x			= (short) (mutablePos.getX() - pos.getX()), y = (short) (mutablePos.getY() - pos.getY()), z = (short) (mutablePos.getZ() - pos.getZ());

			if( !requiredMods.contains(modName)) {
				if(ModList.get().isLoaded(modName)) requiredMods.add(modName);
			} else if( !ModList.get().isLoaded(modName)) {
				structure[y][z][x] = (short) pallete.indexOf(Blocks.AIR.getDefaultState());
				continue;
			}

			TileEntity te = world.getTileEntity(mutablePos);
			if(te != null) {
				CompoundNBT teTag = te.serializeNBT();
				teTag.putShort("x", x);
				teTag.putShort("y", y);
				teTag.putShort("z", z);
				tileEntities.add(teTag);
			}
			if( !pallete.contains(state)) pallete.add(state);
			structure[y][z][x] = (short) pallete.indexOf(state);
		}

		BlockState[] states = new BlockState[pallete.size()];
		states = pallete.toArray(states);

		CompoundNBT[] tes = new CompoundNBT[tileEntities.size()];
		tes = tileEntities.toArray(tes);

		List<CompoundNBT>	entitiesTag	= new ArrayList<CompoundNBT>();

		List<Entity>		entities	= world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + sizeX, pos.getY() + sizeY, pos.getZ() + sizeZ));

		for(Entity entity : entities) {
			Vec3d oldPos = entity.getPositionVector();
			entity.setPosition(oldPos.x - pos.getX(), oldPos.y - pos.getY(), oldPos.z - pos.getZ());
			CompoundNBT entityTag = new CompoundNBT();
			if(entity.writeUnlessPassenger(entityTag)) entitiesTag.add(entityTag);
			entity.setPosition(oldPos.x, oldPos.y, oldPos.z);
		}

		Blueprint schem = new Blueprint(sizeX, sizeY, sizeZ, (short) pallete.size(), states, structure, tes, requiredMods);
		schem.setEntities(entitiesTag.toArray(new CompoundNBT[entitiesTag.size()]));

		if(name != null) schem.setName(name);

		if(architects != null) schem.setArchitects(architects);

		return schem;
	}

	/**
	 * Serializes a given Blueprint to an CompoundNBT
	 *
	 * @param schem
	 *              The Blueprint to serialize
	 * @return An CompoundNBT containing the Blueprint Data
	 */
	public static CompoundNBT writeBlueprintToNBT(Blueprint schem) {
		CompoundNBT tag = new CompoundNBT();
		// Set Blueprint Version
		tag.putByte("version", (byte) 1);
		// Set Blueprint Size
		tag.putShort("size_x", schem.getSizeX());
		tag.putShort("size_y", schem.getSizeY());
		tag.putShort("size_z", schem.getSizeZ());

		// Create Pallete
		BlockState[]	palette		= schem.getPallete();
		ListNBT			paletteTag	= new ListNBT();
		for(short i = 0; i < schem.getPalleteSize(); i++ ) {
			paletteTag.add(NBTUtil.writeBlockState(palette[i]));
		}
		tag.put("palette", paletteTag);

		// Adding blocks
		int[] blockInt = convertBlocksToSaveData(schem.getStructure(), schem.getSizeX(), schem.getSizeY(), schem.getSizeZ());
		tag.putIntArray("blocks", blockInt);

		// Adding Tile Entities
		ListNBT			finishedTes	= new ListNBT();
		CompoundNBT[]	tes			= schem.getTileEntities();
		for(int i = 0; i < tes.length; i++ ) {
			finishedTes.add(tes[i]);
		}
		tag.put("tile_entities", finishedTes);

		// Adding Required Mods
		List<String>	requiredMods	= schem.getRequiredMods();
		ListNBT			modsList		= new ListNBT();
		for(int i = 0; i < requiredMods.size(); i++ ) {
			// modsList.set(i,);
			modsList.add(new StringNBT(requiredMods.get(i)));
		}
		tag.put("required_mods", modsList);

		String		name		= schem.getName();
		String[]	architects	= schem.getArchitects();

		if(name != null) {
			tag.putString("name", name);
		}
		if(architects != null) {
			ListNBT architectsTag = new ListNBT();
			for(String architect : architects) {
				architectsTag.add(new StringNBT(architect));
			}
			tag.put("architects", architectsTag);
		}

		return tag;
	}

	/**
	 * Deserializes a Blueprint form the Given CompoundNBT
	 *
	 * @param tag
	 *            The CompoundNBT containing the Blueprint Data
	 * @return A desserialized Blueprint
	 */
	public static Blueprint readBlueprintFromNBT(CompoundNBT tag) {
		byte version = tag.getByte("version");
		if(version == 1) {
			short			sizeX			= tag.getShort("size_x"), sizeY = tag.getShort("size_y"), sizeZ = tag.getShort("size_z");

			// Reading required Mods
			List<String>	requiredMods	= new ArrayList<String>();
			List<String>	missingMods		= new ArrayList<String>();
			ListNBT			modsList		= (ListNBT) tag.getList("required_mods", NBT.TAG_STRING);
			short			modListSize		= (short) modsList.size();
			for(int i = 0; i < modListSize; i++ ) {
				requiredMods.add( ((StringNBT) modsList.get(i)).getString());
				if( !ModList.get().isLoaded(requiredMods.get(i))) {
					LogManager.getLogger().warn("Found missing mods for Blueprint, some blocks may be missing: " + requiredMods.get(i));
					missingMods.add(requiredMods.get(i));
				}
			}

			// Reading Pallete
			ListNBT			paletteTag	= (ListNBT) tag.get("palette");
			short			paletteSize	= (short) paletteTag.size();
			BlockState[]	palette		= new BlockState[paletteSize];
			for(short i = 0; i < palette.length; i++ ) {
				palette[i] = NBTUtil.readBlockState(paletteTag.getCompound(i));
			}

			// Reading Blocks
			short[][][]		blocks			= convertSaveDataToBlocks(tag.getIntArray("blocks"), sizeX, sizeY, sizeZ);

			// Reading Tile Entities
			ListNBT			teTag			= (ListNBT) tag.get("tile_entities");
			CompoundNBT[]	tileEntities	= new CompoundNBT[teTag.size()];
			for(short i = 0; i < tileEntities.length; i++ ) {
				tileEntities[i] = teTag.getCompound(i);
			}

			Blueprint schem = new Blueprint(sizeX, sizeY, sizeZ, paletteSize, palette, blocks, tileEntities, requiredMods).setMissingMods(missingMods.toArray(new String[missingMods.size()]));

			if(tag.contains("name")) {
				schem.setName(tag.getString("name"));
			}
			if(tag.contains("architects")) {
				ListNBT		architectsTag	= (ListNBT) tag.get("architects");
				String[]	architects		= new String[architectsTag.size()];
				for(int i = 0; i < architectsTag.size(); i++ ) {
					architects[i] = architectsTag.getString(i);
				}
				schem.setArchitects(architects);
			}

			return schem;
		}
		return null;
	}

	/**
	 * Attempts to write a Blueprint to an Output Stream
	 *
	 * @param os
	 *              The Output Stream to write to
	 * @param schem
	 *              The Blueprint to write
	 */
	public static void writeToStream(OutputStream os, Blueprint schem) {
		try {
			CompressedStreamTools.writeCompressed(writeBlueprintToNBT(schem), os);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Attempts to read a Blueprint from an Input Stream
	 *
	 * @param is
	 *           The Input Stream from which to read the Blueprint
	 * @return the Blueprting that was read from the InputStream
	 */
	public static Blueprint readFromFile(InputStream is) {
		CompoundNBT tag;
		try {
			tag = CompressedStreamTools.readCompressed(is);
			return readBlueprintFromNBT(tag);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converts a 3 Dimensional short Array to a one Dimensional int Array
	 *
	 * @param multDimArray
	 *                     3 Dimensional short Array
	 * @param sizeX
	 *                     Sturcture size on the X-Axis
	 * @param sizeY
	 *                     Sturcture size on the Y-Axis
	 * @param sizeZ
	 *                     Sturcture size on the Z-Axis
	 * @return An 1 Dimensional int array
	 */
	protected static int[] convertBlocksToSaveData(short[][][] multDimArray, short sizeX, short sizeY, short sizeZ) {
		// Converting 3 Dimensional Array to One DImensional
		short[]	oneDimArray	= new short[sizeX * sizeY * sizeZ];

		int		j			= 0;
		for(short y = 0; y < sizeY; y++ ) {
			for(short z = 0; z < sizeZ; z++ ) {
				for(short x = 0; x < sizeX; x++ ) {
					oneDimArray[j++ ] = multDimArray[y][z][x];
				}
			}
		}

		// Converting short Array to int Array
		int[]	ints		= new int[(int) Math.ceil(oneDimArray.length / 2f)];

		int		currentInt	= 0;
		for(int i = 1; i < oneDimArray.length; i += 2) {
			currentInt = oneDimArray[i - 1];
			currentInt = currentInt << 16 | oneDimArray[i];
			ints[(int) Math.ceil(i / 2f) - 1] = currentInt;
			currentInt = 0;
		}
		if(oneDimArray.length % 2 == 1) {
			currentInt = oneDimArray[oneDimArray.length - 1] << 16;
			ints[ints.length - 1] = currentInt;
		}
		return ints;
	}

	/**
	 * Converts a 1 Dimensional int Array to a 3 Dimensional short Array
	 *
	 * @param ints
	 *              1 Dimensioanl int Array
	 * @param sizeX
	 *              Sturcture size on the X-Axis
	 * @param sizeY
	 *              Sturcture size on the Y-Axis
	 * @param sizeZ
	 *              Sturcture size on the Z-Axis
	 * @return An 3 Dimensional short array
	 */
	protected static short[][][] convertSaveDataToBlocks(int[] ints, short sizeX, short sizeY, short sizeZ) {
		// Convert int array to short array
		short[] oneDimArray = new short[ints.length * 2];

		for(int i = 0; i < ints.length; i++ ) {
			oneDimArray[i * 2] = (short) (ints[i] >> 16);
			oneDimArray[ (i * 2) + 1] = (short) (ints[i]);
		}

		// Convert 1 Dimensional Array to 3 Dimensional Array
		short[][][]	multDimArray	= new short[sizeY][sizeZ][sizeX];

		int			i				= 0;
		for(short y = 0; y < sizeY; y++ ) {
			for(short z = 0; z < sizeZ; z++ ) {
				for(short x = 0; x < sizeX; x++ ) {
					multDimArray[y][z][x] = oneDimArray[i++ ];
				}
			}
		}
		return multDimArray;
	}
}
