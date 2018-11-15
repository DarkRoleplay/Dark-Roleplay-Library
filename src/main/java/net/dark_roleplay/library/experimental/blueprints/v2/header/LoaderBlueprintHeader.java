package net.dark_roleplay.library.experimental.blueprints.v2.header;

import net.dark_roleplay.library.experimental.blueprints.v2.exception.DataCorruptedException;
import net.dark_roleplay.library.experimental.blueprints.v2.exception.UnsupportedVersionException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public final class LoaderBlueprintHeader {

	private static final String SUPPORTED_VERSIONS = "[1,2]";

	private LoaderBlueprintHeader() {}

	/**
	 * Loads a BlueprintHeader from a NBTTagCompound,<br>
	 * This supports versions 1 - 2
	 * @param compound The Compound containing the Header Data
	 * @return The BlueprintHeader generated from the input NBTTagCompound
	 * @throws DataCorruptedException When no Version can be found
	 * @throws UnsupportedVersionException when the contained Version in the NBTTagCompound isn't supported by the Loader
	 */
	public static BlueprintHeader loadBlueprintHeader(NBTTagCompound compound) throws UnsupportedVersionException{
		if(compound.hasKey(LoaderV2.KEY_VERSION)) {
			return LoaderV2.loadBlueprintHeader(compound);
		}else if(compound.hasKey(LoaderV1.KEY_VERSION)) {
			return LoaderV1.loadBlueprintHeader(compound);
		}else if(compound.hasKey("Version")) {
			throw new UnsupportedVersionException(String.format("Version %d of the Blueprint Format isn't supported, following versions are supported: %s", compound.getInteger("Version"), SUPPORTED_VERSIONS));
		}
		throw new DataCorruptedException(String.format("Couldn't find Version tag within NBTTagCompound %s", compound.toString()));
	}

	protected static final class LoaderV2{
		public static final String KEY_VERSION = "Version";
		public static final String KEY_NAME = "Name";
		public static final String KEY_SIZE = "Size";
		public static final String KEY_CREDITS = "Credits";
		public static final String KEY_DESC = "Description";
		public static final String KEY_MODS = "RequiredMods";
		public static final String KEY_LAYERS = "LayerFiles";
		public static final String KEY_CUSTOM = "Custom";

		public static BlueprintHeader loadBlueprintHeader(NBTTagCompound compound) {
			BlueprintHeader header = new BlueprintHeader();

			//Required
			header.setVersion(compound.getInteger(KEY_VERSION));

			header.setSize(NBTUtil.getPosFromTag(compound.getCompoundTag(KEY_SIZE)));

			NBTTagList requiredMods = compound.getTagList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < requiredMods.tagCount(); i++) header.addRequiredMod(requiredMods.getStringTagAt(i));

			//Optional
			header.setName(compound.getString(KEY_NAME));

			header.setDescription(compound.getString(KEY_DESC));

			NBTTagList credits = compound.getTagList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < credits.tagCount(); i++) header.addCredits(credits.getStringTagAt(i));

			NBTTagList layers = compound.getTagList(KEY_LAYERS, NBT.TAG_STRING);
			for(int i = 0; i < layers.tagCount(); i++) header.addLayerFile(layers.getStringTagAt(i));

			NBTTagCompound custom = compound.getCompoundTag(KEY_CUSTOM);
			for(String key : custom.getKeySet()) header.addCustomData(key, custom.getCompoundTag(key));

			return header;
		}
	}

	protected static final class LoaderV1{
		public static final String KEY_VERSION = "version";
		public static final String KEY_NAME = "name";
		public static final String KEY_SIZE = "size_";
		public static final String KEY_CREDITS = "architects";
		public static final String KEY_MODS = "required_mods";

		public static BlueprintHeader loadBlueprintHeader(NBTTagCompound compound) {
			BlueprintHeader header = new BlueprintHeader();

			//Required
			header.setVersion(compound.getInteger(KEY_VERSION));

			header.setSize(
				new BlockPos(
					compound.getShort(LoaderV1.KEY_SIZE + "x"),
					compound.getShort(LoaderV1.KEY_SIZE + "y"),
					compound.getShort(LoaderV1.KEY_SIZE + "z")
				)
			);

			NBTTagList requiredMods = compound.getTagList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < requiredMods.tagCount(); i++) header.addRequiredMod(requiredMods.getStringTagAt(i));

			//Optional

			header.setName(
				compound.getString(LoaderV1.KEY_NAME)
			);

			NBTTagList credits = compound.getTagList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < credits.tagCount(); i++) header.addCredits(credits.getStringTagAt(i));

			return header;
		}
	}
}
