package net.dark_roleplay.library.experimental.blueprints.v2;

import java.awt.image.BufferedImage;

import net.dark_roleplay.library.experimental.blueprints.v2.header.BlueprintHeader;

public class Blueprint {

	private Constants.LoadingState loadingState = Constants.LoadingState.NONE;

	private BlueprintHeader header = null;
	private BufferedImage previewImage = null;

	public Blueprint() {}

	public Blueprint(BlueprintHeader header) {
		this(header, null);
	}

	public Blueprint(BlueprintHeader header, BufferedImage image) {
		this.loadingState = Constants.LoadingState.HEADER;
		this.header = header;
		this.previewImage = image;
	}

	public BlueprintHeader getHeader() {
		return this.header;
	}

	public Constants.LoadingState getLoadingState() {
		return this.loadingState;
	}
}
