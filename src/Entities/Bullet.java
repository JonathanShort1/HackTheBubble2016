package Entities;

public class Bullet extends Entity {

    private boolean inFlight;
    private Ship inShip;

    public Bullet(double xPos, double yPos, double angle, Ship inShip) {
        super.setCurrentTexture("bullet-inflight.png");
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setVelocity(0);
        super.setAngle(angle);
        this.inFlight = false;
        this.inShip = inShip;
    }

    public void fire(){
        System.out.println("BANG");
        inFlight = true;
        this.setxPos(inShip.getxPos());
        this.setyPos(inShip.getyPos());
        this.setAngle(inShip.getAngle());
        this.setVelocity(inShip.getVelocity() + 3);
    }

    public boolean isInFlight() {
        return inFlight;
    }

    public void setInFlight(boolean inFlight) {
        this.inFlight = inFlight;
    }

}
