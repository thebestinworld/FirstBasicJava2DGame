package entities;

import interfaces.GameObject;
import loaders.BufferedImageLoader;
import controllers.EntityController;
import main.Game;
import controllers.PhysicsController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject {


    private double velX = 0;
    private double velY = 0;
    private BufferedImage player;
    private BufferedImageLoader loader;
    private Game game;
    private EntityController controller;
    private Integer health;

    public Player(double x, double y, Game game, EntityController controller){

        super(x,y);
        this.game=game;
        this.controller=controller;
        this.loader = new BufferedImageLoader();
        this.player = this.loadImage();
        this.health = 200;

    }

    public void tick(){
        x += velX;
        y += velY;

        if(x<=0){
            x=0;
        }
        if(x>=640-48){
            x=640-48;
        }
        if(y<=0){
            y=0;
        }
        if(y>=480-62){
            y=480-62;
        }
        for (int i = 0; i < this.controller.getEnemies().size(); i++) {
            Enemy tempEnemy = this.controller.getEnemies().get(i);

            if(PhysicsController.Collision(this,  tempEnemy )){
                this.controller.removeEnemy(tempEnemy);
                this.game.setEnemyKilled(game.getEnemyKilled()+1);
                this.setHealth(this.getHealth() - 10);

            }
        }
    }

    public void render(Graphics g){
        g.drawImage(this.player,(int)x,(int)y,null);

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


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,48,48);
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    private BufferedImage loadImage(){
        try {
            return this.loader.loadImage("/res/spaceship.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

