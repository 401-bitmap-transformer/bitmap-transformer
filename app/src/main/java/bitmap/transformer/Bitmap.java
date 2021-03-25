package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Bitmap {
    BufferedImage data;
    String imgPath;

    public Bitmap(String imgPath) {
        this.imgPath = imgPath;
    }

    public void openBmp(){
        try {
            this.data = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPixels() {
        int w = this.data.getWidth();
        int h = this.data.getHeight();
        int pixel = data.getRGB(0,h-1);

        ColorModel colorModel = this.data.getColorModel();

        System.out.printf("Red: %d, Green: %d, Blue %d\n",
                colorModel.getRed(pixel),
                colorModel.getGreen(pixel),
                colorModel.getBlue(pixel));
        //int[] rgb = new int[100];
        //int[] pixels = this.data.getRGB(0, 0, w, h, rgb, 0, 0);
    }

    public void printPixels2() {
        int w = this.data.getWidth();
        int h = this.data.getHeight();
        Color pixel = new Color(data.getRGB(0,h-1));

        ColorModel colorModel = this.data.getColorModel();

        System.out.printf("Red: %d, Green: %d, Blue %d\n",
                pixel.getRed(),
                pixel.getGreen(),
                pixel.getBlue());
        //int[] rgb = new int[100];
        //int[] pixels = this.data.getRGB(0, 0, w, h, rgb, 0, 0);
    }


}
