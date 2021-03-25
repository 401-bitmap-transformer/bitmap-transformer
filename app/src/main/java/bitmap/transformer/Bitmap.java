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
        Color pixel = new Color(data.getRGB(0,h-1));
        System.out.printf("Red: %d, Green: %d, Blue %d\n",
                pixel.getRed(),
                pixel.getGreen(),
                pixel.getBlue());

        //int[] pixels = this.data.getRGB(0, 0, w, h, null, 0, 1);
    }

    public void makeGray(){
    }

    public void mirror(){
      //create tmp RGB
      int w = this.data.getWidth();
      int rgb;
      //iterate through each row
      for (int x = 0; x < data.getHeight(); x++) {
        for (int y = 0; y < w / 2; y++) {
          rgb = data.getRGB(x,y);
          data.setRGB(x,y,data.getRGB(w-x,y));
          data.setRGB(w-x,y,rgb);
        }
      }
    }
}
