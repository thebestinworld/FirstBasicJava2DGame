package main;

import interfaces.EntityA;
import interfaces.EntityB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH=320;
    public static final int HEIGHT=WIDTH/12*9;
    public static final int SCALE=2;
    public static final String TITLE = "Space Shooter";

    private boolean isRunning = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);


    private BufferedImage background=null;
    private BufferedImage playerImage  = null;
    private BufferedImage bulletImage = null;
    private BufferedImage enemyImage = null;

    private boolean isShooting = false;
    private int enemyCount = 5;
    private int enemyKilled = 0;
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    public static  int health = 200;

    public  static enum STATE{
        MENU,
        GAME
    };

    public static STATE state = STATE.MENU;
    private Player player;
    private Controller controller;
    private Menu menu;

    public void  init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            playerImage  = loader.loadImage("/res/spaceship.png");
            bulletImage=loader.loadImage("/res/bullet.png");
            enemyImage=loader.loadImage("/res/enemy.png");
            background=loader.loadImage("/res/background.png");


        }catch (IOException ex){
            ex.printStackTrace();

        }

        this.addKeyListener(new KeyInputHandler(this));
        this.addMouseListener(new MouseInput());

        controller = new Controller(this);
        player = new Player(200,200,this,controller);
        menu = new Menu();
        ea = controller.getEntitiesA();
        eb = controller.getEntitiesB();
        controller.addEnemy(enemyCount);
    }

    private synchronized void start(){
        if(isRunning){
            return;
        }
        isRunning = true;
        thread=new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!isRunning){
            return;
        }
        isRunning=false;
        try {
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
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer =System.currentTimeMillis();

            while (isRunning){
                long now = System.nanoTime();
                delta +=(now-lastTime)/ns;
                lastTime = now;
                if(delta>=1){
                    tick();
                    updates++;
                    delta--;
                }
                render();
                frames++;

                if(System.currentTimeMillis()-timer>1000){
                    timer+=1000;
                    System.out.println(updates + " Ticks, " + "FPS " + frames);
                    updates=0;
                    frames=0;
                }



            }
            stop();
    }

    private void tick() {
        if(state == STATE.GAME){
            player.tick();
            controller.tick();
        }
        if(health<=0){
            System.exit(1);
        }

        if(enemyKilled>=enemyCount){
            enemyCount +=2;
            enemyKilled=0;
            controller.addEnemy(enemyCount);
        }

    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //
        g.drawImage(image,0,0,getWidth(),getHeight(),this);

        g.drawImage(background,0,0,null);
        if(state == STATE.GAME){
            player.render(g);
            controller.render(g);
            g.setColor(Color.GRAY);
            g.fillRect(10,10,200,20);

            g.setColor(Color.green);
            g.fillRect(10,10,health,20);

            g.setColor(Color.white);
            g.drawRect(10,10,200,20);
        }else if(state == STATE.MENU){
            menu.render(g);
        }

        //g.drawImage(playerImage,100,100,this);
        //
        g.dispose();
        bs.show();

    }


    public Game(){

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getPlayerImage(){
        return  playerImage;
    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(state == STATE.GAME){
            if(key==KeyEvent.VK_RIGHT){
                player.setVelX(5);

            }else if(key==KeyEvent.VK_LEFT){
                player.setVelX(-5);

            }else if(key==KeyEvent.VK_DOWN){
                player.setVelY(5);

            }else if(key==KeyEvent.VK_UP){
                player.setVelY(-5);

            }else if(key==KeyEvent.VK_SPACE && !isShooting){
                isShooting = true;
                controller.addEntityA(new Bullet(player.getX()-12,player.getY(),this));
                controller.addEntityA(new Bullet(player.getX()+32,player.getY(),this));

            }
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_RIGHT){
            player.setVelX(0);

        }else if(key==KeyEvent.VK_LEFT){
            player.setVelX(0);

        }else if(key==KeyEvent.VK_DOWN){
            player.setVelY(0);

        }else if(key==KeyEvent.VK_UP){
            player.setVelY(0);
        }else if(key==KeyEvent.VK_SPACE){
            isShooting = false;


        }
    }

    public BufferedImage getBulletImage() {
      return   this.bulletImage;
    }
    public BufferedImage getEnemyImage(){
        return  this.enemyImage;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }
}
