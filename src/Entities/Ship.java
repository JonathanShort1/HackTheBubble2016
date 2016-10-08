package Entities;

/**
 *  Created by Peter on 08/10/16.
 */

public class Ship extends Entity{

    private final int init = 0;
    private final int maxVelocity  = 20;
    private int changeSpeed = 5;


    private int ammo;

    private Bullet[] bullets;

    public Ship(double xPos, double yPos, double angle) {
        this.ammo = init;
        super.setAngle(angle);
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setVelocity(init);
        super.setMaxVelocity(maxVelocity);
        this.bullets = new Bullet[3];
    }


    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }

    public int getAmmo() {
        return 0;
    }

    public Bullet[] getBullets(){
        return bullets;
    }

    @Override
    public void update() {
        this.slowDown();
    }

    private void slowDown() {
        if (this.getVelocity() > 0){
            this.setVelocity(this.getVelocity() - changeSpeed);
        }
    }

    private void accelerate() {
        if (this.getVelocity() < maxVelocity) {
            this.setVelocity(this.getVelocity() + changeSpeed);
        }
    }
}
