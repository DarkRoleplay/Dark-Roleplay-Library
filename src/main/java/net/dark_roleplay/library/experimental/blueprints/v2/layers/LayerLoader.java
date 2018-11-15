package net.dark_roleplay.library.experimental.blueprints.v2.layers;

public class LayerLoader {

//	public static Layer loadLayer(NBTTagCompound compound, IPallete globalPallete) {
//
//		byte compressionType = compound.getByte("CompressionType");
//
//		BlockPos size = NBTUtil.getPosFromTag(compound.getCompoundTag("Size"));
//		BlockPos origin = NBTUtil.getPosFromTag(compound.getCompoundTag("Origin"));
//
//		NBTTagList entities = compound.getTagList("Entities", NBT.TAG_COMPOUND);
//		NBTTagList tileEntities = compound.getTagList("Entities", NBT.TAG_COMPOUND);
//		NBTTagCompound custom = compound.getCompoundTag("Custom");
//
//		IPallete pallete = null;
//		if(compressionType == CompressionType.NONE.getID()) {
//			pallete = globalPallete;
//		}else if(compressionType == CompressionType.SIMPLE.getID()) {
//			int[] localPallete = compound.getIntArray("LocalPallete");
//			int bitCount = compound.hasKey("LocalPallete") ? IntArrayCompression.getRequiredBitCount(localPallete.length) : globalPallete.getRequiredBitsAmounts();
//			int[] decompressed = IntArrayCompression.decompress(bitCount, compound.getIntArray("Data"), size.getX() * size.getY() * size.getZ());
//
//		}else if(compressionType == CompressionType.ADVANCED.getID()) {
//			if(compound.getTagId("Data") != NBT.TAG_LONG_ARRAY) throw new DataCorruptedException(String.format("The Data tag seems to have the wrong Format, it seems to be %d but should be 12", compound.getTagId("Data")));
//
//		}
//	}

}
