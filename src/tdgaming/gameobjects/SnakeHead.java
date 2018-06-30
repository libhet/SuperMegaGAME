package tdgaming.gameobjects;

import tdgaming.Game;
import tdgaming.Textures;

import java.awt.*;

public class SnakeHead extends GameObject {

    private Textures textures;
    private Game game;

    public SnakeHead(double x, double y, Game game, Textures textures) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.textures = textures;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(textures.snakeHead,(int)x,(int)y,null);
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
