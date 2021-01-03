package com.vova_cons.tanks_battle.screens.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.tanks_battle.screens.BaseScreen;
import com.vova_cons.tanks_battle.screens.ScreenType;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Entities;
import com.vova_cons.tanks_battle.screens.game.ecs.systems.MoveSystem;
import com.vova_cons.tanks_battle.screens.game.ecs.systems.PlayerInputSystem;
import com.vova_cons.tanks_battle.screens.game.ecs.systems.RenderSystem;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;
import com.vova_cons.tanks_battle.screens.game.world.GameWorldParser;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;

public class GameScreen extends BaseScreen {
    public static int level = 1;
    private GameWorld world;
    private Engine engine;

    @Override
    public ScreenType getScreenType() {
        return ScreenType.Game;
    }

    @Override
    public void start() {
        parseWorld();
        createEngine();
    }

    private void parseWorld() {
        GameWorldParser parser = new GameWorldParser();
        world = parser.parse(level);
    }

    private void createEngine() {
        engine = new Engine();

        Entity player = Entities.createTank(world.playerX, world.playerY);
        player.add(new Components.Player());
        engine.addEntity(player);

        engine.addSystem(new PlayerInputSystem(world));
        engine.addSystem(new MoveSystem(world));
        engine.addSystem(new RenderSystem(world, batch));
    }

    @Override
    public void update(float delta) {
        engine.update(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ScreensService screensService = ServiceLocator.getService(ScreensService.class);
            screensService.changeScreen(ScreenType.MainMenu);
        }
    }
}
