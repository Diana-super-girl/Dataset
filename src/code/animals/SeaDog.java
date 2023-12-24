package code.animals;

import code.animals.base.UnderwaterAnimal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SeaDog extends UnderwaterAnimal {

    public SeaDog(float startX, float startY, float speed, float motionRange, float maxDepth) {
        super(startX, startY, speed, motionRange, maxDepth);
    }

    @Override
    protected double moveFunc(double x) {
        return Math.sin(2 * Math.PI * x * x);
    }

    @Override
    public void drawAt(Graphics graphics, int x, int y) {
        File file1 = new File("dolphin.png");
        BufferedImage dolphinImage;
        try {
            dolphinImage = ImageIO.read(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int imageWidth = 14;
        int imageHeight = 14;
        Image image = dolphinImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);

        graphics.drawImage(image, x - 7, y - 7, 20, 20, null);
    }
}
