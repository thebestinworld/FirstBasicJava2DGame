package controllers;

import constants.GameConstants;

import java.awt.*;

public class MenuController {

    private Rectangle playButton = new Rectangle(GameConstants.WIDTH/2+120,150,100,50);

    private Rectangle quitButton = new Rectangle(GameConstants.WIDTH/2+120,250,100,50);

    public void render(Graphics g){

            Graphics2D graphics2D = (Graphics2D) g;

            Font fontTitle = new Font("arial",Font.BOLD,50);
            g.setFont(fontTitle);
            g.setColor(Color.white);
            g.drawString("Space Shooter", GameConstants.WIDTH/2,100);

            Font fontButton = new Font("arial",Font.BOLD,30);
            g.setFont(fontButton);
            g.drawString("Play",playButton.x+19,playButton.y+30);

            g.drawString("Quit",quitButton.x+19,quitButton.y+30);
            graphics2D.draw(playButton);

            graphics2D.draw(quitButton);
    }
}
