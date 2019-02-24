package net.dark_roleplay.library.unstable.experimental.zip_helper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class ZipNBTHelper {

	public static NBTTagCompound getTagFromZip(ZipFile file, String entryName) {
		ZipEntry subFile = file.getEntry(entryName);
		if(subFile == null) return null;

		NBTTagCompound tag;
		try {
			tag = CompressedStreamTools.readCompressed(new BufferedInputStream(file.getInputStream(subFile)));
			return tag;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
