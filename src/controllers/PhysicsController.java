package controllers;

import entities.Bullet;
import entities.Enemy;
import entities.HealthPacks;
import entities.Player;

public class PhysicsController {

    public static boolean Collision(Bullet bullet, Enemy enemy){

        return bullet.getBounds().intersects(enemy.getBounds());

    }
    public static boolean Collision(Player player, Enemy enemy){

        return player.getBounds().intersects(enemy.getBounds());

    }

    public static boolean Collision(Enemy enemy, Bullet bullet){

        return enemy.getBounds().intersects(bullet.getBounds());

    }

    public static boolean Collision(Player player, HealthPacks health){

        return player.getBounds().intersects(health.getBounds());

    }
}
