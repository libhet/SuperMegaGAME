package tdgaming.gameobjects;

import tdgaming.Textures;

import java.awt.*;

public class SnakeBodyElement extends GameObject {

    private GameObject prevElement;

    public SnakeBodyElement(GameObject prevElement, double x, double y, Textures textures) {
        this.prevElement = prevElement;
        this.x = x;
        this.y = y;
        this.textures = textures;
//        y = snakeHead.getY() + textures.getTexturesHeight();
//        x = snakeHead.getX();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(textures.snakeBody,(int)x,(int)y,null);
    }

    @Override
    public void tick() {
        y = prevElement.getY() + textures.getTexturesHeight();
        x = prevElement.getX();
    }
}
