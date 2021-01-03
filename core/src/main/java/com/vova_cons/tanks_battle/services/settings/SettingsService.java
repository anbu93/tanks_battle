package com.vova_cons.tanks_battle.services.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.vova_cons.tanks_battle.services.Service;
import com.vova_cons.tanks_battle.utils.NativeUtils;

public class SettingsService implements Service {
    private static final String MUSIC = "music";
    private static final String SOUND = "sound";
    private static final String PLAYER_1_KEYS = "player_1_keys";
    private Preferences prefs;

    public SettingsService() {
        prefs = NativeUtils.getPrefs("settings");
    }

    public boolean isEnableMusic() {
        return prefs.getBoolean(MUSIC, true);
    }

    public void setEnableMusic(boolean isEnable) {
        prefs.putBoolean(MUSIC, isEnable);
        prefs.flush();
    }

    public boolean isEnableSound() {
        return prefs.getBoolean(SOUND, true);
    }

    public void setEnableSound(boolean isEnable) {
        prefs.putBoolean(SOUND, isEnable);
        prefs.flush();
    }

    public PlayerKeys getPlayer1Keys() {
        String keysValue = prefs.getString(PLAYER_1_KEYS, null);
        if (keysValue != null) {
            String[] tokens = keysValue.split("#");
            int[] keys = new int[tokens.length];
            for(int i = 0; i < tokens.length; i++) {
                keys[i] = Integer.parseInt(tokens[i]);
            }
            return new PlayerKeys(keys);
        }
        return new PlayerKeys();
    }

    public void setPlayer1Keys(PlayerKeys playerKeys) {
        StringBuilder sb = new StringBuilder();
        int[] keys = playerKeys.serialize();
        for(int i = 0; i < keys.length; i++) {
            if (i != 0) sb.append("#");
            sb.append(keys[i]);
        }
        prefs.putString(PLAYER_1_KEYS, sb.toString());
        prefs.flush();
    }
}
