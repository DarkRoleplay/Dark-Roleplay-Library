package net.dark_roleplay.library.unstable.experimental.schematics_new;

import com.google.common.collect.BiMap;

import net.dark_roleplay.library.unstable.experimental.schematics.exception.NotContainedInPalleteException;

public class Pallete<T> {

	private int					palleteSize		= 0;
	private BiMap<T, Integer>	palleteEntries	= null;

	public int getIDForEntry(T entry) throws NotContainedInPalleteException {
		if(this.palleteEntries.containsKey(entry)) {
			return this.palleteEntries.get(entry);
		} else {
			throw new NotContainedInPalleteException(String.format("Couldn't find the instance of %s, within the Pallete", entry.getClass().getName()));
		}
	}

	public int getOrAddIDForEntry(T entry) {
		if(this.palleteEntries.containsKey(entry)) {
			return this.palleteEntries.get(entry);
		} else {
			this.palleteEntries.put(entry, this.palleteSize);
			this.palleteSize += 1;
			return this.palleteSize - 1;
		}
	}

	public T getEntryForID(int id) throws NotContainedInPalleteException {
		T entry = this.palleteEntries.inverse().get(id);
		if(entry == null) throw new NotContainedInPalleteException(String.format("Couldn't find the ID %d, within the Pallete", id));
		return entry;
	}
}
