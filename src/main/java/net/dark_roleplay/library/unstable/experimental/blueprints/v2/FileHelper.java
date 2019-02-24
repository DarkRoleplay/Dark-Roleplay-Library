package net.dark_roleplay.library.unstable.experimental.blueprints.v2;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import net.dark_roleplay.library.unstable.experimental.blueprints.v2.exception.UnsupportedVersionException;
import net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.BlueprintHeader;
import net.dark_roleplay.library.unstable.experimental.blueprints.v2.header.LoaderBlueprintHeader;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class FileHelper {

	private static final String HEADER_NAME = "header.nbt";
	private static final String GLOBAL_PALLETE_NAME = "global_pallete.nbt";
	private static final String BASE_LAYER_NAME = "layer_base.nbt";
	private static final String PREVIEW_NAME = "preview.png";
	private static final String CUSTOM_NAME = "custom";
	private static final String LAYERS_NAME = "layers";

	public static void writeToFile(File file, NBTTagCompound comp) {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
			writeToStream(os, comp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NBTTagCompound readFromFile(File file) {
		if (!file.exists())
			return null;
		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			return readFromStream(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// TODO Implement version for full Blueprint and create writers
	public static Blueprint loadBlueprintHeader(File file, boolean loadPreviewImage) {
		URI p = file.toURI();
		URI uri = URI.create("jar:" + p);

		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
			InputStream headerInput = new BufferedInputStream(Files.newInputStream(new File(HEADER_NAME).toPath()));

			NBTTagCompound headerComp = CompressedStreamTools.readCompressed(headerInput);
			BlueprintHeader header = LoaderBlueprintHeader.loadBlueprintHeader(headerComp);

			if (loadPreviewImage && Files.exists(new File(PREVIEW_NAME).toPath())) {
				BufferedImage previewImage = ImageIO.read(Files.newInputStream(new File(PREVIEW_NAME).toPath()));
				return new Blueprint(header, previewImage);
			}

			return new Blueprint(header);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedVersionException e) {
			e.printStackTrace();
		}
		return new Blueprint();
	}

	public static Blueprint loadBlueprint(File file, boolean loadPreviewImage, String... customNBTFiles) {
		URI p = file.toURI();
		URI uri = URI.create("jar:" + p);

		BlueprintHeader header = null;
		BufferedImage previewImage = null;
		Map<String, NBTTagCompound> customFiles = new HashMap<String, NBTTagCompound>();


		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
			InputStream headerInput = new BufferedInputStream(Files.newInputStream(new File(HEADER_NAME).toPath()));

			//Load Header
			NBTTagCompound headerComp = CompressedStreamTools.readCompressed(headerInput);
			header = LoaderBlueprintHeader.loadBlueprintHeader(headerComp);

			//Load Preview Image
			if (loadPreviewImage && Files.exists(new File(PREVIEW_NAME).toPath())) {
				previewImage = ImageIO.read(Files.newInputStream(new File(PREVIEW_NAME).toPath()));
			}

			Stream<Path> layerFiles = Files.list(new File(LAYERS_NAME).toPath());
			layerFiles.forEach(path -> {
				//TODO Load layers
			});

			//Load customNBTFiles
			for(String customNBTFile : customNBTFiles) {
				InputStream customNBTStream = new BufferedInputStream(Files.newInputStream(new File(CUSTOM_NAME + "/" + customNBTFile + ".nbt").toPath()));
				NBTTagCompound customComp = CompressedStreamTools.readCompressed(customNBTStream);
				customFiles.put(customNBTFile, customComp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedVersionException e) {
			e.printStackTrace();
		}
		return new Blueprint();
	}

	private static void writeToStream(OutputStream os, NBTTagCompound comp) {
		try {
			CompressedStreamTools.writeCompressed(comp, os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static NBTTagCompound readFromStream(InputStream is) {
		NBTTagCompound tag;
		try {
			tag = CompressedStreamTools.readCompressed(is);
			return tag;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
