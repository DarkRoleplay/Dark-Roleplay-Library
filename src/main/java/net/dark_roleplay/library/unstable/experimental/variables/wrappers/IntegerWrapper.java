package net.dark_roleplay.library.unstable.experimental.variables.wrappers;

public class IntegerWrapper {

	private int value;

	private int min = Integer.MIN_VALUE;
	private int max = Integer.MAX_VALUE;

	public IntegerWrapper(){
		this.value = 0;
	}

	public IntegerWrapper(int value){
		this.value = value;
	}

	public IntegerWrapper(int value, int min, int max) {
		this.value = value;
		this.min = min;
		this.max = max;
	}

	public void add(int amount){
		this.value = Math.min(Math.max(this.value, this.min), this.max);
	}

	public void remove(int amount){
		this.add(-amount);
	}

	public void set(int value){
		this.value = Math.min(Math.max(value, this.min), this.max);
		this.markDirty();
	}

	public int get(){
		return this.value;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMin() {
		return this.min;
	}


	public void setMax(int max) {
		this.max = max;
	}

	public int getMax() {
		return this.max;
	}

	public void markDirty() {}
}
