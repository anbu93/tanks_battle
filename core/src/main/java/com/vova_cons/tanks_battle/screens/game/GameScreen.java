package com.vova_cons.tanks_battle.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.tanks_battle.screens.BaseScreen;
import com.vova_cons.tanks_battle.screens.ScreenType;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;

public class GameScreen extends BaseScreen {
    public static int level = 1;

    @Override
    public ScreenType getScreenType() {
        return ScreenType.Game;
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ScreensService screensService = ServiceLocator.getService(ScreensService.class);
            screensService.changeScreen(ScreenType.MainMenu);
        }
    }
}
