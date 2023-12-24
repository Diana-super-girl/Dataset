package code.animals;

import code.animals.base.FlyingAnimal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird extends FlyingAnimal {

    public Bird(float startX, float startY, float speed) {
        super(startX, startY, speed);
    }

    @Override
    protected double moveFunc(double x) {
        return Math.pow(x, 3); // x в 3 степени
    }

    @Override
    public void drawAt(Graphics graphics, int x, int y) {
        File file1 = new File("bird.png");
        BufferedImage birdImage;
        try {
            birdImage = ImageIO.read(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int imageWidth = 14;
        int imageHeight = 14;
        Image image = birdImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);

        graphics.drawImage(image, x - 7, y - 7, 20, 20, null);
    }
}
