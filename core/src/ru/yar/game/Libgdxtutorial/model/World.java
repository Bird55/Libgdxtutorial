package ru.yar.game.Libgdxtutorial.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Map;

import ru.yar.game.Libgdxtutorial.controller.WalkingControl;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class World extends Stage {

    public float ppuX;
    public float ppuY;
    // Выбранный астер
    public Actor selectedActor = null;
    public Map<String, TextureRegion> textureRegions;
    public static float CAMERA_WIDTH = 12f;
    public static float CAMERA_HEIGHT = 8f;

    // Обновление положения объектов
    public void update(float delta) {
        for (Actor actor : this.getActors()) {
            if (actor instanceof Player)
                ((Player)actor).update(delta);
        }
    }

    public World(int width, int height, boolean b, SpriteBatch spriteBatch, Map<String, TextureRegion> textureRegions) {
        super(new ExtendViewport(width, height));
        this.textureRegions = textureRegions;
        ppuX = getHeight() / CAMERA_WIDTH;
        ppuY = getHeight() / CAMERA_HEIGHT;
        // Добавим 2х персонажей
        addActor(new Player(new Vector2(4, 2), this));
        addActor(new Player(new Vector2(2, 4), this));

        // Контрол как актер
        addActor(new WalkingControl(new Vector2(0f, 0f), this));
    }

    public void setPP(float x, float y) {
        ppuX = x;
        ppuY = y;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        // Передвигаем выбранного актёра
        //moveSelected(screenX, screenY);

        return true;
    }

    /**
     *  Передвижение выбранного актера
     *  @param x
     *  @param y
     */
//    private void moveSelected(float x, float y) {
//        if (selectedActor != null && selectedActor instanceof Player) {
//            ((Player)selectedActor).ChangeNavigation(x, this.getHeight() - y);
//        }
//    }

    /**
     *  Сбрасываем текущий вектор направления движения
     */
    public void resetSelected() {
        if (selectedActor != null && selectedActor instanceof Player) {
            ((Player)selectedActor).resetWay();
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        resetSelected();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (selectedActor != null) {
            super.touchDragged(screenX, screenY, pointer);
        }
        return true;
    }

    public Actor hit(float x, float y, boolean touchable) {
        Actor actor = super.hit(x, y, touchable);
        // Если выбрали актера
        if (actor != null && selectedActor instanceof Player) {
            // Запоминаем
            selectedActor = actor;
        }
        return actor;
    }
}
