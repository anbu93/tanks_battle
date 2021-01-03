package com.vova_cons.tanks_battle.services;

import com.badlogic.gdx.utils.ObjectMap;
import com.vova_cons.tanks_battle.Core;
import com.vova_cons.tanks_battle.screens.BaseScreen;
import com.vova_cons.tanks_battle.screens.ScreenType;

public class ScreensService implements Service {
    private final Core core;
    private ObjectMap<ScreenType, BaseScreen> screensMap = new ObjectMap<>();

    //region interface
    public ScreensService(Core core) {
        this.core = core;
    }

    public void registerScreen(BaseScreen screen) {
        screensMap.put(screen.getScreenType(), screen);
    }

    public void changeScreen(ScreenType screenType) {
        BaseScreen screen = screensMap.get(screenType);
        if (screen != null) {
            core.setScreen(screen);
        }
    }
    //endregion
}
