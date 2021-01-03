package com.vova_cons.tanks_battle.screens.game.world;

@FunctionalInterface
public interface TileMapIterator {
    void accept(int x, int y, int value);
}
