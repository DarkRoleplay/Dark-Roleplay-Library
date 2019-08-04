package net.dark_roleplay.library.unstable.experimental.schematics.layers;

import net.dark_roleplay.library.unstable.experimental.data_compression.IntArrayCompression;
import net.dark_roleplay.library.unstable.experimental.schematics.Constants.CompressionType;
import net.dark_roleplay.library.unstable.experimental.schematics.exception.DataCorruptedException;
import net.dark_roleplay.library.unstable.experimental.schematics.palletes.IPallete;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class LayerLoader {

	public static Layer loadLayer(CompoundNBT compound, IPallete globalPallete) {

		byte		compressionType		= compound.getByte("CompressionType");

		BlockPos	size				= NBTUtil.readBlockPos(compound.getCompound("Size"));
		BlockPos	origin				= NBTUtil.readBlockPos(compound.getCompound("Origin"));

		ListNBT		entities			= compound.getList("Entities", NBT.TAG_COMPOUND);
		ListNBT		tileEntities		= compound.getList("Entities", NBT.TAG_COMPOUND);
		CompoundNBT	custom				= compound.getCompound("Custom");

		IPallete	pallete				= null;
		int[][][]	decompressedData	= new int[size.getX()][size.getY()][size.getZ()];

		if(compressionType == CompressionType.NONE.getID()) {
			pallete = globalPallete;
			int[] data = compound.getIntArray("Data");

			for(int y = 0; y < size.getY(); y++ ) {
				for(int x = 0; x < size.getX(); x++ ) {
					for(int z = 0; z < size.getZ(); z++ ) {
						decompressedData[x][y][z] = data[ (y * (size.getX() * size.getZ())) + (x * size.getZ()) + z];
					}
				}
			}
		} else if(compressionType == CompressionType.SIMPLE.getID()) {
			int[]	localPallete	= compound.getIntArray("LocalPallete");
			int		bitCount		= compound.contains("LocalPallete") ? IntArrayCompression.getRequiredBitCount(localPallete.length) : globalPallete.getRequiredBitsAmounts();
			int[]	decompressed	= IntArrayCompression.decompress(bitCount, compound.getIntArray("Data"), size.getX() * size.getY() * size.getZ());
			// TODO Implement data loading for Compression Type 1
		} else if(compressionType == CompressionType.ADVANCED.getID()) {
			if(compound.getTagId("Data") != NBT.TAG_LONG_ARRAY) throw new DataCorruptedException(String.format("The Data tag seems to have the wrong Format, it seems to be %d but should be 12", compound.getTagId("Data")));
			// TODO Implement data loading for Compression Type 2
		}

		Layer layer = new Layer(pallete, size, origin, decompressedData);

		return layer;
	}

}
