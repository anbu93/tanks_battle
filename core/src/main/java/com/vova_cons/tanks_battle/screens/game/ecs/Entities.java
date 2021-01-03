package com.vova_cons.tanks_battle.screens.game.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.vova_cons.tanks_battle.screens.assets.GameAssets;

public class Entities {
    private static Vector2 vector = new Vector2();

    public static Entity createTank(float x, float y) {
        Entity entity = new Entity();
        entity.add(new Components.Body(x, y, 0.75f, 0.75f));
        entity.add(new Components.Rotation());
        entity.add(new Components.Velocity());
        entity.add(new Components.Health(100));
        entity.add(new Components.Tank());
        entity.add(new Components.Drawable(0));
        entity.add(new Components.Sprite(GameAssets.TANK_RED));
        return entity;
    }

    public static Entity createBullet(float x, float y, float targetX, float targetY, float speed) {
        Entity entity = new Entity();
        entity.add(new Components.Body(x, y, 0.1f, 0.1f));
        vector.set(targetX-x, targetY-y).setLength(speed);
        entity.add(new Components.Velocity(vector.x, vector.y));
        entity.add(new Components.Rotation(vector.angleDeg()));
        entity.add(new Components.Bullet());
        entity.add(new Components.Drawable(1));
        entity.add(new Components.Sprite(GameAssets.BULLET_RED));
        return entity;
    }
}
