package ru.yar.game.Libgdxtutorial.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.yar.game.Libgdxtutorial.controller.WorldController;
import ru.yar.game.Libgdxtutorial.model.Player;
import ru.yar.game.Libgdxtutorial.model.World;
import ru.yar.game.Libgdxtutorial.view.worldRenderer;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class GameScreen implements Screen, InputProcessor {
    private World world;
    private worldRenderer renderer;
    private WorldController controller;

    private int width, height;

    @Override
    public void show() {
        world = new World();
        renderer = new worldRenderer(world);
        controller = new WorldController(world);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        ChangeNavigation(screenX, screenY);
        return false;
    }

    public boolean touchMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.update(delta);
        renderer.render();
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    private void ChangeNavigation(int x, int y) {
        controller.resetWay();
        if (height - y > controller.player.getPosition().y * renderer.ppuY)
            controller.upPressed();

        if (height - y < controller.player.getPosition().y * renderer.ppuY)
            controller.downPressed();

        if (x < controller.player.getPosition().x * renderer.ppuX)
            controller.leftPressed();

        if (x > (controller.player.getPosition().x + Player.SIZE) * renderer.ppuX)
            controller.rightPressed();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;
        ChangeNavigation(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;
        controller.resetWay();
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
