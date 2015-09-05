package ru.yar.game.Libgdxtutorial.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

import ru.yar.game.Libgdxtutorial.model.World;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class GameScreen implements Screen, InputProcessor {

    public OrthographicCamera cam;
    public World world;
    private SpriteBatch spriteBatch;
    Texture texture;
    public Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();

    public int width;
    public int height;

    @Override
    public void show() {
        World.CAMERA_WIDTH =  World.CAMERA_HEIGHT * Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        cam = new OrthographicCamera(World.CAMERA_WIDTH, World.CAMERA_HEIGHT);
        setCamera(World.CAMERA_WIDTH / 2f, World.CAMERA_HEIGHT / 2f);
        spriteBatch = new SpriteBatch();
        loadTextures();
        world = new World(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, spriteBatch, textureRegions);

        Gdx.input.setInputProcessor(this);
    }

    private void loadTextures() {
        texture = new Texture(Gdx.files.internal("images/atlas.png"));
        TextureRegion[][] tmpLeftRight = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight() / 2);
        TextureRegion left2[][] = tmpLeftRight[0][0].split(tmpLeftRight[0][0].getRegionWidth() / 2, tmpLeftRight[0][0].getRegionHeight());
        TextureRegion left[][] = left2[0][0].split(left2[0][0].getRegionWidth()/4, left2[0][0].getRegionHeight()/8);

        textureRegions.put("player", left[0][0]);
        textureRegions.put("brick1", left[0][1]);
        textureRegions.put("brick2", left[1][0]);
        textureRegions.put("brick3", left[1][1]);

        textureRegions.put("navigation_arrows", tmpLeftRight[0][1]);
        TextureRegion rightbot[][] = tmpLeftRight[1][1].split(tmpLeftRight[1][1].getRegionWidth()/2,tmpLeftRight[1][1].getRegionHeight()/2);
        textureRegions.put("khob", rightbot[0][1]);
    }

    public void setCamera(float x, float y) {
        this.cam.position.set(x, y, 1);
        this.cam.update();
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
        this.width = width;
        this.height = height;
        world.getViewport().update(width, height, true);
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
        Gdx.gl.glClearColor(232F/255, 232F/255, 232F/255, 232F/255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update(delta);
        world.draw();
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
