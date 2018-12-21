package net.dark_roleplay.library.experimental.blueprints.v2.layers;

import net.dark_roleplay.library.experimental.blueprints.v2.Constants.CompressionType;
import net.dark_roleplay.library.experimental.blueprints.v2.exception.DataCorruptedException;
import net.dark_roleplay.library.experimental.blueprints.v2.palletes.IPallete;
import net.dark_roleplay.library.experimental.data_compression.IntArrayCompression;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class LayerLoader {

	public static Layer loadLayer(NBTTagCompound compound, IPallete globalPallete) {

		byte compressionType = compound.getByte("CompressionType");

		BlockPos size = NBTUtil.getPosFromTag(compound.getCompoundTag("Size"));
		BlockPos origin = NBTUtil.getPosFromTag(compound.getCompoundTag("Origin"));

		NBTTagList entities = compound.getTagList("Entities", NBT.TAG_COMPOUND);
		NBTTagList tileEntities = compound.getTagList("Entities", NBT.TAG_COMPOUND);
		NBTTagCompound custom = compound.getCompoundTag("Custom");

		IPallete pallete = null;
		int[][][] decompressedData = new int[size.getX()][size.getY()][size.getZ()];

		if(compressionType == CompressionType.NONE.getID()) {
			pallete = globalPallete;
			int[] data = compound.getIntArray("Data");

			for(int y = 0; y < size.getY(); y++) {
				for(int x = 0; x < size.getX(); x++) {
					for(int z = 0; z < size.getZ(); z++) {
						decompressedData[x][y][z] = data[(y * (size.getX() * size.getZ())) + (x * size.getZ()) + z];
					}
				}
			}
		}else if(compressionType == CompressionType.SIMPLE.getID()) {
			int[] localPallete = compound.getIntArray("LocalPallete");
			int bitCount = compound.hasKey("LocalPallete") ? IntArrayCompression.getRequiredBitCount(localPallete.length) : globalPallete.getRequiredBitsAmounts();
			int[] decompressed = IntArrayCompression.decompress(bitCount, compound.getIntArray("Data"), size.getX() * size.getY() * size.getZ());
			//TODO Implement data loading for Compression Type 1
		}else if(compressionType == CompressionType.ADVANCED.getID()) {
			if(compound.getTagId("Data") != NBT.TAG_LONG_ARRAY) throw new DataCorruptedException(String.format("The Data tag seems to have the wrong Format, it seems to be %d but should be 12", compound.getTagId("Data")));
			//TODO Implement data loading for Compression Type 2
		}

		Layer layer = new Layer(pallete, size, origin, decompressedData);

		return layer;
	}

}
