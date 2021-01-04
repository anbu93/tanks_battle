package com.vova_cons.tanks_battle.screens.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.vova_cons.tanks_battle.screens.ScreenType;
import com.vova_cons.tanks_battle.screens.SingleCreateScreen;
import com.vova_cons.tanks_battle.utils.input.InputKeyInterceptor;

public class SettingsScreen extends SingleCreateScreen {
    public static final ScreenType PREV_SCREEN = ScreenType.MainMenu;
    private SettingsController controller;

    @Override
    protected void setInputMultiplexer() {
        InputMultiplexer multiplexer = new InputMultiplexer(stage, new InputKeyInterceptor(this::onInputKey));
        Gdx.input.setInputProcessor(multiplexer);
    }

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

    private boolean onInputKey(int keyCode) {
        return controller.onInputKey(keyCode);
    }
}
