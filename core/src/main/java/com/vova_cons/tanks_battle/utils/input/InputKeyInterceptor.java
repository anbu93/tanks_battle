package com.vova_cons.tanks_battle.utils.input;

import com.badlogic.gdx.InputAdapter;
import com.vova_cons.tanks_battle.utils.Processor;

public class InputKeyInterceptor extends InputAdapter {
    private final Processor<Integer> keysProcessor;

    public InputKeyInterceptor(Processor<Integer> keysProcessor) {
        this.keysProcessor = keysProcessor;
    }

    @Override
    public boolean keyUp(int keycode) {
        return keysProcessor.process(keycode);
    }
}
