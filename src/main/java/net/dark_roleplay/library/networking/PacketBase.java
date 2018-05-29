package net.dark_roleplay.library.networking;

import org.apache.logging.log4j.LogManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created: 29.05.2018
 * Last edit: 29.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: completed
 */
public abstract class PacketBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ> {

	@Override
	public final REQ onMessage(REQ message, MessageContext ctx) {
		if(ctx.side == Side.SERVER) {
			handleServerSide(message, ctx.getServerHandler().player);
		} else {
			handleClientSide(message, null);
		}
		return null;
	}

	/**
	 * Called when an Instance of this Packet is received on the Client
	 * @param message
	 * @param player
	 */
	@SideOnly(Side.CLIENT)
	public abstract void handleClientSide(REQ message, EntityPlayer player);
	
	/**
	 * Called when an Instance of this Packet is received on the Server9
	 * @param message
	 * @param player
	 */
	public abstract void handleServerSide(REQ message, EntityPlayer player);
	
	/**
	 * Has default handling if the Packet is received on the Client,
	 * therefore this should be used if the Packet should just be receiveable by the Server.
	 *
	 * @param <REQ>
	 */
	public static abstract class Server<REQ extends IMessage> extends PacketBase<REQ>{
		@Override
		@SideOnly(Side.CLIENT)
		public final void handleClientSide(REQ message, EntityPlayer player){
			LogManager.getLogger().error("Received a packet that was ment to be only used to send Data form Client to Server!");
			LogManager.getLogger().error("False Packet: "  + message.getClass().getSimpleName());
		}
	}
	
	/**
	 * Has default handling if the Packet is received on the Server,
	 * therefore this should be used if the Packet should just be receiveable by the Client.
	 *
	 * @param <REQ>
	 */
	public static abstract class Client<REQ extends IMessage> extends PacketBase<REQ>{
		@Override
		public final void handleServerSide(REQ message, EntityPlayer player){
			LogManager.getLogger().error("Received a packet that was ment to be only used to send Data form Server to Client!");
			LogManager.getLogger().error("False Packet: "  + message.getClass().getSimpleName());
		}
	}
}
