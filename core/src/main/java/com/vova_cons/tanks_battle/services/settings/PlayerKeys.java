package com.vova_cons.tanks_battle.services.settings;

import com.badlogic.gdx.Input;

public class PlayerKeys {
    public int up = Input.Keys.W;
    public int down = Input.Keys.S;
    public int left = Input.Keys.A;
    public int right = Input.Keys.D;
    public int fire = Input.Keys.SPACE;

    /** Constructor with default keys */
    public PlayerKeys() {}

    /** Create PlayerKeys with serialized array */
    public PlayerKeys(int[] keys) {
        up = keys[0];
        down = keys[1];
        left = keys[2];
        right = keys[3];
        fire = keys[4];
    }

    public int[] serialize() {
        return new int[] {
                up, down, left, right, fire
        };
    }
}
