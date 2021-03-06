package Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Peter on 08/10/16.
 */


public class SpriteLoader {
    private final int width = 32;
    private final int height = 32;
    private String dirPath;


    public SpriteLoader(String dirPath){
        String current = System.getProperty("user.dir");
        this.dirPath = current + "/" + dirPath;
    }

    //returns a buffered Image
    public BufferedImage getSprite(String fileName) {
        return loadSprite(fileName);
    }

    //loads sprite as buffered image
    private BufferedImage loadSprite(String path){
        System.out.println(dirPath+"/"+path);
        File img = new File(dirPath + "/" + path);


        BufferedImage image = null;
        try {
            image = ImageIO.read(img);
        } catch (IOException e){
            System.err.println("Sprite at: " + path + " could not be loaded, \n Check that it exists");
            e.printStackTrace();
        }

        return image;
    }
}
