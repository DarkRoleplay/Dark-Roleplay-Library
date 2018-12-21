package net.dark_roleplay.library.experimental.blueprints.v2;

import java.awt.image.BufferedImage;

import net.dark_roleplay.library.experimental.blueprints.v2.header.BlueprintHeader;

public class Blueprint {

	private LoadingState loadingState = LoadingState.NONE;

	private BlueprintHeader header = null;
	private BufferedImage previewImage = null;

	public Blueprint() {}

	public Blueprint(BlueprintHeader header) {
		this(header, null);
	}

	public Blueprint(BlueprintHeader header, BufferedImage image) {
		this.loadingState = LoadingState.HEADER;
		this.header = header;
		this.previewImage = image;
	}

	public BlueprintHeader getHeader() {
		return this.header;
	}

	public LoadingState getLoadingState() {
		return this.loadingState;
	}

	public static enum LoadingState{
		NONE,
		HEADER,
		FULL
	}
}
