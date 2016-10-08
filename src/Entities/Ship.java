package Entities;

/**
 *  Created by Peter on 08/10/16.
 */

public class Ship extends Entity{

    private final int init = 0;
    private final int maxVelocity  = 7;
    private double changeSpeed = 0.1;


    private int ammo;

    private Bullet bullet;

    public Ship(double xPos, double yPos, double angle, int width, int height, String texturePath) {
        this.ammo = 1;
        super.setCurrentTexture(texturePath);
        super.setAngle(angle);
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setHeight(height);
        super.setWidth(width);
        super.setVelocity(init);
        super.setMaxVelocity(maxVelocity);
        this.bullet = new Bullet(0,0,0,this);
    }

    @Override
    public void update() {
        this.move();
        this.slowDown();
    }

    private void slowDown() {
        if (this.getVelocity() > 0){
            this.setVelocity(this.getVelocity() - changeSpeed);
            if (this.getVelocity() < 0) {
                this.setVelocity(0);
            }
        }
        if (this.getVelocity() < 0 ) {
            this.setVelocity(this.getVelocity() + changeSpeed);
            if (this.getVelocity() > 0){
                this.setVelocity(0);
            }
        }
    }


    public void shoot(){
        if (ammo >= 1) {
            bullet.fire();
            ammo--;
        }
    }


    public void addBullet(Bullet bullet) {
        ammo++;
    }
    //GETTER & SETTERS

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setBullets(Bullet bullet) {
        this.bullet = bullet;
    }

    public int getAmmo() {
        return 0;
    }

    public Bullet getBullet(){
        return bullet;
    }
}
