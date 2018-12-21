package net.dark_roleplay.library.experimental.blueprints.v2;

public class Constants {

	public static enum CompressionType {
		NONE((byte) 0),
		SIMPLE((byte) 1),
		ADVANCED((byte) 2);

		byte id = -1;

		private CompressionType(byte id) {
			this.id = id;
		}

		public byte getID() {
			return this.id;
		}

		public CompressionType getType(byte id) {
			switch(id){
				case 0:
					return NONE;
				case 1:
					return SIMPLE;
				case 2:
					return ADVANCED;
				default: return null;
			}
		}
	}

	public static enum LoadingState{
		NONE,
		HEADER,
		FULL
	}
}
