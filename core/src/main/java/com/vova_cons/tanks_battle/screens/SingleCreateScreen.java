package com.vova_cons.tanks_battle.screens;

public abstract class SingleCreateScreen extends BaseScreen {
    protected boolean isStarted = false;

    @Override
    public void show() {
        if (!isStarted) {
            isStarted = true;
            super.show();
        } else {
            setInputMultiplexer();
        }
    }

    @Override
    public void dispose() {
        // not dispose
    }
}
