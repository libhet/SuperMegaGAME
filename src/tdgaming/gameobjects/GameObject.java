package tdgaming.gameobjects;

import java.awt.*;

public abstract class GameObject {
    protected float x,y;
    protected float velX,velY;

    abstract public void render(Graphics g);
    abstract public void tick();

}
