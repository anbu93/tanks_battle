package com.vova_cons.tanks_battle.screens.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.tanks_battle.screens.UI;
import com.vova_cons.tanks_battle.screens.assets.UiBuilder;
import com.vova_cons.tanks_battle.services.fonts_service.FontsService;
import com.vova_cons.tanks_battle.services.settings.PlayerKeys;
import com.vova_cons.tanks_battle.utils.Processor;

public class InputKeysChangeView extends Group {
    private final SettingsController controller;
    private Label label;
    private PlayerKeys keys;
    private int index = 0;
    private Processor<PlayerKeys> callback = null;

    //region initialization
    public InputKeysChangeView(SettingsController controller) {
        this.controller = controller;
        this.setVisible(false);
        this.addActor(UiBuilder.createFade(0.85f));
        Actor panel = UiBuilder.createPanel();
        this.addActor(panel);
        VerticalGroup content = new VerticalGroup();
        content.space(25f);
        label = UiBuilder.createLabel("Please input key for right", FontsService.Size.Title, Color.BLACK);
        content.addActor(label);
        content.addActor(UiBuilder.createLabel("Input ESCAPE for cancel", FontsService.Size.H1, Color.BLACK));
        content.pack();
        content.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT/2f, Align.center);
        this.addActor(content);
        panel.setSize(content.getWidth() + 100, content.getHeight() + 100);
        panel.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT/2f, Align.center);
    }
    //endregion


    //region interface
    public void show(Processor<PlayerKeys> callback) {
        this.callback = callback;
        keys = new PlayerKeys();
        index = 0;
        this.setVisible(true);
        changeIndex();
    }

    public boolean onInputKey(int keyCode) {
        if (isVisible()) {
            return processInputKey(keyCode);
        }
        return false;
    }

    public void hide() {
        this.setVisible(false);
    }
    //endregion


    //region logic
    private boolean processInputKey(int keyCode) {
        switch(index) {
            case 0: keys.up = keyCode; break;
            case 1: keys.down = keyCode; break;
            case 2: keys.left = keyCode; break;
            case 3: keys.right = keyCode; break;
            case 4: keys.fire = keyCode; break;
        }
        index++;
        changeIndex();
        if (index == 5) {
            finish();
        }
        return true;
    }

    private void changeIndex() {
        switch(index) {
            case 0: label.setText("Please input key for up"); break;
            case 1: label.setText("Please input key for down"); break;
            case 2: label.setText("Please input key for left"); break;
            case 3: label.setText("Please input key for right"); break;
            case 4: label.setText("Please input key for fire"); break;
        }
    }

    private void finish() {
        hide();
        if (callback != null) {
            callback.process(keys);
        }
    }
    //endregion
}
