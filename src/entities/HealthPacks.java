package entities;

import controllers.EntityController;
import controllers.PhysicsController;
import interfaces.GameObject;
import loaders.BufferedImageLoader;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealthPacks extends GameObject {

    private BufferedImage health;
    private BufferedImageLoader loader;
    private Player player;
    private EntityController controller;

    public HealthPacks(double x, double y, Player player, EntityController controller) {
        super(x, y);
        this.player = player;
        this.controller = controller;
        this.loader=new BufferedImageLoader();
        this.health = this.loadImage();
    }




    public void tick(){
        if(PhysicsController.Collision( player,this)){
            if(this.player.getHealth() >= 200){
                this.controller.removeHealthPacks(this);
            }else {
                this.player.setHealth(this.player.getHealth() + 10);
                this.controller.removeHealthPacks(this);
            }
        }
    }


    public void render(Graphics g){
        g.drawImage(this.health,(int)x,(int)y,32,32,null);
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
            return this.loader.loadImage("/res/health.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
