package com.vova_cons.tanks_battle.screens.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.tanks_battle.screens.BaseScreen;
import com.vova_cons.tanks_battle.screens.ScreenType;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Entities;
import com.vova_cons.tanks_battle.screens.game.ecs.systems.*;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;
import com.vova_cons.tanks_battle.screens.game.world.GameWorldParser;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;
import com.vova_cons.tanks_battle.services.settings.SettingsService;
import com.vova_cons.tanks_battle.utils.RandomUtils;

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

        Entity player = Entities.createTank(world.playerX, world.playerY, Teams.PLAYER);
        player.add(new Components.Player());
        engine.addEntity(player);

        for(int i = 0; i < 5; i++) {
            engine.addEntity(Entities.createTank(
                    RandomUtils.random.nextFloat() * world.map.width,
                    RandomUtils.random.nextFloat() * world.map.height,
                    Teams.ENEMY));
        }

        SettingsService settingsService = ServiceLocator.getService(SettingsService.class);
        engine.addSystem(new PlayerInputSystem(settingsService.getPlayer1Keys(), player));
        engine.addSystem(new FireCooldownSystem(world));
        engine.addSystem(new FireSystem(world));
        engine.addSystem(new MoveSystem(world));
        engine.addSystem(new DamageSystem(world));
        engine.addSystem(new DeathSystem(world));
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
