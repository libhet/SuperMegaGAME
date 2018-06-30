package tdgaming;


import tdgaming.gameobjects.Apple;
import tdgaming.gameobjects.GameObject;
import tdgaming.gameobjects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable   {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private static final int SCALE = 2;
    private final String TITLE = "Snake!";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;


/// GAME OBJECTS //////////////////////////////////////////

    private Textures textures;
    private Apple apple;
    private Player player;
    private ArrayList<GameObject> gameObjects;

/// GETTERS ///////////////////////////////////////////////

    public static int getWIDTH()    {return WIDTH;}
    public static int getHEIGHT()   {return HEIGHT;}
    public static int getSCALE()    {return SCALE;}
    public String getTITLE()        {return TITLE;}
    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

/// INITIALIZATION ////////////////////////////////////////

    public void init() {
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{

            spriteSheet = loader.loadImage("data/apple.png");

        }catch (IOException e) {
            e.printStackTrace();
        }
/// INIT GAME OBJECTS ////////////////////////////////////

        addKeyListener(new KeyInput(this));

        textures = new Textures(this);
        apple = new Apple(100,100, this, textures);
        player = new Player(100,100, this, textures);
        gameObjects = new ArrayList<>();
        gameObjects.add(apple);
        gameObjects.add(player);

//////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));


        JFrame frame = new JFrame(game.getTITLE());
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }



    private synchronized void start(){
        if(running)
            return;

        running = true;
        thread  = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running)
            return;

        running = false;
        try{
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);

    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000_000_000 / amountOfTicks;
        double delta = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                tick();
                delta--;
            }
            render();
        }
        stop();
    }
////////////////////////////////////////////////////////
    private void tick(){

        for (GameObject go : gameObjects)
            go.tick();

    }

////////////////////////////////////////////////////////
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
/// DRAW ///////////////////////////////////////////////

        g.drawImage(image,0,0, getWidth(),getHeight(), this);

/// RENDER /////////////////////////////////////////////

        //apple.render(g);
        for (GameObject o : gameObjects)
            o.render(g);

////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            player.setVelX(5);
        } else if(key == KeyEvent.VK_LEFT) {
            player.setVelX(- 5);
        } else if(key == KeyEvent.VK_UP) {
            player.setVelY(-5);
        } else if(key == KeyEvent.VK_DOWN) {
            player.setVelY(5);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            player.setVelX(0);
        } else if(key == KeyEvent.VK_LEFT) {
            player.setVelX(0);
        } else if(key == KeyEvent.VK_UP) {
            player.setVelY(0);
        } else if(key == KeyEvent.VK_DOWN) {
            player.setVelY(0);
        }

    }

}
