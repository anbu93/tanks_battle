package com.vova_cons.tanks_battle.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class NativeUtils {
    public static Preferences getPrefs(String name) {
        return Gdx.app.getPreferences("com.vova_cons.tanks_battle." + name + ".prefs");
    }
}
