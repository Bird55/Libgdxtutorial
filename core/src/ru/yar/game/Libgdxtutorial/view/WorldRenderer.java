package ru.yar.game.Libgdxtutorial.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

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
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        // Устанавливаем камеру по центру
        setCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
    }

    // Основной метод, здесь мы отрисовываем все объекты мира
    public void render() {
        drawBricks();
        drawPlayer();
    }

    // Отрисовка кирпичей
    private void drawBricks() {
        renderer.setProjectionMatrix(cam.combined);
        // Тип устанавливаем... В данном случае с заливкой
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        // Прогоняем блоки
        for (Brick brick: world.getBricks()) {
            Rectangle rect = brick.getBounds();
            float x1 = brick.getPosition().x + rect.x;
            float y1 = brick.getPosition().y + rect.y;
            renderer.setColor(0, 0, 0, 1);
            // Рисуем блок
            renderer.rect(x1, y1, rect.width, rect.height);
        }

        renderer.end();
    }

    // Отрисовка персонажа по аналогии
    private void drawPlayer() {
        renderer.setProjectionMatrix(cam.combined);
        Player player = world.getPlayer();
        renderer.begin(ShapeRenderer.ShapeType.Line);

        Rectangle rect = player.getBounds();
        float x1 = player.getPosition().x + rect.x;
        float y1 = player.getPosition().y + rect.y;
        renderer.setColor(1, 0, 0, 1);
        renderer.rect(x1, y1, rect.width, rect.height);
        renderer.end();
    }
}
