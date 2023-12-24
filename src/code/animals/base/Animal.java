package code.animals.base;

import code.Vector2;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public abstract class Animal {

    protected Vector2 position;
    protected Vector2 startPosition;
    protected Vector2 targetPosition;
    protected double timeMoving;
    public final float speed;
    protected boolean isMoving;
    private final Queue<Vector2> path = new ArrayDeque<>();

    public Vector2 getCurPosition() {
        return position;
    }
    public Vector2 getTargetPosition() {
        return targetPosition;
    }
    public double getTimeMoving() {
        return timeMoving;
    }

    public Animal(float startX, float startY, float speed) {
        this.speed = speed;
        this.startPosition = new Vector2(startX, startY);
        this.position = new Vector2(startX, startY);
    }

    public void addCheckpoint(float x, float y) {
        path.offer(new Vector2(x, y)); // Добавляем чекпоинт в очередь

        if (!isMoving)
            gotoNextTarget();
    }

    protected void gotoNextTarget() {
        if (path.isEmpty()) {
            isMoving = false;
            return;
        }

        targetPosition = path.poll(); // Достаем позицию из начала очереди
        startPosition = position.copy();
        timeMoving = 0;

        isMoving = true;
    }

    public void doMovement(double time) {
        if (!isMoving)
            return;

        if (targetPosition.equals(position)) {
            gotoNextTarget();
            return;
        }

        timeMoving += time;

        // расчет максимального расстояния движения
        // для линейного движения - гипотенуза прямоугольного треугольника
        float s = (float) Math.sqrt(
                Math.pow(targetPosition.x - startPosition.x, 2) +
                Math.pow(targetPosition.y - startPosition.y, 2));

        // расчет максимального времени движения
        double tMax = s / speed;

        // если движение закончилось
        if (timeMoving >= tMax) {
            position = targetPosition.copy();
            gotoNextTarget();
            return;
        }

        calcNewPosition(tMax);
    }

    // расчет текущих координат
    protected void calcNewPosition(double tMax) {
        position.x = startPosition.x + (float)(timeMoving / tMax) * (targetPosition.x - startPosition.x);
        position.y = startPosition.y + (float)moveFunc(timeMoving / tMax) * (targetPosition.y - startPosition.y);
    }

    protected abstract double moveFunc(double x);

    public abstract void drawAt(Graphics graphics, int x, int y);
}
