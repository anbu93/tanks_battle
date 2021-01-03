package com.vova_cons.tanks_battle.screens.settings;

import com.vova_cons.tanks_battle.screens.ScreenType;
import com.vova_cons.tanks_battle.screens.SingleCreateScreen;

public class SettingsScreen extends SingleCreateScreen {
    public static final ScreenType PREV_SCREEN = ScreenType.MainMenu;
    private SettingsController controller;

    @Override
    public ScreenType getScreenType() {
        return ScreenType.Options;
    }

    @Override
    public void start() {
        controller = new SettingsController();
        SettingsView view = new SettingsView(controller);
        this.addActor(view);
    }

    @Override
    public void show() {
        super.show();
        controller.onShow();
    }

    @Override
    public void update(float delta) {
        controller.update(delta);
    }
}
