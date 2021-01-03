package com.vova_cons.tanks_battle.services;

import com.badlogic.gdx.assets.AssetManager;

public class AssetsService implements UpdatableService {
    private AssetManager assetManager = new AssetManager();

    @Override
    public void update(float delta) {
        assetManager.update();
    }

    public boolean isFinishLoading() {
        return assetManager.isFinished();
    }

    public <T> T get(String path, Class<T> type) {
        return assetManager.get(path, type);
    }

    public <T> void load(String path, Class<T> type) {
        assetManager.load(path, type);
    }

    public void unload(String path) {
        assetManager.unload(path);
    }

    public boolean isLoaded(String path) {
        return assetManager.isLoaded(path);
    }
}
