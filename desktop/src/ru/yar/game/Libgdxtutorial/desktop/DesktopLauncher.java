package ru.yar.game.Libgdxtutorial.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.yar.game.Libgdxtutorial.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "libGDX Tutorial";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new MyGame(), config);
	}
}
