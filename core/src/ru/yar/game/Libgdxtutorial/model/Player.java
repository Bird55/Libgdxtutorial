package ru.yar.game.Libgdxtutorial.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class Player extends Actor {
    // Состояние
    public enum State {
        NONE, WALKING, DEAD
    }

    public static enum Direction {
        LEFT, RIGHT, UP, DOWN, NONE
    }

    // Скорость движения
    public static final float SPEED = 2f;
    // Размер
    public static final float SIZE = 0.7f;

    World world;
    // Позиция в мире
    Vector2 position = new Vector2();
    // Используется для вычисления движения
    Vector2 velocity = new Vector2();
    // Текущее состояние
    State state = State.NONE;

    boolean	facingLeft = true;

    public Player(Vector2 position, World world) {
        this.position = position;
        this.world = world;

        setWidth(SIZE * world.ppuX);
        setHeight(SIZE * world.ppuY);
        setX(position.x * world.ppuX);
        setY(position.y * world.ppuY);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }


    // Обновления движения
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        setX(position.x * world.ppuX);
        setY(position.y * world.ppuY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.equals(world.selectedActor)) {
            batch.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        }
        batch.draw(world.textureRegions.get("player"), getX(), getY(), getWidth(), getHeight());
        batch.setColor(1, 1, 1, 1);
    }

    public Actor hit(float x, float y, boolean touchable) {
        // Процедура проверки. Если точка в прямоугольнике актера, возвращаем актера
        return x > 0 && x < getWidth() && y> 0 && y < getHeight()?this:null;
    }

    public void ChangeNavigation(float x, float y){
        resetWay();
        if(y > getY()) upPressed();

        if(y <  getY()) downPressed();

        if ( x< getX()) leftPressed();

        if (x> (getPosition().x +SIZE)* world.ppuX) rightPressed();

        processInput();
    }


    public void resetWay(){
        rightReleased();
        leftReleased();
        downReleased();
        upReleased();

        getVelocity().x = 0;
        getVelocity().y  = 0;
    }

    private void processInput() {
        if (direction.get(Keys.LEFT))
            getVelocity().x = -Player.SPEED;


        if (direction.get(Keys.RIGHT))
            getVelocity().x =Player.SPEED;

        if (direction.get(Keys.UP))
            getVelocity().y = Player.SPEED;


        if (direction.get(Keys.DOWN))
            getVelocity().y = -Player.SPEED;
        if ((direction.get(Keys.LEFT) && direction.get(Keys.RIGHT)) ||
                (!direction.get(Keys.LEFT) && (!direction.get(Keys.RIGHT))))
            getVelocity().x = 0;

        if ((direction.get(Keys.UP) && direction.get(Keys.DOWN)) ||
                (!direction.get(Keys.UP) && (!direction.get(Keys.DOWN))))
            getVelocity().y = 0;
    }

    enum Keys {
        LEFT, RIGHT, UP, DOWN
    }

    static Map<Keys, Boolean> direction = new HashMap<Keys, Boolean>();
    static {
        direction.put(Keys.LEFT, false);
        direction.put(Keys.RIGHT, false);
        direction.put(Keys.UP, false);
        direction.put(Keys.DOWN, false);
    }



    public void leftPressed() {
        direction.get(direction.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        direction.get(direction.put(Keys.RIGHT, true));
    }

    public void upPressed() {
        direction.get(direction.put(Keys.UP, true));
    }

    public void downPressed() {
        direction.get(direction.put(Keys.DOWN, true));
    }

    public void leftReleased() {
        direction.get(direction.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        direction.get(direction.put(Keys.RIGHT, false));
    }

    public void upReleased() {
        direction.get(direction.put(Keys.UP, false));
    }

    public void downReleased() {
        direction.get(direction.put(Keys.DOWN, false));
    }
}

