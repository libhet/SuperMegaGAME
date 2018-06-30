package tdgaming.gameobjects;

import tdgaming.Textures;

import java.awt.*;

public class SnakeTail extends GameObject {

    private GameObject prevElement;

    public SnakeTail(GameObject prevElement, double x, double y, Textures textures) {
        this.prevElement = prevElement;
        this.x = x;
        this.y = y;
        this.textures = textures;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(textures.snakeTail,(int)x,(int)y,null);
    }

    @Override
    public void tick() {
        y = prevElement.getY() + textures.getTexturesHeight();
        x = prevElement.getX();
    }
}
