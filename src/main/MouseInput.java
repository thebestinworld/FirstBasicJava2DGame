package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
       // public Rectangle playButton = new Rectangle(Game.WIDTH/2+120,150,100,50);
       // public Rectangle helpButton = new Rectangle(Game.WIDTH/2+120,250,100,50);
        //public Rectangle quitButton = new Rectangle(Game.WIDTH/2+120,350,100,50);
        if(mx>= Game.WIDTH/2+120&&mx<=Game.WIDTH / 2 +220){
            if(my >= 150 && my<=200){
                Game.state=Game.STATE.GAME;
            }
        }

        if(mx>= Game.WIDTH/2+120&&mx<=Game.WIDTH / 2 +220){
            if(my >= 350 && my<=400){
                System.exit(1);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}