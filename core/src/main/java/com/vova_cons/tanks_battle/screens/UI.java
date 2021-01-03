package com.vova_cons.tanks_battle.screens;

import com.badlogic.gdx.Gdx;

/**
 * Created by anbu on 27.10.19.
 **/
public class UI {
    public static final float SCENE_WIDTH = 1920;
    public static final float SCENE_HEIGHT = 1080;
    public static float WIDTH = SCENE_WIDTH;
    public static float HEIGHT = SCENE_HEIGHT;

    public static float getScreenRatioAdjustiment() {
        float w1 = Gdx.graphics.getBackBufferWidth();
        float h1 = Gdx.graphics.getBackBufferHeight();
        return (w1 * SCENE_HEIGHT / h1 - SCENE_WIDTH) * 0.5f;
    }
}
