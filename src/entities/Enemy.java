package entities;

import constants.GameConstants;
import interfaces.GameObject;
import loaders.BufferedImageLoader;
import controllers.EntityController;
import main.Game;
import controllers.PhysicsController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends GameObject {


    private BufferedImage enemy;
    private BufferedImageLoader loader;
    private Random random = new Random();
    private Game game;
    private EntityController controller;

    public Enemy(double x, double y, Game game, EntityController controller){

        super(x,y);
        this.loader = new BufferedImageLoader();
        this.enemy = this.loadImage();

        this.game=game;
        this.controller=controller;
    }


    public void tick(){
        y += (this.random.nextInt(3)+1);

        if(y > (GameConstants.HEIGHT * GameConstants.SCALE)){
            y = 0;
            x = this.random.nextInt(640);
        }

        for (int i = 0; i < this.controller.getBullets().size(); i++) {
            Bullet tempBullet = this.controller.getBullets().get(i);
            if(PhysicsController.Collision(this,  tempBullet)){
                this.controller.removeEnemy(this);
                this.controller.removeBullet(tempBullet);
                this.game.setEnemyKilled(game.getEnemyKilled()+1);
            }
        }

    }


    public void render(Graphics g){

        g.drawImage(this.enemy,(int)x,(int)y,null);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,48,48);
    }

    private BufferedImage loadImage(){
        try {
            return this.loader.loadImage("/res/enemy.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
