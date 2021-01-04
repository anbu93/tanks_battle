package com.vova_cons.tanks_battle.screens.game.ecs;

import com.badlogic.ashley.core.Family;

public class Families {
    public static Family drawable = Family.all(Components.Drawable.class).get();
    public static Family withBody = Family.all(Components.Body.class).get();
    public static Family withRotatedBody = Family.all(Components.Body.class, Components.Rotation.class).get();
    public static Family moved = Family.all(Components.Body.class, Components.Velocity.class).get();
    public static Family tanks = Family.all(Components.Tank.class).get();
    public static Family players = Family.all(Components.Player.class).get();
    public static Family bullets = Family.all(Components.Bullet.class).get();
    public static Family alive = Family.all(Components.Health.class).get();
    public static Family hasFire = Family.all(Components.Fire.class).get();
    public static Family hasFireCooldown = Family.all(Components.FireCooldown.class).get();
}
