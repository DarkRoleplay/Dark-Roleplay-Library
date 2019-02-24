 package net.dark_roleplay.library.unstable.experimental.data_compression;

public final class IntArrayCompression {

	private IntArrayCompression() {}

	public static int[] compress(int bitCount, int[] data) {
		int mask = (int) ((1L << bitCount) - 1);
		long compressedOffset = 0;
		int[] compressed = new int[(int) Math.ceil((bitCount * data.length) / 32f)];

		for (int i : data) {
			int compressedIndexOffset = (int) (compressedOffset / 32L);
			long compressedBitOffset = compressedOffset % 32L;
			long toWrite = (long) (i & mask) << compressedBitOffset;

			compressed[compressedIndexOffset] |= (int) toWrite;
			int toWriteHigher = (int) (toWrite >>> 32);
			if (toWriteHigher != 0)
				compressed[compressedIndexOffset + 1] |= toWriteHigher;

			compressedOffset += bitCount;
		}
		return compressed;
	}

	public static int[] decompress(int bitCount, int[] compressed, int dataLength) {
		int mask = (int) ((1L << bitCount) - 1);
		long compressedOffset = 0;
		int[] data = new int[dataLength];

		for (int i = 0; i < dataLength; i++) {
			int compressedIndexOffset = (int) (compressedOffset / 32L);
			long compressedBitOffset = compressedOffset % 32L;

			int block = (compressed[compressedIndexOffset] >>> compressedBitOffset) & mask;
			long overflow = compressedBitOffset - 32 + bitCount;
			if (overflow > 0)
				block |= (compressed[compressedIndexOffset + 1] & ((1 << overflow) - 1)) << (bitCount - overflow);
			data[i] = block;

			compressedOffset += bitCount;
		}
		return data;
	}

	public static int getRequiredBitCount(int value) {
		int helper = 1;
		int bitCount = 1;
		while(helper < value) {
			helper *= 2;
			bitCount ++;
		}
		return bitCount;
	}
}
