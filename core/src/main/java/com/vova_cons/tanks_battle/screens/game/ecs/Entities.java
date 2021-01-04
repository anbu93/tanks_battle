package com.vova_cons.tanks_battle.screens.game.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.vova_cons.tanks_battle.screens.assets.GameAssets;
import com.vova_cons.tanks_battle.screens.game.Teams;

public class Entities {
    private static Pool<Entity> entities = new Pool<Entity>(10) {
        @Override
        protected Entity newObject() {
            return new Entity();
        }
    };
    private static Vector2 vector = new Vector2();

    public static Entity createTank(float x, float y, int team) {
        Entity entity = entities.obtain();
        entity.add(Components.create(Components.Body.class).set(x, y, 0.5f, 0.5f));
        entity.add(Components.create(Components.Rotation.class));
        entity.add(Components.create(Components.Velocity.class));
        entity.add(Components.create(Components.Health.class).init(100));
        entity.add(Components.create(Components.Tank.class));
        entity.add(Components.create(Components.Drawable.class).setZ(0));
        entity.add(Components.create(Components.Team.class).set(team));
        switch(team) {
            case Teams.PLAYER:
                entity.add(Components.create(Components.Sprite.class)
                        .setTexture(GameAssets.TANK_RED).setRotation(-180));
                break;
            case Teams.ENEMY:
                entity.add(Components.create(Components.Sprite.class)
                        .setTexture(GameAssets.TANK_BLUE).setRotation(-180));
                break;
        }
        return entity;
    }

    public static Entity createBullet(int team, float damage, float x, float y, float angle, float speed) {
        Entity entity = entities.obtain();
        entity.add(Components.create(Components.Body.class).set(x, y, 0.1f, 0.1f));
        vector.set(0, speed).setAngleDeg(angle);
        entity.add(Components.create(Components.Velocity.class).set(vector.x, vector.y));
        entity.add(Components.create(Components.Rotation.class).set(vector.angleDeg()));
        entity.add(Components.create(Components.Bullet.class).set(team, damage));
        entity.add(Components.create(Components.Drawable.class).setZ(1));
        entity.add(Components.create(Components.Sprite.class).setTexture(GameAssets.BULLET_RED));
        return entity;
    }

    public static void free(Entity entity) {
        for(Component component : entity.getComponents()) {
            Components.free(component);
        }
        entities.free(entity);
    }
}
