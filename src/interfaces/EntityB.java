package interfaces;

import java.awt.*;

public interface EntityB {

    void tick();
    void render(Graphics g);
    double getX();
    double getY();
    Rectangle getBounds();
}
