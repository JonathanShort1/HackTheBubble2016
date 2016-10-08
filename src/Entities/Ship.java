package Entities;

/**
 *  Created by Peter on 08/10/16.
 */

public class Ship extends Entity{

    private final int init = 0;

    private int ammo;

    private Bullet[] bullets;

    public Ship() {
        this.ammo = init;
        super.setAngle(0);
        super.setxPos(0);
        super.setyPos(0);
        super.setVelocity(0);
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



}
