package com.vova_cons.tanks_battle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.tanks_battle.screens.assets.TerrainTilesAtlas;
import com.vova_cons.tanks_battle.screens.game.GameScreen;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;
import com.vova_cons.tanks_battle.services.fonts_service.FontsService;
import com.vova_cons.tanks_battle.utils.ViewUtils;

public class MainMenuScreen extends SingleCreateScreen {
    private ScreensService screensService = ServiceLocator.getService(ScreensService.class);

    @Override
    public ScreenType getScreenType() {
        return ScreenType.MainMenu;
    }

    @Override
    public void start() {
        createBg();
        createMenu();
    }

    private void createBg() {
        TerrainTilesAtlas atlas = new TerrainTilesAtlas();
        for(int x = 0; x < UI.SCENE_WIDTH + TerrainTilesAtlas.TILE_SIZE; x += TerrainTilesAtlas.TILE_SIZE) {
            for(int y = 0; y < UI.SCENE_HEIGHT + TerrainTilesAtlas.TILE_SIZE; y += TerrainTilesAtlas.TILE_SIZE) {
                Image tile = new Image(atlas.getRandom());
                tile.setPosition(x, y);
                this.addActor(tile);
            }
        }
    }

    private void createMenu() {
        VerticalGroup content = new VerticalGroup();
        content.space(30);

        Label startLabel = ViewUtils.createLabel("Start game", FontsService.Size.H1, Color.RED);
        ViewUtils.clickListener(startLabel, this::clickStart);
        content.addActor(startLabel);

        Label optionsLabel = ViewUtils.createLabel("Options", FontsService.Size.H1, Color.RED);
        ViewUtils.clickListener(optionsLabel, this::clickOptions);
        content.addActor(optionsLabel);

        Label exitLabel = ViewUtils.createLabel("Exit", FontsService.Size.H1, Color.RED);
        ViewUtils.clickListener(exitLabel, this::clickExit);
        content.addActor(exitLabel);

        content.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT/2f, Align.center);
        this.addActor(content);
    }

    private void clickStart() {
        GameScreen.level = 1;
        screensService.changeScreen(ScreenType.Game);
    }

    private void clickOptions() {
        screensService.changeScreen(ScreenType.Options);
    }

    private void clickExit() {
        Gdx.app.exit();
    }

    @Override
    public void update(float delta) {}
}
