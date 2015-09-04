package ru.yar.game.Libgdxtutorial.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class Player {
    // Состояние
    public enum State {
        NONE, WALKING, DEAD
    }
    public enum Direction {
        LEFT, RIGHT, UP, DOWN, NONE
    }

    // Скорость движения
    public static final float SPEED = 2f;
    // Размер
    public static final float SIZE = 0.7f;

    // Позиция в мире
    Vector2 position = new Vector2();
    // Используется для вычисления движения
    Vector2 velocity = new Vector2();
    //прямоугольник, в который вписан игрок
    //будет использоваться в будущем для нахождения коллизий (столкновение со стенкой и т.д.
    Rectangle bounds = new Rectangle();
    // Текущее состояние
    State state = State.NONE;

    boolean facingLeft = true;

    public Player(Vector2 position) {
        this.position = position;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    // Обновления движения
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }
}

