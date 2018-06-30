package tdgaming;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable   {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 320;
    public static final int SCALE = 2;
    public final String TITLE = "Snake!";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;


/// GAME OBJECTS //////////////////////////////////////////

    private Apple apple;

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
        BufferedImageLoader loader = new BufferedImageLoader();
        try{

            spriteSheet = loader.loadImage("data/apple.png");

        }catch (IOException e) {
            e.printStackTrace();
        }
/// INIT GAME OBJECTS ////////////////////////////////////

        addKeyListener(new KeyInput(this));

        apple = new Apple(100,100, this);

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

        apple.tick();

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

        apple.render(g);

////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            apple.setX(apple.getX() + 5);
        } else if(key == KeyEvent.VK_LEFT) {
            apple.setX(apple.getX() - 5);
        } else if(key == KeyEvent.VK_UP) {
            apple.setY(apple.getY() - 5);
        } else if(key == KeyEvent.VK_DOWN) {
            apple.setY(apple.getY() + 5);;
        }
    }

    public void keyReleased(KeyEvent e) {


    }

}
