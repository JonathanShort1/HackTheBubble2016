
public class Bullet extends Entity {

    private boolean inFlight;

    public Bullet() {
        super.setxPos(0);
        super.setyPos(0);
        super.setVelocity(0);
        super.setAngle(0);
        this.inFlight = true;
    }

    public boolean isInFlight() {
        return inFlight;
    }

    public void setInFlight(boolean inFlight) {
        this.inFlight = inFlight;
    }
}
