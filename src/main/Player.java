package main;

import interfaces.EntityA;
import interfaces.EntityB;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {


    private double velX = 0;
    private double velY = 0;
    private BufferedImage player;
    private Game game;
    private Controller controller;

    public Player(double x, double y,Game game,Controller controller){

        super(x,y);
        this.game=game;
        this.controller=controller;
        player = game.getPlayerImage();

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
        for (int i = 0; i < game.eb.size(); i++) {
            EntityB tempEntity = game.eb.get(i);
            if(Physics.Collision(this,  tempEntity  )){
               controller.removeEntityB(tempEntity);
               game.setEnemyKilled(game.getEnemyKilled()+1);
               Game.health -=10;
            }
        }
    }

    public void render(Graphics g){
        g.drawImage(player,(int)x,(int)y,48,48,null);

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

    @Override
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
}

