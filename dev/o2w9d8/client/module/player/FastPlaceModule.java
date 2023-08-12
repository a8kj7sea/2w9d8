package dev.o2w9d8.client.module.player;

import org.lwjgl.input.Keyboard;

import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;

public class FastPlaceModule extends Module {

	public FastPlaceModule() {
		super("FastPlace", Keyboard.KEY_B, Category.PLAYER);
	}

	@Override 
	public void onUpdate() {
		if (this.isToggled()) {
			mc.rightClickDelayTimer = 0;
		}
	}

}