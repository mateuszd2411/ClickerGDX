package com.mygdx.clicker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.clicker.ClickerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = ClickerGame.GAME_NAME;
		config.width = ClickerGame.WIDTH;
		config.height = ClickerGame.HEIGHT;
		config.resizable = false; 		/// window cant change

		new LwjglApplication(new ClickerGame(), config);
	}
}
