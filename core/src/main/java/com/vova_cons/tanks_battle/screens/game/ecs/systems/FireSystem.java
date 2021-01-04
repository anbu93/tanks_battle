package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Entities;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

public class FireSystem extends FamilyGameSystem {
    public static final float BULLET_SPEED = 20f;
    public FireSystem(GameWorld world) {
        super(world, Families.tanks);
    }

    public FireSystem(int priority, GameWorld world) {
        super(priority, world, Families.tanks);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(Entity entity : entities) {
            processEntity(entity);
        }
    }

    private void processEntity(Entity entity) {
        if (Families.hasFire.matches(entity)) {
            fireEntity(entity);
        }
    }

    private void fireEntity(Entity entity) {
        Components.Body body = Components.body.get(entity);
        Components.Rotation rotation = Components.rotation.get(entity);
        Components.Fire fire = Components.fire.get(entity);
        Components.Team team = Components.team.get(entity);
        Entity bullet = Entities.createBullet(team.value, fire.damage,
                body.x + body.w / 2f, body.y + body.h / 2f,
                rotation.value + 90, BULLET_SPEED);
        entity.remove(Components.Fire.class);
        Components.free(fire);
        getEngine().addEntity(bullet);
        entity.add(Components.create(Components.FireCooldown.class).timeout(1.5f));
    }
}
