package tdgaming;

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage snakeHead, snakeBody, snakeTail, apple;
    private SpriteSheet spriteSheet;

    public Textures(Game game) {
        spriteSheet = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
    }

    private void getTextures() {
        snakeHead = spriteSheet.grabImage(1,2,32,32);
        snakeBody = spriteSheet.grabImage(1,3,32,32);
        snakeTail = spriteSheet.grabImage(1,4,32,32);
        apple = spriteSheet.grabImage(1,1,32,32);
    }
}
