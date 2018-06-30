package tdgaming;

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage snakeHead, snakeBody, snakeTail, apple;
    private SpriteSheet spriteSheet;
    private int width = 32, height = 32;

    public Textures(Game game) {
        spriteSheet = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
    }

    private void getTextures() {
        snakeHead = spriteSheet.grabImage(1,2,width, height);
        snakeBody = spriteSheet.grabImage(1,3,width, height);
        snakeTail = spriteSheet.grabImage(1,4,width, height);
        apple = spriteSheet.grabImage(1,1,width, height);
    }

    public int getTexturesWidth() {
        return width;
    }

    public int getTexturesHeight() {
        return height;
    }
}
