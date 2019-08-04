package net.dark_roleplay.library.unstable.experimental.schematics;

import java.awt.image.BufferedImage;

import net.dark_roleplay.library.unstable.experimental.schematics.api.SchematicHeader;

public class Blueprint {

	private SchematicHeader			header			= null;
	private Constants.LoadingState	loadingState	= Constants.LoadingState.NONE;

	private BufferedImage			previewImage	= null;

	public Blueprint() {}

	public Blueprint(SchematicHeader header) {
		this(header, null);
	}

	public Blueprint(SchematicHeader header, BufferedImage image) {
		this.loadingState = Constants.LoadingState.HEADER;
		this.header = header;
		this.previewImage = image;
	}

	public SchematicHeader getHeader() { return this.header; }

	public Constants.LoadingState getLoadingState() { return this.loadingState; }
}
