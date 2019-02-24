package net.dark_roleplay.library.unstable.experimental.blueprints.v2.exception;

public class DataCorruptedException extends RuntimeException{

	public DataCorruptedException() {
		super();
	}
	
	public DataCorruptedException(String message) {
		super(message);
	}
}