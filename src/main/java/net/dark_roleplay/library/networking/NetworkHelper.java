package net.dark_roleplay.library.networking;

import net.dark_roleplay.library.DRPLibrary;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHelper {

	private static SimpleChannel CURRENT_CHANNEL = null;
	private static int CURRENT_PACKET_ID = 0;
	
	/**
	 * Used to set the SimpleChannel, to which {@link SimplePacket} are registered
	 * when using {@link NetworkHelper#registerPacket(Class, SimplePacket)}
	 * 
	 * @param channel
	 */
	public static void initChannel(SimpleChannel channel) {
		CURRENT_CHANNEL = channel;
		CURRENT_PACKET_ID = 0;
	}
	
	/**
	 * Clears your SimpleChannel so other mods don't accidently register their Packets to your SimpleChannel
	 */
	public static void clearChannel() {
		CURRENT_CHANNEL = null;
	}
	
	/**
	 * Warning: 
	 * Changing the Order in which your Packets are registered, will also change their IDs.
	 * this may result in crashes on both, Client and Dedicated Server.
	 * Therefore keep in mind that you need to update the protocol version,
	 * of your channel when this is changed.
	 *  
	 * @param packetClass the Class of your Packet
	 * @param packet a basic instance, this instance doesn't have 
	 */
	public static <T extends SimplePacket<T>> void registerPacket(Class<T> packetClass) {
		if(CURRENT_CHANNEL == null) {
			DRPLibrary.LOGGER.error("Tried to register a Packet without initializing a SimpleChannel");
		}else {
			CURRENT_CHANNEL.<T>registerMessage(
					CURRENT_PACKET_ID++,
					packetClass,
					(msg, packetBuffer) -> msg.encode(msg, packetBuffer),
					(packetBuffer) -> {
						try {
							return packetClass.newInstance().decode(packetBuffer);
						} catch (InstantiationException | IllegalAccessException e) {
							DRPLibrary.LOGGER.error("Unable to create an instance of the packet class. No Default constructor found", e);
							return null;
						}
					},
					(msg, contextSupplier) -> msg.onMessage(msg,  contextSupplier)
			);
		}
	}
}