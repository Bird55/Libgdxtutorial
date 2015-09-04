package ru.yar.game.Libgdxtutorial.controller;

import java.util.HashMap;
import java.util.Map;

import ru.yar.game.Libgdxtutorial.model.Player;
import ru.yar.game.Libgdxtutorial.model.World;

/**
 * Created by bird on 04.09.2015.
 *
 */
public class WorldController {
    // Направления движения
    enum Keys {
        LEFT, RIGHT, UP, DOWN
    }

    // Игрок
    public Player player;

    //Куда движемся... Игрок может двигаться одновременно по 2-м направлениям
    static Map<Keys,Boolean> keys = new HashMap<WorldController.Keys, Boolean>();

    // Первоначально стоим
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
    }

    public WorldController(World world) {
        this.player = world.getPlayer();
    }

    // Флаг устанавливаем, что движемся влево
    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    // Флаг устанавливаем, что движемся вправо
    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    // Флаг устанавливаем, что движемся вверх
    public void upPressed() {
        keys.get(keys.put(Keys.UP, true));
    }

    // Флаг устанавливаем, что движемся вниз
    public void downPressed() {
        keys.get(keys.put(Keys.DOWN, true));
    }

    // Освобождаем флаги
    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    public void upReleased() {
        keys.get(keys.put(Keys.UP, false));
    }

    public void downReleased() {
        keys.get(keys.put(Keys.DOWN, false));
    }

    // Главный метод класса... Обновляем состояние объектов здесь
    public void update(float delta) {
        processInput();
        player.update(delta);
    }

    public void resetWay() {
        rightReleased();
        leftReleased();
        upReleased();
        downReleased();
    }

    // В зависимости от выбранного направления движения
    // выставляем новое направление движения для персонажа
    private void processInput() {
        if (keys.get(Keys.LEFT))
            player.getVelocity().x = -Player.SPEED;

        if (keys.get(Keys.RIGHT))
            player.getVelocity().x = Player.SPEED;

        if (keys.get(Keys.UP))
            player.getVelocity().y = Player.SPEED;

        if (keys.get(Keys.DOWN))
            player.getVelocity().y = -Player.SPEED;

        // Если не выбрано направление, то стоим на месте
        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && (!keys.get(Keys.RIGHT))))
            player.getVelocity().x = 0;
        if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
                (!keys.get(Keys.UP) && (!keys.get(Keys.DOWN))))
            player.getVelocity().y = 0;
    }
}
