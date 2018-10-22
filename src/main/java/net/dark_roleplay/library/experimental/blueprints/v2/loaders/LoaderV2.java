package net.dark_roleplay.library.experimental.blueprints.v2.loaders;

import java.util.zip.ZipFile;

import net.dark_roleplay.library.experimental.blueprints.v2.Blueprint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class LoaderV2 {


	public static final String NAME_KEY = "Name";
	public static final String CREDITS_KEY = "Credits";
	public static final String SIZE_KEY = "Size";

	public static final String DEFAULT_NAME = "unnamed";
	public static final String[] DEFAULT_CREDITS = new String[0];

	public static Blueprint loadBlueprint(ZipFile file, NBTTagCompound mainCompound, Blueprint blueprint) {

		String name = mainCompound.hasKey(NAME_KEY) ? mainCompound.getString(NAME_KEY) : DEFAULT_NAME;
		String[] credits = !mainCompound.hasKey(CREDITS_KEY) ? DEFAULT_CREDITS : getStringArray(mainCompound.getTagList(CREDITS_KEY, NBT.TAG_STRING));

		blueprint.setName(name);
		blueprint.setCredits(credits);

		return blueprint;
	}

	private static String[] getStringArray(NBTTagList list) {
		if(list.getTagType() != NBT.TAG_STRING) return new String[0];
		String[] arr = new String[list.tagCount()];

		for(int i = 0; i < list.tagCount(); i++)
			arr[i] = list.getStringTagAt(i);

		return arr;
	}
}
