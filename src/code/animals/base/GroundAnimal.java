package code.animals.base;

public abstract class GroundAnimal extends Animal {

    protected float jumpHeight;

    public float getJumpHeight() {
        return jumpHeight;
    }

    public GroundAnimal(float startX, float startY, float speed, float jumpHeight) {
        super(startX, startY, speed);
        this.jumpHeight = jumpHeight;
    }

    @Override
    protected void calcNewPosition(double tMax) {
        position.x = startPosition.x + (float)(timeMoving / tMax) * (targetPosition.x - startPosition.x);

        int jumpsCount = (int) Math.ceil(Math.abs((double)(targetPosition.x - startPosition.x) / (3 * (double)jumpHeight)));
        double t = ((timeMoving / tMax) * jumpsCount) % 1;
        position.y = startPosition.y + Math.abs((float)moveFunc(t)) * Math.min(Math.abs(targetPosition.x - startPosition.x), jumpHeight);
    }

    @Override
    public void addCheckpoint(float x, float y) {
        if (y < 0)
            return;

        super.addCheckpoint(x, 0);
    }
}
