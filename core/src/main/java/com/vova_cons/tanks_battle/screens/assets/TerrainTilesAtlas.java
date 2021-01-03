package com.vova_cons.tanks_battle.screens.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.vova_cons.tanks_battle.services.AssetsService;
import com.vova_cons.tanks_battle.services.ServiceLocator;
import com.vova_cons.tanks_battle.utils.RandomUtils;

public class TerrainTilesAtlas {
    public static final String PATH = "tilesheet/terrainTiles_default.png";
    public static final int TILE_SIZE = 64;
    public static final int EMPTY_GRASS = 0;
    public static final int EMPTY_SAND = 1;
    public static final int ROAD_TOP = 2;
    public static final int ROAD_BOTTOM = 4;
    public static final int ROAD_LEFT = 8;
    public static final int ROAD_RIGHT = 16;

    private Texture texture;
    private IntArray identifiers = new IntArray();
    private IntMap<TextureRegion> regions = new IntMap<>();


    public TerrainTilesAtlas() {
        AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
        texture = assetsService.get(PATH, Texture.class);
        // texture size: 640x256 px
        // tiles size: 10x4
        // tile size: 64x64 px
        createRegion(0, 0, EMPTY_GRASS);
        createRegion(0, 2, EMPTY_SAND);
        createRegion(1, 0, EMPTY_GRASS, ROAD_TOP, ROAD_BOTTOM);
        createRegion(2, 0, EMPTY_GRASS, ROAD_LEFT, ROAD_RIGHT);
        createRegion(3, 0, EMPTY_GRASS, ROAD_TOP, ROAD_BOTTOM, ROAD_RIGHT);
        createRegion(4, 0, EMPTY_GRASS, ROAD_TOP, ROAD_BOTTOM, ROAD_LEFT);
        createRegion(5, 0, EMPTY_GRASS, ROAD_LEFT, ROAD_RIGHT, ROAD_TOP);
        createRegion(6, 0, EMPTY_GRASS, ROAD_LEFT, ROAD_RIGHT, ROAD_BOTTOM);
        createRegion(2, 1, EMPTY_GRASS, ROAD_LEFT, ROAD_RIGHT, ROAD_BOTTOM, ROAD_TOP);
        createRegion(3, 1, EMPTY_GRASS, ROAD_RIGHT, ROAD_BOTTOM);
        createRegion(4, 1, EMPTY_GRASS, ROAD_LEFT, ROAD_BOTTOM);
        createRegion(5, 1, EMPTY_GRASS, ROAD_RIGHT, ROAD_TOP);
        createRegion(6, 1, EMPTY_GRASS, ROAD_LEFT, ROAD_TOP);
        createRegion(1, 2, EMPTY_SAND, ROAD_TOP, ROAD_BOTTOM);
        createRegion(2, 2, EMPTY_SAND, ROAD_LEFT, ROAD_RIGHT);
        createRegion(3, 2, EMPTY_SAND, ROAD_TOP, ROAD_BOTTOM, ROAD_RIGHT);
        createRegion(4, 2, EMPTY_SAND, ROAD_TOP, ROAD_BOTTOM, ROAD_LEFT);
        createRegion(5, 2, EMPTY_SAND, ROAD_LEFT, ROAD_RIGHT, ROAD_TOP);
        createRegion(6, 2, EMPTY_SAND, ROAD_LEFT, ROAD_RIGHT, ROAD_BOTTOM);
        createRegion(2, 3, EMPTY_SAND, ROAD_LEFT, ROAD_RIGHT, ROAD_BOTTOM, ROAD_TOP);
        createRegion(3, 3, EMPTY_SAND, ROAD_RIGHT, ROAD_BOTTOM);
        createRegion(4, 3, EMPTY_SAND, ROAD_LEFT, ROAD_BOTTOM);
        createRegion(5, 3, EMPTY_SAND, ROAD_RIGHT, ROAD_TOP);
        createRegion(6, 3, EMPTY_SAND, ROAD_LEFT, ROAD_TOP);
    }

    public void createRegion(int x, int y, int... types) {
        int type = collectType(types);
        TextureRegion region = new TextureRegion(texture, x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
        regions.put(type, region);
        identifiers.add(type);
    }

    public TextureRegion get(int... types) {
        return regions.get(collectType(types));
    }

    public TextureRegion getRandom() {
        int type = identifiers.get(RandomUtils.random.nextInt(identifiers.size));
        return regions.get(type);
    }

    private int collectType(int... types) {
        int totalType = 0;
        for(int type : types) {
            totalType = totalType | type;
        }
        return totalType;
    }
}
