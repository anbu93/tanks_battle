package com.vova_cons.tanks_battle.screens.game.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Rectangle;
import com.vova_cons.tanks_battle.utils.GameMathUtils;

public class Components {
    public static ComponentMapper<Body> body = ComponentMapper.getFor(Body.class);
    public static ComponentMapper<Velocity> velocity = ComponentMapper.getFor(Velocity.class);
    public static ComponentMapper<Rotation> rotation = ComponentMapper.getFor(Rotation.class);
    public static ComponentMapper<Tank> tank = ComponentMapper.getFor(Tank.class);
    public static ComponentMapper<Bullet> bullet = ComponentMapper.getFor(Bullet.class);
    public static ComponentMapper<Health> health = ComponentMapper.getFor(Health.class);
    public static ComponentMapper<Drawable> drawable = ComponentMapper.getFor(Drawable.class);
    public static ComponentMapper<Sprite> sprite = ComponentMapper.getFor(Sprite.class);

    public static class Body implements Component {
        public float x, y, w, h;
        public Body() {}
        public Body(float x, float y) {
            this(x, y, 0, 0);
        }
        public Body(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        public Rectangle get(Rectangle rect) {
            return rect.set(x, y, w, h);
        }
    }
    public static class Rotation implements Component {
        public float value;
        public Rotation() {}
        public Rotation(float rotation) {
            this.value = rotation;
        }
    }
    public static class Velocity implements Component {
        public float x, y;
        public Velocity() {}
        public Velocity(float x, float y) {
            this.x = x;
            this.y = y;
        }
        public void apply(Body body) {
            body.x += x;
            body.y += y;
        }
    }
    public static class Tank implements Component {
        // tank type here?
    }
    public static class Bullet implements Component {
        public float damage;
    }
    public static class Health implements Component {
        public float current;
        public float max;
        public Health(float max) {
            this(max, max);
        }
        public Health(float current, float max) {
            this.current = current;
            this.max = max;
        }
        public void add(float value) {
            current = GameMathUtils.limit(current + value, 0, max);
        }
    }
    public static class Drawable implements Component {
        public static final int SPRITE = 0;
        public int z;
        public int type = SPRITE;
        public Drawable() {}
        public Drawable(int z) {
            this.z = z;
        }
    }
    public static class Sprite implements Component {
        public String texture;
        public Sprite(String texture) {
            this.texture = texture;
        }
    }
    public static class Player implements Component {}
}
