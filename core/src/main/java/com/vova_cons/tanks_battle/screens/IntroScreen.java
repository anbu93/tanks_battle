package com.vova_cons.tanks_battle.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.tanks_battle.screens.assets.GameAssets;
import com.vova_cons.tanks_battle.screens.assets.TerrainTilesAtlas;
import com.vova_cons.tanks_battle.screens.assets.UiBuilder;
import com.vova_cons.tanks_battle.services.AssetsService;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;

public class IntroScreen extends BaseScreen {
    private AssetsService assetManager = ServiceLocator.getService(AssetsService.class);
    private ScreensService screensService = ServiceLocator.getService(ScreensService.class);

    @Override
    public ScreenType getScreenType() {
        return ScreenType.Intro;
    }

    @Override
    public void start() {
        prepareView();
        startLoadingAssets();
    }

    private void prepareView() {
        Image libgdxLogo = new Image(new Texture("badlogic.png"));
        libgdxLogo.setPosition(UI.SCENE_WIDTH/2f, UI.SCENE_HEIGHT/2f, Align.center);
        this.addActor(libgdxLogo);
    }

    private void startLoadingAssets() {
        assetManager.load(UiBuilder.BLACK, Texture.class);
        assetManager.load(UiBuilder.BLUE_BUTTON_IMG, Texture.class);
        assetManager.load(UiBuilder.GREEN_BUTTON_IMG, Texture.class);
        assetManager.load(UiBuilder.PANEL, Texture.class);
        assetManager.load(UiBuilder.PANEL_GREEN, Texture.class);
        assetManager.load(UiBuilder.CLOSE_RED_BG, Texture.class);
        assetManager.load(UiBuilder.CLOSE_BLUE_CROSS, Texture.class);
        assetManager.load(TerrainTilesAtlas.PATH, Texture.class);
        assetManager.load(GameAssets.TANK_RED, Texture.class);
        assetManager.load(GameAssets.TANK_BLUE, Texture.class);
        assetManager.load(GameAssets.BULLET_RED, Texture.class);
    }

    @Override
    public void update(float delta) {
        if (assetManager.isFinishLoading()) {
            screensService.changeScreen(ScreenType.MainMenu);
        }
    }
}
