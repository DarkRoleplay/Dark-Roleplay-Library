package net.dark_roleplay.library.unstable.experimental.schematics.palletes;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.common.util.Constants.NBT;

public final class GlobalPalleteLoader {

	private GlobalPalleteLoader() {}

	public static GlobalPallete loadBlueprintHeader(CompoundNBT compound) {
		GlobalPallete	pallete		= new GlobalPallete();

		ListNBT			blockStates	= compound.getList("BlockStates", NBT.TAG_COMPOUND);

		for(int i = 0; i < blockStates.size(); i++ ) {
			pallete.getOrAddIDForState(NBTUtil.readBlockState(blockStates.getCompound(i)));
		}

		if(compound.contains("StructureVoidID")) pallete.setStructureVoidID(compound.getInt("StructureVoidID"));

		if(compound.contains("StructureSolidID")) pallete.setStructureSolidID(compound.getInt("StructureSolidID"));

		return pallete;
	}
}
