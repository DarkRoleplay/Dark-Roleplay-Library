package net.dark_roleplay.library.unstable.experimental.variables.wrappers;

public class StringWrapper {

	private String value;

	public StringWrapper(){
		this.value = "";
	}

	public StringWrapper(String value){
		this.value = value;
	}

	public void set(String value){
		this.value = value;
		this.markDirty();
	}

	public String get(){
		return this.value;
	}

	public void markDirty() {}
}
