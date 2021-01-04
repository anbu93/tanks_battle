package com.vova_cons.tanks_battle.screens.game.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pools;
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
    public static ComponentMapper<Fire> fire = ComponentMapper.getFor(Fire.class);
    public static ComponentMapper<FireCooldown> fireCooldown = ComponentMapper.getFor(FireCooldown.class);
    public static ComponentMapper<Team> team = ComponentMapper.getFor(Team.class);

    public static <T extends Component> T create(Class<T> type) {
        return Pools.obtain(type);
    }
    public static <T extends Component> void free(T obj) {
        Pools.free(obj);
    }

    public static class Body implements Component {
        public float x, y, w, h;
        public Body setPosition(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }
        public Body set(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            return this;
        }
        public Rectangle get(Rectangle rect) {
            return rect.set(x, y, w, h);
        }
    }
    public static class Rotation implements Component {
        public float value;
        public Rotation set(float rotation) {
            this.value = rotation;
            return this;
        }
    }
    public static class Velocity implements Component {
        public float x, y;
        public Velocity set(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }
    }
    public static class Tank implements Component {
        // tank type here?
    }
    public static class Bullet implements Component {
        public float damage;
        public int team;
        public Bullet set(int team, float damage) {
            this.team = team;
            this.damage = damage;
            return this;
        }
    }
    public static class Health implements Component {
        public float current;
        public float max;
        public Health init(float max) {
            return init(max, max);
        }
        public Health init(float current, float max) {
            this.current = current;
            this.max = max;
            return this;
        }
        public void add(float value) {
            current = GameMathUtils.limit(current + value, 0, max);
        }
    }
    public static class Drawable implements Component {
        public static final int SPRITE = 0;
        public int z;
        public int type = SPRITE;
        public Drawable setZ(int z) {
            this.z = z;
            return this;
        }
    }
    public static class Sprite implements Component {
        public String texture;
        public float rotation = 0;
        public Sprite setTexture(String texture) {
            this.texture = texture;
            return this;
        }
        public Sprite setRotation(float rotation) {
            this.rotation = rotation;
            return this;
        }
    }
    public static class Player implements Component {}
    public static class Fire implements Component {
        public float damage;
        public Fire setDamage(int damage) {
            this.damage = damage;
            return this;
        }
    }
    public static class FireCooldown implements Component {
        public float time;
        public FireCooldown timeout(float time) {
            this.time = time;
            return this;
        }
    }
    public static class Team implements Component {
        public int value;
        public Team set(int value) {
            this.value = value;
            return this;
        }
    }
}
