package main;

import constants.GameConstants;
import controllers.EntityController;
import controllers.MenuController;
import entities.Bullet;
import entities.HealthPacks;
import entities.Player;
import enums.GameState;
import inputHandlers.KeyInputHandler;
import inputHandlers.MouseInput;
import loaders.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private GameState state;
    private BufferedImage background = null;
    private Player player;
    private EntityController controller;
    private MenuController menu;
    private boolean isShooting = false;
    private Random random;

    private int enemyCount = 5;
    private int enemyKilled = 0;



    public Game(){

    }

    private void  init(){

        requestFocus();

        BufferedImageLoader loader = new BufferedImageLoader();

        try{
            background=loader.loadImage("/res/background.png");
        }catch (IOException ex){
            ex.printStackTrace();

        }

        this.addKeyListener(new KeyInputHandler(this));
        this.addMouseListener(new MouseInput(this));

        this.random = new Random();
        this.controller = new EntityController(this);
        this.player = new Player(200,200,this,controller);
        this.menu = new MenuController();
        this.controller.addEnemy(enemyCount);
        this.controller.addHealthPacks(new HealthPacks(random.nextInt(GameConstants.WIDTH * GameConstants.SCALE) ,
                random.nextInt(GameConstants.HEIGHT* GameConstants.SCALE)
                ,this.player,this.controller));
        this.state = GameState.MENU;
    }

    private synchronized void start(){
        if(this.isRunning){
            return;
        }
        this.isRunning = true;
        this.thread=new Thread(this);
        this.thread.start();
    }

    private synchronized void stop(){
        if(!this.isRunning){
            return;
        }
        this.isRunning=false;
        try {
            this.thread.join();
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

            while (this.isRunning){
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
        if(this.state == GameState.GAME){
            this.player.tick();
            this.controller.tick();
        }
        if(player.getHealth() <= 0){
            System.exit(1);
        }

        if(this.enemyKilled >= this.enemyCount){
            this.enemyCount +=2;
            this.enemyKilled=0;
            this.controller.addEnemy(this.enemyCount);
            this.controller.addHealthPacks(new HealthPacks(random.nextInt(GameConstants.WIDTH * GameConstants.SCALE) ,
                    random.nextInt(GameConstants.HEIGHT* GameConstants.SCALE)
                    ,this.player,this.controller));
        }

    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Render
        g.drawImage(background,0,0,null);
        if(this.state == GameState.GAME){
            this.player.render(g);
            this.controller.render(g);
            g.setColor(Color.GRAY);

            g.fillRect(10,10,200,20);

            g.setColor(Color.green);
            g.fillRect(10,10, this.player.getHealth(),20);

            g.setColor(Color.white);
            g.drawRect(10,10,200,20);
        }else if(this.state == GameState.MENU){
            this.menu.render(g);
        }
        //
        g.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(this.state == GameState.GAME){
            if(key==KeyEvent.VK_RIGHT){
                this.player.setVelX(5);

            }else if(key==KeyEvent.VK_LEFT){
                this.player.setVelX(-5);

            }else if(key==KeyEvent.VK_DOWN){
                this.player.setVelY(5);

            }else if(key==KeyEvent.VK_UP){
                this.player.setVelY(-5);

            }else if(key==KeyEvent.VK_SPACE && !isShooting){
                this.isShooting = true;
                this.controller.addBullet(new Bullet(player.getX()-12,player.getY()));
                this.controller.addBullet(new Bullet(player.getX()+32,player.getY()));

            }
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_RIGHT){
            this.player.setVelX(0);

        }else if(key==KeyEvent.VK_LEFT){
            this.player.setVelX(0);

        }else if(key==KeyEvent.VK_DOWN){
            this.player.setVelY(0);

        }else if(key==KeyEvent.VK_UP){
            this.player.setVelY(0);
        }else if(key==KeyEvent.VK_SPACE){
            this.isShooting = false;

        }
    }

    public int getEnemyCount() {
        return this.enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyKilled() {
        return this.enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public EntityController getController() {
        return this.controller;
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(GameConstants.WIDTH * GameConstants.SCALE,GameConstants.HEIGHT * GameConstants.SCALE));
        game.setMaximumSize(new Dimension(GameConstants.WIDTH * GameConstants.SCALE,GameConstants.HEIGHT * GameConstants.SCALE));
        game.setMinimumSize(new Dimension(GameConstants.WIDTH * GameConstants.SCALE,GameConstants.HEIGHT * GameConstants.SCALE));

        JFrame frame = new JFrame(GameConstants.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }









}
