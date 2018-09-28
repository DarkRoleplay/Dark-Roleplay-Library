package net.dark_roleplay.library.experimental.blocks.behaviors;


import java.util.List;

public abstract class MultiBehavior<T extends IBlockBehavior> implements IBlockBehavior{

	protected List<T> behaviors;

	public MultiBehavior(T... behaviors) {
		for(T behavior : behaviors) {
			this.behaviors.add(behavior);
		}
	}

	public T addBehavior(T... behaviors) {

		for(T behavior : behaviors) {
			this.behaviors.add(behavior);
		}

		return (T) this;
	}

}
