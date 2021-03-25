package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    BufferedImage data;
    String imgPath;

    public Bitmap() {
    }

    public void loadFromFile(String imgPath) throws IOException {
        this.imgPath = imgPath;
        this.data = ImageIO.read(new File(imgPath));
    }

    public void printPixels() {
        int w = this.data.getWidth();
        int h = this.data.getHeight();
        Color pixel = new Color(data.getRGB(0, h - 1));
        System.out.printf("Red: %d, Green: %d, Blue %d\n",
                pixel.getRed(),
                pixel.getGreen(),
                pixel.getBlue());
    }

    public void saveToFile(String outputPath) throws IOException {
        File out = new File(outputPath);
        ImageIO.write(this.data, "bmp", out);
    }

    public void makeGrayscale() {
        for (int x = 0; x < this.data.getWidth(); x++) {
            for (int y = 0; y < this.data.getWidth(); y++) {
                Color pixel = new Color(data.getRGB(x, y));
                int value = (int) (0.299 * pixel.getRed()
                        + 0.114 * pixel.getBlue()
                        + 0.587 * pixel.getGreen());
                Color newPixel = new Color(value, value, value);
                this.data.setRGB(x, y, newPixel.getRGB());
            }
        }
    }

    public void mirror(){
      //create tmp RGB
      int w = this.data.getWidth();
      int rgb;
      //iterate through each row
      for (int y = 0; y < data.getHeight(); y++) {
        for (int x = 0; x < w / 2; x++) {
          rgb = data.getRGB(x,y);
          data.setRGB(x,y,data.getRGB(w-1-x,y));
          data.setRGB(w-1-x,y,rgb);
        }
      }
    }
}
