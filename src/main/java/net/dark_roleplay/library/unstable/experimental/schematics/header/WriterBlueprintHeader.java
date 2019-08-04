package net.dark_roleplay.library.unstable.experimental.schematics.header;

import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_CREDITS;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_CUSTOM;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_DESC;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_LAYERS;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_MODS;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_NAME;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_SIZE;
import static net.dark_roleplay.library.unstable.experimental.schematics.header.LoaderBlueprintHeader.LoaderV2.KEY_VERSION;

import java.util.Set;

import net.dark_roleplay.library.unstable.experimental.schematics.api.SchematicHeader;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.nbt.StringNBT;

public class WriterBlueprintHeader {

	public static CompoundNBT writeBlueprintHeaderV2(SchematicHeader header) {
		CompoundNBT	comp	= new CompoundNBT();

		String		test	= KEY_CREDITS;

		// Required
		comp.putInt(KEY_VERSION, 2);
		comp.put(KEY_SIZE, NBTUtil.writeBlockPos(header.getSize()));

		ListNBT		requiredModsTag	= new ListNBT();
		Set<String>	requiredMods	= header.getRequiredMods();
		requiredMods.forEach(mod -> requiredModsTag.add(new StringNBT(mod)));
		comp.put(KEY_MODS, requiredModsTag);

		// Optional
		comp.putString(KEY_NAME, header.getName());
		comp.putString(KEY_DESC, header.getDescription());

		ListNBT		creditsTag	= new ListNBT();
		Set<String>	credits		= header.getCredits();
		credits.forEach(credit -> creditsTag.add(new StringNBT(credit)));
		comp.put(KEY_CREDITS, creditsTag);

		ListNBT		layersTag	= new ListNBT();
		Set<String>	layers		= header.getLayerFiles();
		layers.forEach(layer -> layersTag.add(new StringNBT(layer)));
		comp.put(KEY_LAYERS, layersTag);

		comp.put(KEY_CUSTOM, header.getAllCustomData());

		return comp;
	}
}
