package tdgaming.gameobjects;

import tdgaming.Game;
import tdgaming.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private BufferedImage player;
    private Game game;

    public Player(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        player = ss.grabImage(1,1,32,32);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player,(int)x,(int)y,null);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x <= -32 / 2)
            x = game.getWidth();
        if (y <= -32 / 2)
            y = game.getHeight();
        if (x > game.getWidth())
            x = 0;
        if (y > game.getHeight())
            y = 0;
    }



}
