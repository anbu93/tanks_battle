package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

import static com.vova_cons.tanks_battle.screens.game.ecs.Families.moved;

public class MoveSystem extends GameSystem {
    public MoveSystem(GameWorld world) {
        super(world);
    }

    public MoveSystem(int priority, GameWorld world) {
        super(priority, world);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(Entity entity : getEngine().getEntitiesFor(moved)) {
            processEntity(entity, deltaTime);
        }
    }

    private void processEntity(Entity entity, float deltaTime) {
        Components.Body body = Components.body.get(entity);
        Components.Velocity velocity = Components.velocity.get(entity);
        body.x += velocity.x * deltaTime;
        body.y += velocity.y * deltaTime;
    }
}
