package net.republicmc.bookhelp;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;


public class BookUtil {
	@SuppressWarnings("deprecation")
	public static void showBook(ItemStack book, Player p) {

		ItemStack currentItem = p.getItemInHand();
		p.setItemInHand(book);
		
		try {
			PacketContainer pc = ProtocolLibrary.getProtocolManager().
		    		createPacket(PacketType.Play.Server.CUSTOM_PAYLOAD);
			pc.getModifier().writeDefaults();
			ByteBuf bf = Unpooled.buffer(256); // note 1
			bf.setByte(0, (byte)0); // note 2
			bf.writerIndex(1);
			pc.getModifier().write(1, MinecraftReflection.getPacketDataSerializer(bf));
			pc.getStrings().write(0, "MC|BOpen");
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, pc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p.setItemInHand(currentItem);
	}
}
