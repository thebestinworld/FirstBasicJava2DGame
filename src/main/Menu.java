package main;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Game.WIDTH/2+120,150,100,50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH/2+120,250,100,50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/2+120,350,100,50);

    public void render(Graphics g){

            Graphics2D graphics2D = (Graphics2D) g;

            Font fontTitle = new Font("arial",Font.BOLD,50);
            g.setFont(fontTitle);
            g.setColor(Color.white);
            g.drawString("Space Shooter", Game.WIDTH/2,100);

            Font fontButton = new Font("arial",Font.BOLD,30);
            g.setFont(fontButton);
            g.drawString("Play",playButton.x+19,playButton.y+30);
            g.drawString("Help",helpButton.x+19,helpButton.y+30);
            g.drawString("Quit",quitButton.x+19,quitButton.y+30);
            graphics2D.draw(playButton);
            graphics2D.draw(helpButton);
            graphics2D.draw(quitButton);
    }
}
