package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

/**
 * Created by anbu on 06.01.2021.
 **/
public class DeathSystem extends FamilyGameSystem {
    public DeathSystem(GameWorld world) {
        super(world, Families.alive);
    }

    public DeathSystem(int priority, GameWorld world) {
        super(priority, world, Families.alive);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(Entity entity : entities) {
            Components.Health health = Components.health.get(entity);
            if (health.current <= 0) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
