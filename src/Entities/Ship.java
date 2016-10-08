package Entities;

/**
 *  Created by Peter on 08/10/16.
 */

public class Ship extends Entity{

    private final int init = 0;
    private final int maxVelocity  = 5;
    private int changeSpeed = 1;


    private int ammo;

    private Bullet[] bullets;

    public Ship(double xPos, double yPos, double angle, String texturePath) {
        this.ammo = init;
        super.setCurrentTexture(texturePath);
        super.setAngle(angle);
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setVelocity(init);
        super.setMaxVelocity(maxVelocity);
        this.bullets = new Bullet[3];
    }

    @Override
    public void update() {
        this.move();
        this.slowDown();
    }

    private void slowDown() {
        if (this.getVelocity() > 0){
            this.setVelocity(this.getVelocity() - changeSpeed);
        }
    }

    private void shoot() {
        if (bullets.length > 0) {

        }
    }

    //GETTER & SETTERS

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
}
