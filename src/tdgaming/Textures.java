package tdgaming;

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage player, apple;
    private SpriteSheet spriteSheet;

    public Textures(Game game) {
        spriteSheet = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
    }

    private void getTextures() {
        player = spriteSheet.grabImage(1,1,32,32);
        apple = spriteSheet.grabImage(1,1,32,32);
    }
}
