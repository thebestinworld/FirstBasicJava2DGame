package inputHandlers;

import constants.GameConstants;
import enums.GameState;
import main.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(game.getState()== GameState.MENU) {
            if (mx >= GameConstants.WIDTH / 2 + 120 && mx <= GameConstants.WIDTH / 2 + 220 ) {
                if (my >= 150 && my <= 200) {
                    this.game.setState(GameState.GAME);
                }
            }

            if (mx >= GameConstants.WIDTH / 2 + 120 && mx <= GameConstants.WIDTH / 2 + 220) {
                if (my >= 250 && my <= 400) {
                    System.exit(1);
                }
            }
        }else if(game.getState()== GameState.GAME_OVER){

            if (mx >= GameConstants.WIDTH / 2 + 120 && mx <= GameConstants.WIDTH / 2 + 220) {
                if (my >= 250 && my <= 400) {
                    System.exit(1);
                }
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
