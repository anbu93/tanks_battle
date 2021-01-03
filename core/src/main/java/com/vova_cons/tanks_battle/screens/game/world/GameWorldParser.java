package com.vova_cons.tanks_battle.screens.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.vova_cons.tanks_battle.screens.assets.TerrainTilesAtlas;

import java.util.Scanner;

public class GameWorldParser {
    private static final int ROAD = 1;
    private static final int EMPTY = 0;
    private GameWorld world;
    private TileMap map;

    public GameWorld parse(int level) {
        try {
            world = new GameWorld();
            parseLevelFile(level);
            prepareWorld();
            return world;
        } catch (Exception e) {
            throw new RuntimeException("Error with parsing level " + level, e);
        }
    }

    private void parseLevelFile(int level) {
        FileHandle levelFile = Gdx.files.internal("levels/lvl_" + level + ".txt");
        Scanner scanner = new Scanner(levelFile.read());
        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        map = new TileMap(width, height);
        map.defValue = -1;
        for(int line = 0; line < height; line++) {
            int y = height - line - 1;
            String tileLine = scanner.nextLine();
            char[] tiles = tileLine.toCharArray();
            for (int x = 0; x < Math.min(tileLine.length(), width); x++) {
                parseTile(x, y, tiles[x]);
            }
        }
    }

    private void parseTile(int x, int y, char tile) {
        switch(tile) {
            case '@':
                world.playerX = x;
                world.playerY = y;
                break;
            case '#':
                map.set(x, y, ROAD);
                break;
        }
    }

    private void prepareWorld() {
        world.map = new TileMap(map.width, map.height);
        map.foreach(this::prepareWorldIterate);
    }

    private void prepareWorldIterate(int x, int y, int value) {
        switch(value) {
            case ROAD:
                prepareRoadTile(x, y);
                break;
            case EMPTY:
                world.map.set(x, y, TerrainTilesAtlas.EMPTY_GRASS);
                break;
        }
    }

    private void prepareRoadTile(int x, int y) {
        int roadType = TerrainTilesAtlas.EMPTY_GRASS;
        if (map.get(x-1, y) == ROAD) {
            roadType = roadType | TerrainTilesAtlas.ROAD_LEFT;
        }
        if (map.get(x+1, y) == ROAD) {
            roadType = roadType | TerrainTilesAtlas.ROAD_RIGHT;
        }
        if (map.get(x, y-1) == ROAD) {
            roadType = roadType | TerrainTilesAtlas.ROAD_BOTTOM;
        }
        if (map.get(x, y+1) == ROAD) {
            roadType = roadType | TerrainTilesAtlas.ROAD_TOP;
        }
        if (roadType == TerrainTilesAtlas.ROAD_LEFT || roadType == TerrainTilesAtlas.ROAD_RIGHT) {
            roadType = TerrainTilesAtlas.ROAD_RIGHT | TerrainTilesAtlas.ROAD_LEFT;
        }
        if (roadType == TerrainTilesAtlas.ROAD_BOTTOM || roadType == TerrainTilesAtlas.ROAD_TOP) {
            roadType = TerrainTilesAtlas.ROAD_BOTTOM | TerrainTilesAtlas.ROAD_TOP;
        }
        world.map.set(x, y, roadType);
    }
}
