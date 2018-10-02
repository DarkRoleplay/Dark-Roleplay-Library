package net.dark_roleplay.library.experimental.variables.wrappers;

public class FloatWrapper {

	private float value;

	private float min = Float.MIN_VALUE;
	private float max = Float.MAX_VALUE;

	public FloatWrapper(){
		this.value = 0;
	}

	public FloatWrapper(float value){
		this.value = value;
	}

	public FloatWrapper(float value, float min, float max) {
		this.value = value;
		this.min = min;
		this.max = max;
	}

	public void add(float amount){
		this.value += amount;
		this.value = Math.min(Math.max(this.value, this.min), this.max);
	}

	public void remove(float amount){
		this.add(-amount);
	}

	public void set(float value){
		this.value = Math.min(Math.max(value, this.min), this.max);
		this.markDirty();
	}

	public float get(){
		return this.value;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMin() {
		return this.min;
	}


	public void setMax(float max) {
		this.max = max;
	}

	public float getMax() {
		return this.max;
	}

	public void markDirty() {}
}
