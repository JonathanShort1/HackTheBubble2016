package Entities;

/**
 * Created by Peter on 08/10/16.
 */
public class Entity {
    private double xPos;
    private double yPos;
    private double velocity;
    private double angle;

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

    public void update(){}

}
