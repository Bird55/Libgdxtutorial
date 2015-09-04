package ru.yar.game.Libgdxtutorial;

import com.badlogic.gdx.Game;

import ru.yar.game.Libgdxtutorial.screens.GameScreen;

public class MyGame extends Game {
	public GameScreen game;

	@Override
	public void create() {
		game = new GameScreen();
		setScreen(game);
	}
}
