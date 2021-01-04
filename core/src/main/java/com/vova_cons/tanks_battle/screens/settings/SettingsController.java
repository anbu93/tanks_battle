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
        view.hidePlayerChangeView();
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

    public void onClickChangePlayer1Keys() {
        view.showPlayerChangeView(this::onChangePlayer1Keys);
    }

    public boolean onInputKey(int keyCode) {
        return view.onInputKey(keyCode);
    }

    public boolean onChangePlayer1Keys(PlayerKeys keys) {
        player1Keys = keys;
        // check keys valid?
        view.redrawPlayer1Keys(player1Keys);
        return true;
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
