package dev.o2w9d8.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

public class Module {

	public Minecraft getMc() {
		return this.mc;
	}

	public String getName() {
		return this.name;
	}

	public int getKey() {
		return this.key;
	}

	public boolean isToggled() {
		return this.toggled;
	}

	public Category getCategory() {
		return this.category;
	}

	public void toggle() {
		this.toggled = !this.toggled;
		if (this.toggled) {
			onEnable();
		} else {
			onDisable();
		}
	}

	protected Minecraft mc = Minecraft.getMinecraft();

	private String name;

	private int key;

	private boolean toggled;

	private Category category;

	public Module(String name, int key, Category category) {
		this.name = name;
		this.key = key;
		this.toggled = false;
		this.category = category;
	}

	public void onEnable() {
	}

	public void onDisable() {
	}

	public void onUpdate() {
	}

	public void onRender() {
	}

	@SuppressWarnings("rawtypes")
	protected void sendPacket(Packet p) {
		mc.thePlayer.sendQueue.addToSendQueue(p);
	}
}
