package controllers;

import constants.GameConstants;
import entities.Bullet;
import entities.Enemy;
import entities.HealthPacks;
import main.Game;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EntityController {

    private List<Enemy> enemies = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private List<HealthPacks> healthPacks = new ArrayList<>();
    private Bullet tempBullet;
    private Enemy tempEnemy;
    private HealthPacks tempHealthPack;
    private Game game;
    private Random random = new Random();

    public EntityController(Game game){
        this.game=game;
    }

    public void tick(){

        for (int i = 0; i < this.bullets.size(); i++) {

            tempBullet = this.bullets.get(i);
            if (tempBullet.getY() < 0) {
                removeBullet(tempBullet);
            }
            tempBullet.tick();

        }

        for (int i = 0; i < this.enemies.size(); i++) {

            tempEnemy = this.getEnemies().get(i);
            tempEnemy.tick();

        }
        for (int i = 0; i < healthPacks.size(); i++) {
            tempHealthPack = this.healthPacks.get(i);
            tempHealthPack.tick();
        }
    }

    public void render(Graphics g){

        for (int i = 0; i < this.bullets.size(); i++) {
            tempBullet = this.bullets.get(i);
            tempBullet.render(g);
        }

        for (int i = 0; i < this.enemies.size(); i++) {

            tempEnemy = this.getEnemies().get(i);
            tempEnemy.render(g);

        }

        for (int i = 0; i < healthPacks.size(); i++) {
            tempHealthPack = this.healthPacks.get(i);
            tempHealthPack.render(g);
        }

    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);

    }

    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);

    }

    public void addEnemy(int enemyCount){
        for (int i = 0; i < enemyCount; i++) {
            addEnemy(new Enemy(random.nextInt(GameConstants.WIDTH * GameConstants.SCALE),0,game,this));
        }
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }

   public void addHealthPacks(HealthPacks health){
        this.healthPacks.add(health);
   }
   public void removeHealthPacks(HealthPacks health){
        this.healthPacks.remove(health);
   }

    public List<Bullet> getBullets() {
        return Collections.unmodifiableList(bullets);
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

}
