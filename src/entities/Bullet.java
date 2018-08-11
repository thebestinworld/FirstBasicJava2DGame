package entities;

import interfaces.GameObject;
import loaders.BufferedImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet extends GameObject {

    private BufferedImage bullet;
    private BufferedImageLoader loader;

    public Bullet(double x , double y){


        super(x,y);
        this.loader=new BufferedImageLoader();
        this.bullet = this.loadImage();

    }


    public void tick(){
        y-=5;
    }


    public void render(Graphics g){
        g.drawImage(this.bullet,(int)x,(int)y,24,24,null);
    }


    public double getX() {
        return this.x;
    }



    public double getY() {
        return this.y;
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }


    private BufferedImage loadImage(){
        try {
            return this.loader.loadImage("/res/bullet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
