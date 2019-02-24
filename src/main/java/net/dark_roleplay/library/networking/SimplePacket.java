package net.dark_roleplay.library.networking;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * It is recommended to register Packets of this Instance using {@link NetworkHelper}
 *
 * @param <T> this should be your implementation of this Class
 */
public abstract class SimplePacket<T>{
	//TODO finish and cleanup JavaDoc
	
	/**
	 * Use this to encode your message into a the passed in PacketBuffer
	 * @param message an instance of your packet implementation
	 * @param packet 
	 */
	public abstract void encode(T message, PacketBuffer packet);
	
	
	public abstract T decode(PacketBuffer packet);
	
	public abstract void onMessage(T message, Supplier<NetworkEvent.Context> context);
}