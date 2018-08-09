package main;

import interfaces.EntityA;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {

    private BufferedImage bullet;
    private Game game;

    public Bullet(double x , double y,Game game){


        super(x,y);
        bullet = game.getBulletImage();
        this.game = game;
    }

    @Override
    public void tick(){
        y-=5;

    }

    @Override
    public void render(Graphics g){
        g.drawImage(bullet,(int)x,(int)y,null);
    }

    @Override
    public double getX() {
        return 0;
    }


    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,48,48);
    }
}
