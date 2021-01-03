package com.vova_cons.tanks_battle.screens.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.vova_cons.tanks_battle.screens.game.ecs.Components;
import com.vova_cons.tanks_battle.screens.game.ecs.Families;
import com.vova_cons.tanks_battle.screens.game.world.GameWorld;
import com.vova_cons.tanks_battle.utils.GameMathUtils;

public class PlayerInputSystem extends FamilyGameSystem {
    private int dx, dy;
    private float maxSpeed = 3.5f;
    private float acceleration = 10f;
    private float deceleration = 10f;

    public PlayerInputSystem(GameWorld world) {
        super(world, Families.players);
    }

    public PlayerInputSystem(int priority, GameWorld world) {
        super(priority, world, Families.players);
    }

    @Override
    public void update(float deltaTime) {
        processInput();
        for(Entity entity : entities) {
            processPlayer(entity, deltaTime);
        }
    }

    private void processInput() {
        dx = 0;
        dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx -= 1;
        }
    }

    private Vector2 vector = new Vector2();
    private void processPlayer(Entity entity, float deltaTime) {
        Components.Velocity velocity = Components.velocity.get(entity);
        applyVelocity(entity, velocity, deltaTime);
        setRotation(entity, velocity);
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
}
