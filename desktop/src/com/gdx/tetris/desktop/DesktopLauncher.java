package com.gdx.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.tetris.TetrisGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tetris on Java";
		config.width = 1920;
		config.height = 1080;
		//config.foregroundFPS = 60;
		config.backgroundFPS = 15;
		config.fullscreen = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new TetrisGDX(), config);
	}
}
