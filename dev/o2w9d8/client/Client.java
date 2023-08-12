package dev.o2w9d8.client;

import dev.o2w9d8.client.alt.AltManager;
import dev.o2w9d8.client.extensions.DRP;
import dev.o2w9d8.client.module.ModuleManager;
import net.minecraft.client.multiplayer.ServerList;

import org.lwjgl.opengl.Display;

public class Client {

	public static ModuleManager moduleManager;
	public static AltManager altManager;
	private static DRP discordRpc = new DRP();

	public static void start() {
		moduleManager = new ModuleManager();
		altManager = new AltManager();
		displayStuff();
		discordRpc.start();
	}

	private static void displayStuff() {
		Display.setTitle("2w9d8 Client | 1.8.8-Beta");
	}

	public static DRP getDiscordRpc() {
		return discordRpc;
	}

}
