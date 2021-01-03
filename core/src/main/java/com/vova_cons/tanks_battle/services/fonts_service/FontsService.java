package com.vova_cons.tanks_battle.services.fonts_service;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vova_cons.tanks_battle.services.Service;

public interface FontsService extends Service {
    BitmapFont getFont();
    BitmapFont getFont(Size sizeName);
    void dispose();

    enum Size {
        Title(60), H1(41), H2(30);

        int size;
        Size(int size) {
            this.size = size;
        }
    }
}
