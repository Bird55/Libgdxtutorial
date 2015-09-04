package ru.yar.game.Libgdxtutorial.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class Brick {
    // Размер объекта
    static  final float SIZE = 0.8f;
    // Кординаты
    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Brick(Vector2 position) {
        this.position = position;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
