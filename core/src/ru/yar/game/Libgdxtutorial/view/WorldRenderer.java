package ru.yar.game.Libgdxtutorial.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Map;

import ru.yar.game.Libgdxtutorial.model.Brick;
import ru.yar.game.Libgdxtutorial.model.Player;
import ru.yar.game.Libgdxtutorial.model.World;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class WorldRenderer {
    public static float CAMERA_WIDTH = 8f;
    public static float CAMERA_HEIGHT = 5f;

    private World world;
    public OrthographicCamera cam;
    ShapeRenderer renderer = new ShapeRenderer();

    // Для отрисовки
    private SpriteBatch spriteBatch;
    // Текстура нашего атласа
    Texture texture;
    // Массив регионов
    public Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();

    public int width;
    public int height;
    public float ppuX;  // пикселей на точку мира по X
    public float ppuY;  // пикселей на точку мира по Y

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    // Установка камеры
    public void setCamera(float x, float y) {
        this.cam.position.set(x, y, 0);
        this.cam.update();
    }

    public WorldRenderer(World world) {
        spriteBatch = new SpriteBatch();
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        // Устанавливаем камеру по центру
        setCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        loadTexture();
    }

    private void loadTexture() {
        // Создание текстуры
        texture = new Texture("images/atlas.png");
        // Получение регионов. Атлас у нас состоит из 4 изображений одинакового размера.
        // Так что выцепить отдельные регионы не составляет проблемы.
        TextureRegion tmp[][] = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight() / 2);
        // Добавляем в массив регионов
        textureRegions.put("player", tmp[0][0]);
        textureRegions.put("brick1", tmp[0][1]);
        textureRegions.put("brick2", tmp[1][0]);
        textureRegions.put("brick3", tmp[1][1]);
    }

    // Основной метод, здесь мы отрисовываем все объекты мира
    public void render() {
        // Начинаем рисовать
        spriteBatch.begin();
        // Рисуем блоки
        drawBricks();
        // Рисуем игрока
        drawPlayer();
        // Заканчиваем отрисовку
        spriteBatch.end();
    }

    // Отрисовка кирпичей
    private void drawBricks() {
        int i = 0;
        for (Brick brick: world.getBricks()) {
            // Ради интереса для отрисовки используем разные изображения (регионы)
            spriteBatch.draw(textureRegions.get("brick" + (i % 3 + 1)),
                    brick.getPosition().x * ppuX, brick.getPosition().y * ppuY,
                    Brick.SIZE * ppuY, Brick.SIZE * ppuY);
            i++;
        }
    }

    // Отрисовка персонажа
    private void drawPlayer() {
        spriteBatch.draw(textureRegions.get("player"),
            world.getPlayer().getPosition().x * ppuX, world.getPlayer().getPosition().y * ppuY,
            Player.SIZE * ppuX, Player.SIZE * ppuY);
    }
}
