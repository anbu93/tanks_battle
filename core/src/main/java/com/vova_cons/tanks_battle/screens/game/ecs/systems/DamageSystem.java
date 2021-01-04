package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Entities;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;

public class DamageSystem extends GameSystem {
    public DamageSystem(GameWorld world) {
        super(world);
    }

    public DamageSystem(int priority, GameWorld world) {
        super(priority, world);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ImmutableArray<Entity> targets = getEngine().getEntitiesFor(Families.tanks);
        for(Entity entity : getEngine().getEntitiesFor(Families.bullets)) {
            processBullet(entity, targets);
        }
    }

    private Rectangle bulletRect = new Rectangle();
    private Rectangle targetRect = new Rectangle();
    private void processBullet(Entity entity, ImmutableArray<Entity> targets) {
        Components.Body body = Components.body.get(entity);
        bulletRect = body.get(bulletRect);
        for(Entity targetEntity : targets) {
            Components.Body targetBody = Components.body.get(targetEntity);
            targetRect = targetBody.get(targetRect);
            if (bulletRect.overlaps(targetRect)) {
                boolean isHit = hitTargetCheck(entity, targetEntity);
                if (isHit) {
                    return;
                }
            }
        }
    }

    private boolean hitTargetCheck(Entity entity, Entity targetEntity) {
        if (isCanDamage(entity, targetEntity)) {
            Components.Bullet bullet = Components.bullet.get(entity);
            Components.Health health = Components.health.get(targetEntity);
            health.add(-bullet.damage); // damage
            getEngine().removeEntity(entity);
            Entities.free(entity);
            return true;
        }
        return false;
    }

    private boolean isCanDamage(Entity entity, Entity targetEntity) {
        Components.Team teamBullet = Components.team.get(entity);
        Components.Team teamTarget = Components.team.get(targetEntity);
        if (teamBullet.value != teamTarget.value) {
            return true;
        }
        return false; // friendly fire not allow
    }
}
