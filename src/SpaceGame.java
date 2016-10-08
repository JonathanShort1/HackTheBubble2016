import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.*;
import Graphics.Renderer;

public class SpaceGame extends JFrame implements Runnable {
    private static final long serialVersionUID = -3009979543265247698L;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int MAX_UPS = 60;
    
    private Thread mainThread;
    private int maxFPS = 60;
    private boolean gameIsRunning = false;
    private JPanel mainPanel;
    private Renderer gameRenderer;

    private ArrayList<Entity> gameEntities = new ArrayList<Entity>();

    private ArrayList<Entity> entitiesToRender = new ArrayList<Entity>();
    private ArrayList<Integer> keysPressed = new ArrayList<Integer>();
    
    private static SpaceGame spaceGame;
    
    
    public static void main(String[] args) {
        spaceGame = new SpaceGame("Hello World");
        spaceGame.init();
        spaceGame.toFront();

        spaceGame.run();
        
        
    }
     
    public void init(){
        gameIsRunning = true;
        Ship testShip = new Ship(200,200,2);
        gameEntities.add(testShip);

    }
    
    public void run(){
        //Counts the number of seconds the thread has been running for
        long totalSecondsRunning = 0;

        //Used to count when a second has elapsed for displaying ups and fps.
        long secondTimer = System.nanoTime();

		/*
		 * Keeps track of the last time the program updated the thread
		 * or displayed a new frame.
		 */
        long updateLastTime = System.nanoTime();
        long frameLastTime = System.nanoTime();

		/*
		 * The number of nano seconds the program needs to wait before updating/
		 * displaying a frame.
		 */
        final double nanoSecsBetweenUpdates = 1000000000.0/MAX_UPS;
        final double nanoSecsBetweenFrames = 1000000000.0/maxFPS;

		/*
		 * When these deltas = 1, the number of nanoseconds above
		 * have passed.
		 */
        double updateDelta = 0;
        double frameDelta = 0;

        int fps = 0;
        int updatesPerSec = 0;

        gameRenderer.repaint();

        while (gameIsRunning) {
            long currentTime = System.nanoTime();
            updateDelta = updateDelta + (currentTime-updateLastTime)/nanoSecsBetweenUpdates;
            updateLastTime = currentTime;

            currentTime = System.nanoTime();
            frameDelta = frameDelta + (currentTime-frameLastTime)/nanoSecsBetweenFrames;
            frameLastTime = currentTime;

            if (updateDelta >= 1) {
                update();
                updateDelta = 0;
                updatesPerSec++;
            }


            if (frameDelta >= 1) {
                if (gameRenderer.isFrameRendered() == true) {
                    render();
                    fps++;
                    frameDelta = 0;
                }

            }



            //Done each Second
            if ((System.nanoTime()/1000000) - (secondTimer/1000000)>1000) {
                totalSecondsRunning++;
                secondTimer = System.nanoTime();
                System.out.println("UPS: "+updatesPerSec+", FPS: "+fps);

                updatesPerSec = 0;
                fps = 0;
            }

        }

    }
    
    public void update(){
        for (int i = 0; i < keysPressed.size(); i++) {
            System.out.println(keysPressed.get(i));
        }
    }
    
    public void render(){

        for (int i=0; i < gameEntities.size();i++){
            entitiesToRender.add(gameEntities.get(i));
        }

        gameRenderer.setEntitiesToRender(entitiesToRender);

        gameRenderer.setWindowWidth(this.getWidth());
        gameRenderer.setWindowHeight(this.getHeight());
        gameRenderer.repaint();
    }
    
    public SpaceGame(String title) {
        super(title);
        this.setResizable(true);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusableWindowState(true);
        this.setVisible(true);
        this.setIgnoreRepaint(false);
        
        System.setProperty("java.awt.headless", "false");
        
        gameRenderer = new Graphics.Renderer(WIDTH, HEIGHT);
        
        mainPanel = new JPanel();
        mainPanel.add(gameRenderer);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainPanel.setFocusable(true);
        mainPanel.setVisible(true);
        mainPanel.setLayout(new GridLayout());

    
        gameRenderer.setVisible(true);
        mainPanel.add(gameRenderer);
        mainPanel.addKeyListener(new spaceGameKeyListener());

        this.add(mainPanel);
    }

    public class spaceGameKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (!keysPressed.contains(keyEvent.getKeyCode())) {
                keysPressed.add(keyEvent.getKeyCode());
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keysPressed.contains(keyEvent.getKeyCode())) {
                keysPressed.remove(keyEvent.getKeyCode());
            }
        }
    }

}
