package net.dark_roleplay.library.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Created: 26.05.2018
 * Last edit: 26.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: completed
 */
public class CapabilityProvider<C> implements ICapabilitySerializable<INBT> {

	/** The capability this is for */
	private final Capability<C>	capability;
	private final C				instance;

	/**
	 * Pass the Capbility this is for.
	 * 
	 * @param capability
	 */
	public CapabilityProvider(Capability<C> capability) {
		this.capability = capability;
		this.instance = capability.getDefaultInstance();
	}

	public CapabilityProvider(Capability<C> capability, C instance) {
		this.capability = capability;
		this.instance = instance;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		return capability != null && capability == this.capability ? LazyOptional.of(() -> (T) this.instance) : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return capability.getStorage().writeNBT(capability, this.instance, null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		capability.getStorage().readNBT(capability, this.instance, null, nbt);
	}

}
