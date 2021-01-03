package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

public class GameSystem extends EntitySystem implements EntityListener {
    protected final GameWorld world;

    public GameSystem(GameWorld world) {
        this.world = world;
    }

    public GameSystem(int priority, GameWorld world) {
        super(priority);
        this.world = world;
    }

    @Override
    public void entityAdded(Entity entity) {}

    @Override
    public void entityRemoved(Entity entity) {}
}
