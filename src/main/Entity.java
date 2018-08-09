package main;

import java.awt.*;

public interface Entity {

    void tick();
    void render(Graphics g);
    double getX();
    double getY();
}
