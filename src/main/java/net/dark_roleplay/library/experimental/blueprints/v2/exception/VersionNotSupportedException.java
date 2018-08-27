package net.dark_roleplay.library.experimental.blueprints.v2.exception;

public class VersionNotSupportedException extends RuntimeException{

	public VersionNotSupportedException() {
		super();
	}
	
	public VersionNotSupportedException(String message) {
		super(message);
	}
}
