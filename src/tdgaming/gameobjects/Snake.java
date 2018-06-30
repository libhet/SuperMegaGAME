package tdgaming.gameobjects;

import tdgaming.Game;
import tdgaming.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake extends GameObject {

    private Game game;
    private ArrayList<GameObject> snakeElements;
    private SnakeHead snakeHead;

    public Snake(double x, double y, Game game, Textures textures) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.textures = textures;

        snakeHead = new SnakeHead(x, y, game, textures);
        snakeElements = new ArrayList<>();
        snakeElements.add(snakeHead);
        SnakeBodyElement bodyElement = new SnakeBodyElement(snakeHead, x, y + 32, textures);
        snakeElements.add(bodyElement);
        snakeElements.add(new SnakeTail(bodyElement, x, y + 64, textures));
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(textures.snakeHead,(int)x,(int)y,null);
        for (GameObject se : snakeElements){
            se.render(g);
        }
    }

    @Override
    public void tick() {
        for (GameObject se : snakeElements){
            se.tick();
        }
//        x += velX;
//        y += velY;
//
//        if (x <= -32 / 2)
//            x = game.getWidth();
//        if (y <= -32 / 2)
//            y = game.getHeight();
//        if (x > game.getWidth())
//            x = 0;
//        if (y > game.getHeight())
//            y = 0;
    }


    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void grow(){
        //
    }
}
