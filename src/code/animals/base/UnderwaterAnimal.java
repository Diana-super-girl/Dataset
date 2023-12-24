package code.animals.base;

public abstract class UnderwaterAnimal extends Animal {

    protected float maxDepth;
    protected float motionRange;

    public float getMaxDepth() {
        return maxDepth;
    }
    public float getMotionRange() {
        return motionRange;
    }

    public UnderwaterAnimal(float startX, float startY, float speed, float motionRange, float maxDepth) {
        super(startX, startY, speed);
        this.motionRange = motionRange / 5;
        this.maxDepth = maxDepth;
    }

    @Override
    protected void calcNewPosition(double tMax) {
        position.x = startPosition.x + (float)(timeMoving / tMax) * (targetPosition.x - startPosition.x);
        float y = (float)(startPosition.y
                + (timeMoving / tMax) * (targetPosition.y - startPosition.y)
                + (float)moveFunc(timeMoving / tMax) * (motionRange + targetPosition.y - startPosition.y));

        if (y < -maxDepth)
            y = -maxDepth;

        position.y = y;
    }

    @Override
    public void addCheckpoint(float x, float y) {
        if (y > 0)
            return;

        if (y < -maxDepth)
            y = -maxDepth;

        super.addCheckpoint(x, y);
    }
}
