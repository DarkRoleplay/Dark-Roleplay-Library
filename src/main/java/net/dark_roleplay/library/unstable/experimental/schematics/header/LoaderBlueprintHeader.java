package net.dark_roleplay.library.unstable.experimental.schematics.header;

import net.dark_roleplay.library.unstable.experimental.schematics.api.SchematicHeader;
import net.dark_roleplay.library.unstable.experimental.schematics.exception.DataCorruptedException;
import net.dark_roleplay.library.unstable.experimental.schematics.exception.UnsupportedVersionException;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public final class LoaderBlueprintHeader {

	private static final String SUPPORTED_VERSIONS = "[1,2]";

	private LoaderBlueprintHeader() {}

	/**
	 * Loads a BlueprintHeader from a CompoundNBT,<br>
	 * This supports versions 1 - 2
	 * 
	 * @param compound The Compound containing the Header Data
	 * @return The BlueprintHeader generated from the input CompoundNBT
	 * @throws DataCorruptedException      When no Version can be found
	 * @throws UnsupportedVersionException when the contained Version in the
	 *                                     CompoundNBT isn't supported by the
	 *                                     Loader
	 */
	public static SchematicHeader loadBlueprintHeader(CompoundNBT compound) throws UnsupportedVersionException {
		if(compound.contains(LoaderV2.KEY_VERSION) && compound.getInt(LoaderV2.KEY_VERSION) == 2) {
			return LoaderV2.loadBlueprintHeader(compound);
		} else if(compound.contains(LoaderV1.KEY_VERSION)) {
			return LoaderV1.loadBlueprintHeader(compound);
		} else if(compound.contains("Version")) {
			throw new UnsupportedVersionException(String.format("Version %d of the Blueprint Format isn't supported, following versions are supported: %s", compound.getInt("Version"), SUPPORTED_VERSIONS));
		}
		throw new DataCorruptedException(String.format("Couldn't find Version tag within CompoundNBT %s", compound.toString()));
	}

	protected static final class LoaderV2 {

		public static final String	KEY_VERSION	= "Version";
		public static final String	KEY_NAME	= "Name";
		public static final String	KEY_SIZE	= "Size";
		public static final String	KEY_CREDITS	= "Credits";
		public static final String	KEY_DESC	= "Description";
		public static final String	KEY_MODS	= "RequiredMods";
		public static final String	KEY_LAYERS	= "LayerFiles";
		public static final String	KEY_CUSTOM	= "Custom";

		public static SchematicHeader loadBlueprintHeader(CompoundNBT compound) {
			SchematicHeader header = new SchematicHeader();

			// Required
			header.setVersion(compound.getInt(KEY_VERSION));

			header.setSize(NBTUtil.readBlockPos(compound.getCompound(KEY_SIZE)));

			ListNBT requiredMods = compound.getList(KEY_MODS, NBT.TAG_STRING);
			for(int i = 0; i < requiredMods.size(); i++ )
				header.addRequiredMod(requiredMods.getString(i));

			// Optional
			header.setName(compound.getString(KEY_NAME));

			header.setDescription(compound.getString(KEY_DESC));

			ListNBT credits = compound.getList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < credits.size(); i++ )
				header.addCredits(credits.getString(i));

			ListNBT layers = compound.getList(KEY_LAYERS, NBT.TAG_STRING);
			for(int i = 0; i < layers.size(); i++ )
				header.addLayerFile(layers.getString(i));

			CompoundNBT custom = compound.getCompound(KEY_CUSTOM);
			for(String key : custom.keySet())
				header.addCustomData(key, custom.getCompound(key));

			return header;
		}
	}

	protected static final class LoaderV1 {

		public static final String	KEY_VERSION	= "version";
		public static final String	KEY_NAME	= "name";
		public static final String	KEY_SIZE	= "size_";
		public static final String	KEY_CREDITS	= "architects";
		public static final String	KEY_MODS	= "required_mods";

		public static SchematicHeader loadBlueprintHeader(CompoundNBT compound) {
			SchematicHeader header = new SchematicHeader();

			// Required
			header.setVersion(compound.getInt(KEY_VERSION));

			header.setSize(new BlockPos(compound.getShort(LoaderV1.KEY_SIZE + "x"), compound.getShort(LoaderV1.KEY_SIZE + "y"), compound.getShort(LoaderV1.KEY_SIZE + "z")));

			ListNBT requiredMods = compound.getList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < requiredMods.size(); i++ )
				header.addRequiredMod(requiredMods.getString(i));

			// Optional

			header.setName(compound.getString(LoaderV1.KEY_NAME));

			ListNBT credits = compound.getList(KEY_CREDITS, NBT.TAG_STRING);
			for(int i = 0; i < credits.size(); i++ )
				header.addCredits(credits.getString(i));

			return header;
		}
	}
}