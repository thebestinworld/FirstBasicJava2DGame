package main;

import interfaces.EntityA;
import interfaces.EntityB;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private LinkedList<EntityA> entitiesA = new LinkedList<>();
    private LinkedList<EntityB> entitiesB = new LinkedList<>();
    EntityA tempEntityA ;
    EntityB tempEntityB;
   // private LinkedList<Bullet> b = new LinkedList<>();
   // private LinkedList<Enemy> e = new LinkedList<>();
    Random random = new Random();
   // private Bullet tempBullet;
   // private Enemy tempEnemy;
    private Game game;

    public Controller(Game game){
        this.game=game;
      // addEntityB(new Enemy(random.nextInt(Game.WIDTH * Game.SCALE),0,game));

        

    }

    public void tick(){
        for (int i = 0; i < entitiesA.size() ; i++) {
            tempEntityA = entitiesA.get(i);
            tempEntityA.tick();
        }

        for (int i = 0; i < entitiesB.size() ; i++) {
            tempEntityB = entitiesB.get(i);
            tempEntityB.tick();
        }
      /*  for (int i = 0; i < b.size(); i++) {
            tempBullet = b.get(i);
            if(tempBullet.getY()< 0){
                removeBullet(tempBullet);
            }
            tempBullet.tick();
        }
        for (int i = 0; i < e.size(); i++) {
            tempEnemy = e.get(i);

            tempEnemy.tick();
        }
        */
    }

    public void render(Graphics g){

        for (int i = 0; i < entitiesA.size() ; i++) {
            tempEntityA = entitiesA.get(i);
            tempEntityA.render(g     );
        }
        for (int i = 0; i < entitiesB.size() ; i++) {
            tempEntityB = entitiesB.get(i);
            tempEntityB.render(g);
        }

      /*  for (int i = 0; i < b.size(); i++) {
            tempBullet = b.get(i);
            tempBullet.render(g);
        }
        for (int i = 0; i < e.size(); i++) {
            tempEnemy = e.get(i);
            tempEnemy.render(g);
        }
        */
    }
   /* public void addBullet(Bullet bullet){
        b.add(bullet);

    }
    public void removeBullet(Bullet bullet){
        b.remove(bullet);
    }

    public void addEnemy(Enemy enemy){
        e.add(enemy);

    }
    public void removeEnemy(Enemy enemy){
        e.remove(enemy);
    }
    */
    public void addEntityA(EntityA entity){
        entitiesA.add(entity);
    }

    public void removeEntityA(EntityA entity){
        entitiesA.remove(entity);
    }

    public void addEntityB(EntityB entity){
        entitiesB.add(entity);
    }

    public void removeEntityB(EntityB entity){
        entitiesB.remove(entity);
    }

    public void addEnemy(int enemyCount){
        for (int i = 0; i < enemyCount; i++) {
            addEntityB(new Enemy(random.nextInt(Game.WIDTH * Game.SCALE),0,game,this));
        }
    }

    public LinkedList<EntityA> getEntitiesA() {
        return entitiesA;
    }

    public LinkedList<EntityB> getEntitiesB() {
        return entitiesB;
    }
}
