package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;
import com.vova_cons.tanks_battle.services.settings.PlayerKeys;
import com.vova_cons.tanks_battle.utils.GameMathUtils;

public class PlayerInputSystem extends EntitySystem {
    private final PlayerKeys keys;
    private final Entity player;
    private float maxSpeed = 2.5f;
    private float acceleration = 10f;
    private float deceleration = 10f;
    private int dx, dy;
    private boolean isFire = false;

    public PlayerInputSystem(PlayerKeys keys, Entity player) {
        this.keys = keys;
        this.player = player;
    }

    public PlayerInputSystem(int priority, PlayerKeys keys, Entity player) {
        super(priority);
        this.keys = keys;
        this.player = player;
    }

    @Override
    public void update(float deltaTime) {
        processInput();
        processPlayer(player, deltaTime);
    }

    private void processInput() {
        dx = 0;
        dy = 0;
        if (Gdx.input.isKeyPressed(keys.up)) {
            dy += 1;
        }
        if (Gdx.input.isKeyPressed(keys.down)) {
            dy -= 1;
        }
        if (Gdx.input.isKeyPressed(keys.right)) {
            dx += 1;
        }
        if (Gdx.input.isKeyPressed(keys.left)) {
            dx -= 1;
        }
        isFire = Gdx.input.isKeyJustPressed(keys.fire);
    }

    private Vector2 vector = new Vector2();
    private void processPlayer(Entity entity, float deltaTime) {
        Components.Velocity velocity = Components.velocity.get(entity);
        applyVelocity(entity, velocity, deltaTime);
        setRotation(entity, velocity);
        applyFire(entity);
    }

    private void applyVelocity(Entity entity, Components.Velocity velocity, float deltaTime) {
        if (dx != 0) {
            velocity.x = accelerate(velocity.x, dx, deltaTime);
        } else {
            velocity.x = decelerate(velocity.x, deltaTime);
        }
        if (dy != 0) {
            velocity.y = accelerate(velocity.y, dy, deltaTime);
        } else {
            velocity.y = decelerate(velocity.y, deltaTime);
        }
    }

    private float decelerate(float velocity, float deltaTime) {
        if (velocity > 0) {
            return GameMathUtils.limit(velocity - deceleration * deltaTime, 0, maxSpeed);
        }
        return GameMathUtils.limit(velocity + deceleration * deltaTime, -maxSpeed, 0);
    }

    private float accelerate(float velocity, int direction, float deltaTime) {
        if (direction == 1) {
            return GameMathUtils.limit(velocity + acceleration * deltaTime, 0, maxSpeed);
        } else if (direction == -1) {
            return GameMathUtils.limit(velocity - acceleration * deltaTime, -maxSpeed, 0);
        }
        return velocity;
    }

    private void setRotation(Entity entity, Components.Velocity velocity) {
        if (dx != 0 || dy != 0) {
            Components.Rotation rotation = Components.rotation.get(entity);
            vector.set(velocity.x, velocity.y);
            rotation.value = vector.angleDeg() - 90;
        }
    }

    private void applyFire(Entity entity) {
        if (isFire) {
            if (!Families.hasFireCooldown.matches(entity)) {
                entity.add(Components.create(Components.Fire.class).setDamage(15));
            }
        }
    }
}
