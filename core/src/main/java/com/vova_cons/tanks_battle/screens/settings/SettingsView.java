package com.vova_cons.tanks_battle.screens.settings;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.tanks_battle.screens.UI;
import com.vova_cons.tanks_battle.screens.assets.UiBuilder;
import com.vova_cons.tanks_battle.services.fonts_service.FontsService;
import com.vova_cons.tanks_battle.services.settings.PlayerKeys;
import com.vova_cons.tanks_battle.utils.ViewUtils;

public class SettingsView extends Group {
    private final SettingsController controller;
    private ImageTextButton musicButton;
    private ImageTextButton soundButton;
    private VerticalGroup playerInputContent;

    //region initialization
    public SettingsView(SettingsController controller) {
        this.controller = controller;
        controller.setView(this);
        createUi();
    }

    private void createUi() {
        createBg();
        createTitle();
        createBackButton();
        createContent();
    }

    private void createBg() {
        Actor bg = UiBuilder.createPanel();
        bg.setSize(UI.SCENE_WIDTH-100, UI.SCENE_HEIGHT-100);
        bg.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT/2f, Align.center);
        this.addActor(bg);
    }

    private void createTitle() {
        Actor bg = UiBuilder.createPanelGreen();
        this.addActor(bg);
        Label label = UiBuilder.createLabel("Settings", FontsService.Size.Title, Color.RED);
        label.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT - 50, Align.center);
        this.addActor(label);
        bg.setSize(label.getWidth()+50, label.getHeight()+20);
        bg.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT - 50, Align.center);
    }

    private void createBackButton() {
        Actor button = UiBuilder.createCloseButton();
        button.setPosition(UI.SCENE_WIDTH-50, UI.SCENE_HEIGHT-50, Align.center);
        ViewUtils.clickListener(button, controller::onClickExit);
        this.addActor(button);
    }

    private void createContent() {
        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.space(20);
        verticalGroup.addActor(createMusicButton());
        verticalGroup.addActor(createSoundButton());
        verticalGroup.addActor(createPlayerInputs());
        verticalGroup.pack();
        verticalGroup.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT/2f, Align.center);
        this.addActor(verticalGroup);
    }

    private Actor createMusicButton() {
        musicButton = UiBuilder.createButton("Music: on", FontsService.Size.H1);
        ViewUtils.clickListener(musicButton, controller::onClickMusic);
        return musicButton;
    }

    private Actor createSoundButton() {
        soundButton = UiBuilder.createButton("Sound: on", FontsService.Size.H1);
        ViewUtils.clickListener(soundButton, controller::onClickSound);
        return soundButton;
    }

    private Actor createPlayerInputs() {
        playerInputContent = new VerticalGroup();
        playerInputContent.setWidth(UI.SCENE_WIDTH * 0.7f);
        playerInputContent.left();
        redrawPlayer1Keys(new PlayerKeys());
        playerInputContent.pack();
        return playerInputContent;
    }
    //endregion


    //region interface
    public void redrawMusic(boolean isMusicEnable) {
        musicButton.setText("Music: " + (isMusicEnable ? "on" : "off"));
    }

    public void redrawSound(boolean isSoundEnable) {
        soundButton.setText("Sound: " + (isSoundEnable ? "on" : "off"));
    }

    public void redrawPlayer1Keys(PlayerKeys playerKeys) {
        playerInputContent.clear();
        playerInputContent.addActor(ViewUtils.createLabel("up: " + Input.Keys.toString(playerKeys.up), FontsService.Size.H1, Color.BLACK));
        playerInputContent.addActor(ViewUtils.createLabel("down: " + Input.Keys.toString(playerKeys.down), FontsService.Size.H1, Color.BLACK));
        playerInputContent.addActor(ViewUtils.createLabel("left: " + Input.Keys.toString(playerKeys.left), FontsService.Size.H1, Color.BLACK));
        playerInputContent.addActor(ViewUtils.createLabel("right: " +Input.Keys.toString(playerKeys.right), FontsService.Size.H1, Color.BLACK));
        playerInputContent.addActor(ViewUtils.createLabel("fire: " + Input.Keys.toString(playerKeys.fire), FontsService.Size.H1, Color.BLACK));
    }
    //endregion
}
