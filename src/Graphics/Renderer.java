package Graphics;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JLabel;

import Entities.Entity;

public class Renderer extends JLabel{
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
    
}
