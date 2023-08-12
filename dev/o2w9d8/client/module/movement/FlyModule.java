package dev.o2w9d8.client.module.movement;

import org.lwjgl.input.Keyboard;

import dev.o2w9d8.client.module.Category;
import dev.o2w9d8.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class FlyModule extends Module {

	public static float flyHackSpeed = 1.3f;
	private double posYBeforeJump;
	private double posYAfterJump;

	public FlyModule() {
		super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
	}
//
//	@Override
//	public void onDisable() {
//		super.onDisable();
//		mc.thePlayer.capabilities.isFlying = false;
//		mc.thePlayer.capabilities.setFlySpeed(0.1f);
//		mc.thePlayer.motionY += 0.0f;
//	}
//
//	@Override
//	public void onUpdate() {
//		this.onUpdate();
//
//		if (this.isToggled()) {
//			mc.thePlayer.capabilities.isFlying = true;
//
//			if (mc.gameSettings.keyBindJump.isPressed()) {
//				mc.thePlayer.motionY *= 0.8f;
//				mc.thePlayer.setAIMoveSpeed(0.8f);
//
//			}
//
//			if (mc.gameSettings.keyBindSneak.isPressed()) {
//				mc.thePlayer.motionY -= 0.5f;
//			}
//
//			if (mc.gameSettings.keyBindForward.isPressed()) {
//				mc.thePlayer.capabilities.setFlySpeed(flyHackSpeed);
//			}
//		}
//	}

	@Override
	public void onDisable() {
		if (this.mc.thePlayer != null) {
			this.mc.thePlayer.capabilities.isFlying = false;
		}
	}

	private final void blocksmc() {
		if (!mc.thePlayer.isJumping) {
			this.posYBeforeJump = mc.thePlayer.posY;
		}
		if (mc.thePlayer.onGround && mc.thePlayer.ticksExisted % 8 == 0) {
			if (this.posYBeforeJump == mc.thePlayer.posY) {
				mc.thePlayer.jump();
				this.posYAfterJump = mc.thePlayer.posY;
			} else if (this.posYAfterJump != this.posYBeforeJump) {
				// empty if block
			}
		}

	}

	@Override
	public void onUpdate() {
		if (!this.isToggled()) {
			return;
		}
		this.mc.thePlayer.capabilities.isFlying = true;
		if (mc.thePlayer.onGround) {
			mc.thePlayer.jump();
			mc.thePlayer.capabilities.setFlySpeed(17.5f);
			mc.thePlayer.motionY += 17.0f;
		}
		super.onUpdate();
	}

}
