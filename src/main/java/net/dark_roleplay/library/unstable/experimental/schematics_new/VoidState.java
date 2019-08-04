package net.dark_roleplay.library.unstable.experimental.schematics_new;

public class VoidState {

	private VoidType[] voidTypes = {VoidType.NONE};

	public VoidState() {}

	public VoidState(VoidType... voidTypes) {
		this.voidTypes = voidTypes;
	}

	public static enum VoidType {
		SOLID, // Will replace Solid
		AIR, // Will replace Air
		FLUID, // Will replace Fluids
		NONE; // Won't replace anything (vanilla structure void)
	}

}