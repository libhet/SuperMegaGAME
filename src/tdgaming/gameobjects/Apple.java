package tdgaming.gameobjects;

import tdgaming.Game;
import tdgaming.Textures;

import java.awt.*;

public class Apple extends GameObject {

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }


    public Apple(double x, double y, Game game, Textures textures) {
        this.x = x;
        this.y = y;
        this.textures = textures;

    }


    @Override
    public void tick() {
//        this.x = this.x + 1.0;
//
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(textures.apple,(int)x,(int)y,null);
    }

//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if(key == KeyEvent.VK_RIGHT) {
//            x++;
//        } else if(key == KeyEvent.VK_LEFT) {
//            x--;
//        } else if(key == KeyEvent.VK_UP) {
//            y--;
//        } else if(key == KeyEvent.VK_DOWN) {
//            y++;
//        }
//    }
//
//    public void keyReleased(KeyEvent e) {
//
//
//    }
}
