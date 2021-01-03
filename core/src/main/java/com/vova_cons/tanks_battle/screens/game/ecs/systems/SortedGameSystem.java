package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

import java.util.Comparator;

public abstract class SortedGameSystem extends SortedIteratingSystem {
    protected final GameWorld world;

    public SortedGameSystem(Family family, Comparator<Entity> comparator, GameWorld world) {
        super(family, comparator);
        this.world = world;
    }

    public SortedGameSystem(Family family, Comparator<Entity> comparator, GameWorld world, int priority) {
        super(family, comparator, priority);
        this.world = world;
    }
}
