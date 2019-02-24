package net.dark_roleplay.library.unstable.experimental.variables.wrappers;

public class BooleanWrapper{

	private boolean value;

	public BooleanWrapper(){
		this.value = false;
	}

	public BooleanWrapper(boolean value){
		this.value = value;
	}

	public void set(boolean value){
		this.value = value;
		this.markDirty();
	}

	public boolean get(){
		return this.value;
	}

	public void markDirty() {}
}
