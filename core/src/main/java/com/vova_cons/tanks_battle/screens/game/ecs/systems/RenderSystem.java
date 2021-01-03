package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.vova_cons.tanks_battle.screens.assets.TerrainTilesAtlas;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;
import com.vova_cons.tanks_battle.services.AssetsService;
import com.vova_cons.tanks_battle.services.ServiceLocator;

public class RenderSystem extends SortedGameSystem {
    public static float TILE = 64f;
    private final Batch batch;
    private AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
    private ObjectMap<String, TextureRegion> regions = new ObjectMap<>();
    private TerrainTilesAtlas terrain = new TerrainTilesAtlas();

    public RenderSystem(GameWorld world, Batch batch) {
        super(Families.drawable, RenderSystem::compare, world);
        this.batch = batch;
    }

    public RenderSystem(int priority, GameWorld world, Batch batch) {
        super(Families.drawable, RenderSystem::compare, world, priority);
        this.batch = batch;
    }

    private static int compare(Entity e1, Entity e2) {
        Components.Drawable s1 = Components.drawable.get(e1);
        Components.Drawable s2 = Components.drawable.get(e2);
        if (s1 != null && s2 != null) {
            return (int) Math.signum(s1.z - s2.z);
        }
        return -1;
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        drawBg();
        super.update(deltaTime);
        batch.flush();
        batch.end();
    }

    private void drawBg() {
        world.map.foreach(this::drawTile);
    }

    private void drawTile(int x, int y, int tile) {
        TextureRegion texture = terrain.getSingle(tile);
        if (texture != null) {
            batch.draw(texture, x * TILE, y * TILE, TILE, TILE);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Components.Drawable drawable = Components.drawable.get(entity);
        switch(drawable.type) {
            case Components.Drawable.SPRITE:
                drawSprite(entity);
                break;
        }
    }

    private void drawSprite(Entity entity) {
        Components.Body body = Components.body.get(entity);
        Components.Sprite sprite = Components.sprite.get(entity);
        TextureRegion texture = getRegion(sprite.texture);
        if (Families.withRotatedBody.matches(entity)) {
            Components.Rotation rotation = Components.rotation.get(entity);
            batch.draw(texture, body.x*TILE, body.y*TILE,
                    body.w*TILE/2f, body.h*TILE/2f,
                    body.w*TILE, body.h*TILE,
                    1f, 1f,
                    rotation.value - 180);
        } else {
            batch.draw(texture, body.x*TILE, body.y*TILE, body.w*TILE, body.h*TILE);
        }
    }

    private TextureRegion getRegion(String path) {
        if (!regions.containsKey(path)) {
            regions.put(path, new TextureRegion(assetsService.get(path, Texture.class)));
        }
        return regions.get(path);
    }
}
