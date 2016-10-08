package Entities;

import Graphics.SpriteLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Peter on 08/10/16.
 */
public class Entity {

    private final String imgDir = "sprites";

    private double xPos;
    private double yPos;
    private int increase = 2;
    private int width;
    private int height;
    private double velocity;
    private double angle;
    private double maxVelocity = 10;

    public BufferedImage getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(String texturePath) {
        SpriteLoader spriteLoader =  new SpriteLoader(imgDir);
        this.currentTexture = spriteLoader.getSprite(texturePath);
    }

    private BufferedImage currentTexture;

    public void update(){}

    public void move(){
        if (this.getAngle()  > 180) {
            this.setAngle(-180);
        }
        if (this.getAngle() < -180) {
            this.setAngle(180);
        }
        double rad = Math.toRadians(this.getAngle());

        if (this.getAngle() >= 0 && this.getAngle() <= 45){
            this.setxPos(this.getxPos() + this.getVelocity());
            this.setyPos(this.getyPos() + this.getVelocity()*Math.tan(rad));
        }
        else if (this.getAngle() > 45 && this.getAngle() <= 90){
            rad = Math.toRadians(90-this.getAngle());
            this.setxPos(this.getxPos() + this.getVelocity()*Math.tan(rad));
            this.setyPos(this.getyPos() + this.getVelocity());
        }
        else if (this.getAngle() > 90 && this.getAngle() <= 135){
            rad = Math.toRadians(this.getAngle() - 90);
            this.setxPos(this.getxPos() + this.getVelocity()*-Math.tan(rad));
            this.setyPos(this.getyPos() + this.getVelocity());
        }
        else if (this.getAngle() > 135 && this.getAngle() <= 180){
            rad = Math.toRadians(180-this.getAngle());
            this.setxPos(this.getxPos() - this.getVelocity());
            this.setyPos(this.getyPos() + this.getVelocity()*Math.tan(rad));
        }
        else if (this.getAngle() > -180 && this.getAngle() <= -135){
            rad = Math.toRadians(this.getAngle() + 180);
            this.setxPos(this.getxPos() - this.getVelocity());
            this.setyPos(this.getyPos() + this.getVelocity()*-Math.tan(rad));
        }
        else if (this.getAngle() > -135 && this.getAngle() <= -90){
            rad = Math.toRadians(-(this.getAngle()+90));
            this.setxPos(this.getxPos() + this.getVelocity()*-Math.tan(rad));
            this.setyPos(this.getyPos() - this.getVelocity());
        }
        else if (this.getAngle() > -90 && this.getAngle() <= -45){
            rad = Math.toRadians(this.getAngle() + 90);
            this.setxPos(this.getxPos() + this.getVelocity()*Math.tan(rad));
            this.setyPos(this.getyPos() - this.getVelocity());
        }
        else if (this.getAngle() > -45 && this.getAngle() <= 0){
            rad = Math.toRadians(-this.getAngle());
            this.setxPos(this.getxPos() + this.getVelocity());
            this.setyPos(this.getyPos() + this.getVelocity()*-Math.tan(rad));
        }


        System.out.println(this.getVelocity()*Math.cos(rad));
    }

    public void accelerate(){
        if (this.getVelocity() < this.getMaxVelocity()) {
            this.setVelocity(this.getVelocity() + increase);
        }
    }

    // GETTERS & SETTERS

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setAngle(double angle) {
        if(angle > 360){
            angle = angle % 360;
        }

        this.angle = angle;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAngle() {
        return angle;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
