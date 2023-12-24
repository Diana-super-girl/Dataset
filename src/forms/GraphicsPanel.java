package forms;

import code.animals.base.Animal;
import code.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class GraphicsPanel extends JPanel {

    // границы мировой системы координат
    private float worldXMin = -15;
    private float worldXMax = 15;
    private float worldYMin = -15;
    private float worldYMax = 15;

    public final List<Animal> animals = new ArrayList<>();

    public GraphicsPanel() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                float x = screenXtoWorldX(e.getX());
                float y = screenYtoWorldY(e.getY());

                for (var animal : animals) {
                    animal.addCheckpoint(x, y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    // установка границ мировой системы координат
    public void setWorldCords(float xMin, float xMax, float yMin, float yMax) {
        this.worldXMax = xMax;
        this.worldXMin = xMin;
        this.worldYMax = yMax;
        this.worldYMin = yMin;
    }

    // World --> Screen
    private int worldXtoScreenX(float wx) {
        return Math.round(this.getWidth() * (wx - worldXMin) / (worldXMax - worldXMin));
    }
    private int worldYtoScreenY(float wy) {
        return Math.round(this.getHeight() * (1 - (wy - worldYMin) / (worldYMax - worldYMin)));
    }

    // Screen --> World
    private float screenXtoWorldX(int sx) {
        return (float) sx / this.getWidth() * (worldXMax - worldXMin) + worldXMin;
    }
    private float screenYtoWorldY(int sy) {
        return (1 - (float) sy / this.getHeight()) * (worldYMax - worldYMin) + worldYMin;
    }

    private void drawCoordinateGrid(Graphics g) {
        g.setColor(Color.green);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        g.setColor(Color.red);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoordinateGrid(g);

        int count = animals.size();
        for (int i = 0; i < count; i++) {
            Animal animal = animals.get(i);
            Vector2 animalPos = animal.getCurPosition();

            animal.drawAt(g, worldXtoScreenX(animalPos.x), worldYtoScreenY(animalPos.y));

            g.setColor(Color.black);
            g.drawString(MessageFormat.format("x: {0}  y: {1}", (int) animalPos.x, (int) animalPos.y), 10, 20 * (i + 1));
        }
    }
}
