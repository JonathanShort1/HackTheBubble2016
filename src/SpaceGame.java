import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
    
    private static SpaceGame spaceGame;
    
    
    public static void main(String[] args) {
        spaceGame = new SpaceGame("Hello World");
        spaceGame.init();
        spaceGame.toFront();
        
        
    }
     
    public void init(){
        
    }
    
    public void run(){
        
    }
    
    public void update(){
        
    }
    
    public void render(){
        
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
        
        //gameRenderer = new Graphics.Renderer(WIDTH, HEIGHT);
        
        mainPanel = new JPanel();
        //mainPanel.add(gameRenderer);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainPanel.setFocusable(true);
        mainPanel.setVisible(true);
        mainPanel.setLayout(new GridLayout());

    
        //gameRenderer.setVisible(true);
       // mainPanel.add(gameRenderer);

        this.add(mainPanel);
    }

}
