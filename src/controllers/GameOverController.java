package controllers;

import constants.GameConstants;

import java.awt.*;

public class GameOverController {

    private Rectangle quitButton = new Rectangle(GameConstants.WIDTH/2+120,250,120,50);

    public void render(Graphics g){

        Graphics2D graphics2D = (Graphics2D) g;

        Font fontTitle = new Font("arial",Font.BOLD,50);
        g.setFont(fontTitle);
        g.setColor(Color.white);
        g.drawString("Game Over", GameConstants.WIDTH/2+30,200);

        Font fontButton = new Font("arial",Font.BOLD,30);
        g.setFont(fontButton);

        g.drawString("Quit",quitButton.x+25,quitButton.y+30);


        graphics2D.draw(quitButton);
    }
}
