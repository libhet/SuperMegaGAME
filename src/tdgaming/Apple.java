package tdgaming;

import javax.swing.text.GapContent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.management.PlatformLoggingMXBean;

public class Apple {

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    private double x;
    private double y;

    private BufferedImage apple;

    public Apple(double x, double y, Game game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        apple = ss.grabImage(1,1,32,32);
    }


    public void tick() {
//        this.x = this.x + 1.0;
    }

    public void render(Graphics g) {
        g.drawImage(apple,(int)x,(int)y,null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            x++;
        } else if(key == KeyEvent.VK_LEFT) {
            x--;
        } else if(key == KeyEvent.VK_UP) {
            y--;
        } else if(key == KeyEvent.VK_DOWN) {
            y++;
        }
    }

    public void keyReleased(KeyEvent e) {


    }
}
