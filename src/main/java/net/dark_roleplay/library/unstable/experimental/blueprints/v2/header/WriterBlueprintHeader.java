package net.dark_roleplay.library.unstable.experimental.blueprints.v2.header;

import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_CREDITS;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_CUSTOM;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_DESC;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_LAYERS;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_MODS;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_NAME;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_SIZE;
import static net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader.LoaderV2.KEY_VERSION;

import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;

public class WriterBlueprintHeader {

	public static NBTTagCompound writeBlueprintHeaderV2(BlueprintHeader header){
		NBTTagCompound comp = new NBTTagCompound();

		String test = KEY_CREDITS;

		//Required
		comp.setInt(KEY_VERSION, 2);
		comp.setTag(KEY_SIZE, NBTUtil.writeBlockPos(header.getSize()));

		NBTTagList requiredModsTag = new NBTTagList();
		Set<String> requiredMods = header.getRequiredMods();
		requiredMods.forEach(mod -> requiredModsTag.add(new NBTTagString(mod)));
		comp.setTag(KEY_MODS, requiredModsTag);

		//Optional
		comp.setString(KEY_NAME, header.getName());
		comp.setString(KEY_DESC, header.getDescription());

		NBTTagList creditsTag = new NBTTagList();
		Set<String> credits = header.getCredits();
		credits.forEach(credit -> creditsTag.add(new NBTTagString(credit)));
		comp.setTag(KEY_CREDITS, creditsTag);

		NBTTagList layersTag = new NBTTagList();
		Set<String> layers = header.getLayerFiles();
		layers.forEach(layer -> layersTag.add(new NBTTagString(layer)));
		comp.setTag(KEY_LAYERS, layersTag);

		comp.setTag(KEY_CUSTOM, header.getAllCustomData());

		return comp;
	}
}
