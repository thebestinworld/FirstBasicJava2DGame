package main;

import interfaces.EntityA;
import interfaces.EntityB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {


    private BufferedImage enemy;
    Random random = new Random();
    private Game game;
    private Controller controller;

    public Enemy(double x, double y,Game game,Controller controller){

        super(x,y);
        this.enemy = game.getEnemyImage();
        this.game=game;
        this.controller=controller;
    }

    @Override
    public void tick(){
        y+=(random.nextInt(3)+1);
        if(y > (Game.HEIGHT*Game.SCALE)){
            y = 0;
            x = random.nextInt(640);
        }
        for (int i = 0; i < game.ea.size(); i++) {
            EntityA tempEntity = game.ea.get(i);
            if(Physics.Collision(this,  tempEntity  )){
                controller.removeEntityB(this);
                controller.removeEntityA(tempEntity);
                game.setEnemyKilled(game.getEnemyKilled()+1);
            }
        }

    }

    @Override
    public void render(Graphics g){

        g.drawImage(enemy,(int)x,(int)y,null);
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,48,48);
    }
}
