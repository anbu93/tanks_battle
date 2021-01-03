package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

public class FamilyGameSystem extends GameSystem {
    protected Family family;
    protected ImmutableArray<Entity> entities;

    public FamilyGameSystem(GameWorld world, Family family) {
        super(world);
        this.family = family;
    }

    public FamilyGameSystem(int priority, GameWorld world, Family family) {
        super(priority, world);
        this.family = family;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        refillEntities();
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);
        refillEntities();
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);
        refillEntities();
    }

    protected void refillEntities() {
        entities = getEngine().getEntitiesFor(family);
    }
}
