package net.dark_roleplay.library.unstable.experimental.blueprints.v2.palletes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.common.util.Constants.NBT;

public final class GlobalPalleteLoader {

	private GlobalPalleteLoader() {}

	public static GlobalPallete loadBlueprintHeader(NBTTagCompound compound){
		GlobalPallete pallete = new GlobalPallete();

		NBTTagList blockStates = compound.getList("BlockStates", NBT.TAG_COMPOUND);

		for(int i = 0; i < blockStates.size(); i++) {
			pallete.getOrAddIDForState(NBTUtil.readBlockState(blockStates.getCompound(i)));
		}

		if(compound.hasKey("StructureVoidID"))
			pallete.setStructureVoidID(compound.getInt("StructureVoidID"));

		if(compound.hasKey("StructureSolidID"))
			pallete.setStructureSolidID(compound.getInt("StructureSolidID"));

		return pallete;
	}
}
