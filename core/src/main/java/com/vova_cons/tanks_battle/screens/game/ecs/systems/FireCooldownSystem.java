package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

public class FireCooldownSystem extends FamilyGameSystem {
    public FireCooldownSystem(GameWorld world) {
        super(world, Families.tanks);
    }

    public FireCooldownSystem(int priority, GameWorld world, Family family) {
        super(priority, world, Families.tanks);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(Entity entity : entities) {
            processEntity(entity, deltaTime);
        }
    }

    private void processEntity(Entity entity, float deltaTime) {
        if (Families.hasFireCooldown.matches(entity)) {
            Components.FireCooldown cooldown = Components.fireCooldown.get(entity);
            cooldown.time -= deltaTime;
            if (cooldown.time <= 0) {
                entity.remove(Components.FireCooldown.class);
                Components.free(cooldown);
            }
        }
    }
}
