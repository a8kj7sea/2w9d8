package dev.o2w9d8.client.module;

import java.util.ArrayList;
import java.util.List;

import dev.o2w9d8.client.module.crashing.*;
import dev.o2w9d8.client.module.movement.*;
import dev.o2w9d8.client.module.player.FastPlaceModule;

public class ModuleManager {
	private static List<Module> modules;

	public static List<Module> getModules() {
		return modules;
	}

	public ModuleManager() {
		modules = new ArrayList<>();

		// register combat module
		

		// register movement modules
		addModule(new FlyModule());
		// register player modules
		addModule(new FastPlaceModule());

		// register crashing modules

		addModule(new DiggingCModule());
		addModule(new KeepAliveCModule());
		addModule(new SneakingCModule());
		addModule(new SpammerModule());
		addModule(new StressCModule());
	}

	public static void addModule(Module module) {
		modules.add(module);
	}

	public static void removeModule(Module module) {
		modules.remove(module);
	}

	public static void onUpdate() {
		for (Module module : modules)
			module.onUpdate();
	}

	public static void onRender() {
		for (Module module : modules)
			module.onRender();
	}

	public static void onKey(int keycode) {
		for (Module module : modules) {
			if (module.getKey() == keycode)
				module.toggle();
		}
	}
}
