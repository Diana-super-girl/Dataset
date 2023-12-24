package code.animals.base;

public abstract class FlyingAnimal extends Animal {

    public FlyingAnimal(float startX, float startY, float speed) {
        super(startX, startY, speed);
    }

    @Override
    public void addCheckpoint(float x, float y) {
        if (y <= 0)
            return;

        super.addCheckpoint(x, y);
    }
}
