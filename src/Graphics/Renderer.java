package Graphics;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JLabel;

import Entities.Entity;

public class Renderer extends JLabel {
    private static final long serialVersionUID = 976977070478626642L;
    private int width;
    private int height;

    private int windowWidth;
    private int windowHeight;

    private ArrayList<Entity> entitiesToRender = new ArrayList<Entity>();
    private VolatileImage renderedGame;
    private Graphics2D g2d;

    private int currentFPS;
    private int currentUPS;
    private int totalNumberOfEntities;

    private boolean frameRendered = true;

    public Renderer(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        this.setLayout(null);

        System.out.println("Width: " + width + "  Height: " + height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setFrameRendered(false);
        super.paintComponent(g);

        if (renderedGame == null || renderedGame.validate(getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {

            renderedGame = createVolatileImage(width, height);

        }

        g2d = renderedGame.createGraphics();

        //Draws a simple rectangle "background"
        g2d.setColor(Color.blue);
        g2d.fillRect(0, 0, 1280, 720);

        //Loops through all of the entities to render.
        for (int i = 0; i < entitiesToRender.size(); i++) {
            int x = (int)entitiesToRender.get(i).getxPos();
            int y = (int)entitiesToRender.get(i).getyPos();

            g2d.setColor(Color.green);
            g2d.fillRect(x,y,100,100);
        }

        entitiesToRender.clear();
        g2d.finalize();

        /*
            This code deals with resizing the image based on the window size.
            Like streching it and stuff
         */
        double widthScale = windowWidth / (double) renderedGame.getWidth();
        double heightScale = windowHeight / (double) renderedGame.getHeight();

        Graphics2D rendererGraphics = (Graphics2D) g;
        rendererGraphics.scale(widthScale, heightScale);

        //Draws the game image to the window
        rendererGraphics.drawImage(renderedGame, 0, 0, null);
        rendererGraphics.dispose();


        renderedGame.flush();

        this.setFrameRendered(true);
    }


    public boolean isFrameRendered() {
        return frameRendered;
    }

    public void setFrameRendered(boolean frameRendered) {
        this.frameRendered = frameRendered;
    }


    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }


    public void setEntitiesToRender(ArrayList<Entity> entitiesToRender) {
        this.entitiesToRender = entitiesToRender;
    }
}
