/**
 *
 */

public class Ship extends Entity{

    private final int init = 0;

    private int ammo;

    private Bullet[] bullets;

    public Ship() {
        this.ammo = init;
        this.angle = init;
        this.velocity = init;
        this.bullets = new Bullet[3];
    }


    public int getAmmo() {
        return 0;
    }

    public Bullet[] getBullets(){
        return bullets;
    }

}
