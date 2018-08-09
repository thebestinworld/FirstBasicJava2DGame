package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {

    private Game game;

    public KeyInputHandler(Game game){
        this.game=game;
    }

    public void keyPressed(KeyEvent e){
        this.game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        this.game.keyReleased(e);
    }
}
