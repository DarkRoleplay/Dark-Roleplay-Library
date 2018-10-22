package net.dark_roleplay.library.experimental.blueprints.v2;

import java.util.zip.ZipFile;

import net.dark_roleplay.library.experimental.blueprints.v2.loaders.LoaderV2;
import net.dark_roleplay.library.experimental.zip_helper.ZipNBTHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class Blueprint {

	public static final String VERSION_KEY = "Version";

	private String name = "unnamed";
	private BlockPos size = new BlockPos(1, 1, 1);
	private String[] requiredMods = new String[0];
	private String[] credits = new String[0];

	private Blueprint() {}

	public static Blueprint loadBlueprint(ZipFile file) {
		NBTTagCompound compound = ZipNBTHelper.getTagFromZip(file, "blueprint.nbt");
		if(compound == null) return null;

		Blueprint blueprint = new Blueprint();

		if(!compound.hasKey(VERSION_KEY)) return null;

		switch(compound.getByte(VERSION_KEY)) {
		case 1:
			//TODO reimplement v1 Loader
			break;
		case 2:
			blueprint = LoaderV2.loadBlueprint(file, compound, blueprint);
			break;
		default:
			break;
		}

		return blueprint;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BlockPos getSize() {
		return this.size;
	}

	public void setSize(BlockPos size) {
		this.size = size;
	}

	public String[] getRequiredMods() {
		return this.requiredMods;
	}

	public void setRequiredMods(String[] requiredMods) {
		this.requiredMods = requiredMods;
	}

	public String[] getCredits() {
		return this.credits;
	}

	public void setCredits(String[] credits) {
		this.credits = credits;
	}
}
