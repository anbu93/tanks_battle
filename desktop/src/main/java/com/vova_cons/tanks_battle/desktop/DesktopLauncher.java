package com.vova_cons.tanks_battle.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vova_cons.tanks_battle.Core;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
	public static void main(String[] args) {
		createApplication();
	}

	private static LwjglApplication createApplication() {
		return new LwjglApplication(new Core(), getDefaultConfiguration());
	}

	private static LwjglApplicationConfiguration getDefaultConfiguration() {
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		configuration.title = "tanks_battle";
		configuration.width = (int) (1920 * 0.75f);
		configuration.height = (int) (1080 * 0.75f);
		configuration.x = 0;
		configuration.y = 0;
		//// This prevents a confusing error that would appear after exiting normally.
		configuration.forceExit = false;
		
		for (int size : new int[] { 128, 64, 32, 16 }) {
			configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
		}
		return configuration;
	}
}