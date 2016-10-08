package Graphics;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.*;

import Entities.Entity;

public class Renderer extends JComponent {

    private static final long serialVersionUID = 1L;

    private int width;
    private int height;

    private int windowWidth;
    private int windowHeight;

    private ArrayList<Entity> entitiesToRender = new ArrayList<Entity>();
    private VolatileImage renderedGame;
    private Graphics2D g2d;

    private int currentFPS;
    private int currentUPS;
    private boolean frameRendered = true;


    public Renderer(int width, int height){
        super();
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        this.setLayout(null);

        System.out.println("Width: "+width+"  Height: "+height);
    }


    @Override
    protected void paintComponent(Graphics g) {
        this.setFrameRendered(false);
        super.paintComponent(g);

        if (renderedGame == null || renderedGame.validate(getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {

            renderedGame = createVolatileImage(1280, 720);

        }

        g2d = renderedGame.createGraphics();



        //create simple background.
        g2d.setColor(new Color(0,10,10));
        g2d.fillRect(0, 0, 1280, 720);


        for (int i = 0; i < entitiesToRender.size(); i++) {
            g2d.drawImage(entitiesToRender.get(i).getCurrentTexture(),(int)entitiesToRender.get(i).getxPos(), (int)entitiesToRender.get(i).getyPos(),null);
        }


        entitiesToRender.clear();
        g2d.finalize();

        double widthScale;
        double heightScale;

        widthScale = windowWidth/(double)renderedGame.getWidth();
        heightScale = windowHeight/(double)renderedGame.getHeight();

        Graphics2D rendererGraphics = (Graphics2D)g;
        rendererGraphics.scale(widthScale, heightScale);
        rendererGraphics.drawImage(renderedGame, 0, 0, null);
        rendererGraphics.dispose();


        renderedGame.flush();

        this.setFrameRendered(true);

    }

    public int getWindowWidth() {
        return windowWidth;
    }


    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }


    public int getWindowHeight() {
        return windowHeight;
    }


    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }


    public ArrayList<Entity> getEntitiesToRender() {
        return entitiesToRender;
    }


    public void setEntitiesToRender(ArrayList<Entity> entitiesToRender) {
        this.entitiesToRender = entitiesToRender;
    }


    public int getCurrentFPS() {
        return currentFPS;
    }


    public void setCurrentFPS(int currentFPS) {
        this.currentFPS = currentFPS;
    }


    public int getCurrentUPS() {
        return currentUPS;
    }


    public void setCurrentUPS(int currentUPS) {
        this.currentUPS = currentUPS;
    }

    public boolean isFrameRendered() {
        return frameRendered;
    }


    public void setFrameRendered(boolean frameRendered) {
        this.frameRendered = frameRendered;
    }

}
