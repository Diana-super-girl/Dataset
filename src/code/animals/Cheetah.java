package code.animals;

import code.Main;
import code.animals.base.GroundAnimal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cheetah extends GroundAnimal {

    public Cheetah(float startX, float startY, float speed, float jumpHeight) {
        super(startX, startY, speed, jumpHeight);
    }

    @Override
    protected double moveFunc(double x) {
        return (1-4* Math.pow(x-0.5, 2));
    }

    @Override
    public void drawAt(Graphics graphics, int x, int y) {
        File file1 = new File("hare.png");
        BufferedImage hareImage;
        try {
            hareImage = ImageIO.read(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int imageWidth = 14;
        int imageHeight = 14;
        Image image = hareImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);

        graphics.drawImage(image, x - 7, y - 7, 20, 20, null);
    }
}
