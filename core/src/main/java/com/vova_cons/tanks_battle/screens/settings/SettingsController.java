package com.vova_cons.tanks_battle.screens.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;
import com.vova_cons.tanks_battle.services.settings.PlayerKeys;
import com.vova_cons.tanks_battle.services.settings.SettingsService;
import com.vova_cons.tanks_battle.utils.NativeUtils;

public class SettingsController {
    private SettingsService settingsService = ServiceLocator.getService(SettingsService.class);
    private SettingsView view;
    private boolean isMusicEnable;
    private boolean isSoundEnable;
    private PlayerKeys player1Keys;

    public void setView(SettingsView view) {
        this.view = view;
    }

    //region interface
    public void onShow() {
        isMusicEnable = settingsService.isEnableMusic();
        isSoundEnable = settingsService.isEnableSound();
        player1Keys = settingsService.getPlayer1Keys();
        view.redrawMusic(isSoundEnable);
        view.redrawSound(isSoundEnable);
        view.redrawPlayer1Keys(player1Keys);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            onClickExit();
        }
    }

    public void onClickExit() {
        save();
        ScreensService screensService = ServiceLocator.getService(ScreensService.class);
        screensService.changeScreen(SettingsScreen.PREV_SCREEN);
    }

    public void onClickMusic() {
        isMusicEnable = !isMusicEnable;
        view.redrawMusic(isMusicEnable);
    }

    public void onClickSound() {
        isSoundEnable = !isSoundEnable;
        view.redrawSound(isSoundEnable);
    }

    public void onChangePlayer1Keys(int[] keys) {
        player1Keys = new PlayerKeys(keys);
        // check keys valid?
        view.redrawPlayer1Keys(player1Keys);
    }
    //endregion


    //region logic
    private void save() {
        settingsService.setEnableMusic(isMusicEnable);
        settingsService.setEnableSound(isSoundEnable);
        settingsService.setPlayer1Keys(player1Keys);
    }
    //endregion
}
