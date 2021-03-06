import Entities.Bullet;
import Entities.Entity;
import Entities.Ship;
import Graphics.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class SpaceGame extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int MAX_UPS = 60;

    private Thread mainThread;
    private int maxFPS = 60;
    private boolean gameIsRunning = false;
    private ArrayList<Integer> keysPressed = new ArrayList<Integer>();
    private JPanel mainPanel;
    private Renderer gameRenderer;

    private int mouseX;
    private int mouseY;

    private static SpaceGame game;


    private ArrayList<Entity> gameEntities = new ArrayList<Entity>();
    private ArrayList<Entity> entitiesToRender = new ArrayList<Entity>();

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

        gameRenderer = new Renderer(WIDTH, HEIGHT);

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
        mainPanel.addMouseMotionListener(new mouseMotionListner());

        this.add(mainPanel);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");
        game = new SpaceGame("Space Dodgeball");
        game.startNewGame();
        game.toFront();
    }


    private void startNewGame() {
        gameIsRunning = true;
        Ship ship1 = new Ship(200, 200, 0, 80, 80, "Ship1.png");
        gameEntities.add(ship1);

        Ship ship2 = new Ship(800, 500, 180, 80, 80, "Ship2.png");
        gameEntities.add(ship2);

        mainThread = new Thread(this, "main");
        mainThread.start();
    }

    @Override
    public void run() {
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
        final double nanoSecsBetweenUpdates = 1000000000.0 / MAX_UPS;
        final double nanoSecsBetweenFrames = 1000000000.0 / maxFPS;

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
            updateDelta = updateDelta + (currentTime - updateLastTime) / nanoSecsBetweenUpdates;
            updateLastTime = currentTime;

            currentTime = System.nanoTime();
            frameDelta = frameDelta + (currentTime - frameLastTime) / nanoSecsBetweenFrames;
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
            if ((System.nanoTime() / 1000000) - (secondTimer / 1000000) > 1000) {
                totalSecondsRunning++;
                secondTimer = System.nanoTime();
                System.out.println("UPS: " + updatesPerSec + ", FPS: " + fps);

                updatesPerSec = 0;
                fps = 0;
            }

        }
    }

    private void render() {
        entitiesToRender.clear();

        for (int i = 0; i < gameEntities.size(); i++) {
            entitiesToRender.add(gameEntities.get(i));

            if (gameEntities.get(i) instanceof Ship) {
                Bullet bullet = ((Ship) gameEntities.get(i)).getBullet();

                if (bullet.isInFlight()) {
                    entitiesToRender.add(bullet);
                }

            }
        }


        gameRenderer.setEntitiesToRender(entitiesToRender);

        gameRenderer.setWindowWidth(this.getWidth());
        gameRenderer.setWindowHeight(this.getHeight());
        gameRenderer.repaint();
    }

    private void update() {
        for (int i = 0; i < keysPressed.size(); i++) {
            if (keysPressed.get(i) == KeyEvent.VK_D) {
                gameEntities.get(0).setAngle(gameEntities.get(0).getAngle() + 3);
            } else if (keysPressed.get(i) == KeyEvent.VK_A) {
                gameEntities.get(0).setAngle(gameEntities.get(0).getAngle() - 3);
            } else if (keysPressed.get(i) == KeyEvent.VK_S) {
                gameEntities.get(0).reverseAcceleration();
            } else if (keysPressed.get(i) == KeyEvent.VK_W) {
                gameEntities.get(0).accelerate();
            } else if (keysPressed.get(i) == KeyEvent.VK_UP) {
                gameEntities.get(1).accelerate();
            } else if (keysPressed.get(i) == KeyEvent.VK_DOWN) {
                gameEntities.get(1).reverseAcceleration();
            } else if (keysPressed.get(i) == KeyEvent.VK_LEFT) {
                gameEntities.get(1).setAngle(gameEntities.get(1).getAngle() - 3);
            } else if (keysPressed.get(i) == KeyEvent.VK_RIGHT) {
                gameEntities.get(1).setAngle(gameEntities.get(1).getAngle() + 3);
            } else if (keysPressed.get(i) == KeyEvent.VK_SPACE) {
                if (gameEntities.get(0) instanceof Ship) {
                    ((Ship) gameEntities.get(0)).shoot();
                }
            } else if (keysPressed.get(i) == KeyEvent.VK_NUMPAD0) {
                if (gameEntities.get(1) instanceof Ship) {
                    ((Ship) gameEntities.get(1)).shoot();
                }
            }
            else if (keysPressed.get(i) == KeyEvent.VK_R){
                this.dispose();
                keysPressed.remove(i);
                main(new String[1]);


            }

        }

        for (int i = 0; i < gameEntities.size(); i++) {
            gameEntities.get(i).update();

            if (gameEntities.get(i) instanceof Ship) {
                if ( ((Ship) gameEntities.get(i)).getBullet().isInFlight()){
                    ((Ship) gameEntities.get(i)).getBullet().move();
                }

            }
        }

        if (gameEntities.get(0) instanceof  Ship && gameEntities.get(1) instanceof Ship){
            if (checkCollision(gameEntities.get(0), gameEntities.get(1))){
                ((Ship) gameEntities.get(0)).isKill("Ship1-ded.png");
                ((Ship) gameEntities.get(1)).isKill("Ship2-ded.png");
            }
            else if(checkCollision(gameEntities.get(0),((Ship) gameEntities.get(0)).getBullet())){
                if (((Ship) gameEntities.get(0)).getBullet().getSafetyTime() == 0) {
                    ((Ship) gameEntities.get(0)).isKill("Ship1-ded.png");
                }
            }
            else if (checkCollision(gameEntities.get(0), ((Ship) gameEntities.get(1)).getBullet())){
                if (((Ship) gameEntities.get(1)).getBullet().getSafetyTime() == 0) {
                    ((Ship) gameEntities.get(0)).isKill("Ship1-ded.png");
                }
            }

            else if(checkCollision(gameEntities.get(1),((Ship) gameEntities.get(0)).getBullet())){
                if (((Ship) gameEntities.get(0)).getBullet().getSafetyTime() == 0) {
                    ((Ship) gameEntities.get(1)).isKill("Ship2-ded.png");
                }
            }
            else if (checkCollision(gameEntities.get(1), ((Ship) gameEntities.get(1)).getBullet())){
                if (((Ship) gameEntities.get(1)).getBullet().getSafetyTime() == 0) {
                    ((Ship) gameEntities.get(1)).isKill("Ship2-ded.png");
                }
            }

        }

        //Edge of board
        for (int i = 0; i < gameEntities.size(); i++) {
            if (gameEntities.get(i).getxPos() + gameEntities.get(i).getWidth() < 0) {
                gameEntities.get(i).setxPos(mainPanel.getWidth());
            }
            if (gameEntities.get(i).getxPos() > mainPanel.getWidth()) {
                gameEntities.get(i).setxPos(0 - gameEntities.get(i).getWidth());
            }
            if (gameEntities.get(i).getyPos() + gameEntities.get(i).getHeight() < 0) {
                gameEntities.get(i).setyPos(mainPanel.getHeight());
            }
            if (gameEntities.get(i).getyPos() > mainPanel.getHeight()) {
                gameEntities.get(i).setyPos(0 - gameEntities.get(i).getHeight());
            }
            //BULLETS
            if (((Ship)gameEntities.get(i)).getBullet().getxPos() < 0) {
                if (((Ship) gameEntities.get(i)).getBullet().getAngle() > 0) {
                    ((Ship) gameEntities.get(i)).getBullet().setAngle(180 - ((Ship)gameEntities.get(i)).getBullet().getAngle());
                } else {
                    ((Ship) gameEntities.get(i)).getBullet().setAngle(-1*(180 + ((Ship)gameEntities.get(i)).getBullet().getAngle()));
                }
            }
            if (((Ship)gameEntities.get(i)).getBullet().getxPos() + ((Ship)gameEntities.get(i)).getBullet().getWidth() > mainPanel.getWidth()) {
                if (((Ship) gameEntities.get(i)).getBullet().getAngle() > 0) {
                    ((Ship) gameEntities.get(i)).getBullet().setAngle((180 - ((Ship)gameEntities.get(i)).getBullet().getAngle()));
                    System.out.println("Reached");
                } else {
                    ((Ship) gameEntities.get(i)).getBullet().setAngle(-1*(180 + ((Ship)gameEntities.get(i)).getBullet().getAngle()));
                }
                if (((Ship) gameEntities.get(i)).getBullet().getAngle() == 0) {
                    ((Ship) gameEntities.get(i)).getBullet().setAngle(180);
                }
            }
            if (((Ship)gameEntities.get(i)).getBullet().getyPos() < 0) {
                ((Ship)gameEntities.get(i)).getBullet().setAngle(-1*((Ship)gameEntities.get(i)).getBullet().getAngle());
            }
            if (((Ship)gameEntities.get(i)).getBullet().getyPos() + ((Ship)gameEntities.get(i)).getBullet().getHeight() > mainPanel.getHeight()) {
                ((Ship)gameEntities.get(i)).getBullet().setAngle(-1*((Ship)gameEntities.get(i)).getBullet().getAngle());
            }


        }


    }

    public boolean checkCollision (Entity entity1, Entity entity2){
        boolean collisionDetected = false;

        int entity1Y = (int)entity1.getyPos();
        int entity1X = (int)entity1.getxPos();
        int entity1Height = entity1.getHeight();
        int entity1Width = entity1.getWidth();

        int entity2Y = (int)entity2.getyPos();
        int entity2X = (int)entity2.getxPos();
        int entity2Height = entity2.getHeight();
        int entity2Width = entity2.getWidth();

        if (entity2X+entity2Width >= entity1X && entity2X <= entity1X+entity1Width && entity2Y+entity2Height >= entity1Y && entity2Y <= entity1Y+entity1Height) {
            collisionDetected = true;
        }


        return collisionDetected;
    }

    public class spaceGameKeyListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent arg0) {

            if (!keysPressed.contains(arg0.getKeyCode())) {
                keysPressed.add(new Integer(arg0.getKeyCode()));
            }
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            keysPressed.remove(new Integer(arg0.getKeyCode()));
        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }
    }

    public class mouseMotionListner implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
        }
    }

    public ArrayList<Entity> getGameEntities() {
        return gameEntities;
    }

    public void setGameEntities(ArrayList<Entity> gameEntities) {
        this.gameEntities = gameEntities;
    }
}
