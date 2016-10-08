package Entities;

public class Bullet extends Entity {

    private boolean inFlight;
    private int changeSpeed = 5;

    public Bullet(double xPos, double yPos, double angle) {
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setVelocity(0);
        super.setAngle(angle);
        this.inFlight = true;
    }

    public boolean isInFlight() {
        return inFlight;
    }

    public void setInFlight(boolean inFlight) {
        this.inFlight = inFlight;
    }

    public void slowdown() {
        while  (inFlight) {
            this.setVelocity(this.getVelocity() - changeSpeed);
        }
    }
}
