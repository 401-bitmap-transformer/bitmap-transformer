package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bitmap {
    BufferedImage data;
    String imgPath;
    int width = 0;
    int height = 0;

    public Bitmap() {
    }

    public void loadFromFile(String imgPath) throws IOException {
        this.imgPath = imgPath;
        this.data = ImageIO.read(new File(imgPath));
        this.width = data.getWidth();
        this.height = data.getHeight();
    }

    public void printPixels() {
        Color pixel = new Color(data.getRGB(0, height - 1));
        System.out.printf("Red: %d, Green: %d, Blue %d\n",
                pixel.getRed(),
                pixel.getGreen(),
                pixel.getBlue());
    }

    public void saveToFile(String outputPath) throws IOException {
        File out = new File(outputPath);
        ImageIO.write(this.data, "bmp", out);
    }

    public void convertToGrayscale() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixel = new Color(data.getRGB(x, y));
                int value = (int) (0.299 * pixel.getRed()
                        + 0.114 * pixel.getBlue()
                        + 0.587 * pixel.getGreen());
                Color newPixel = new Color(value, value, value);
                this.data.setRGB(x, y, newPixel.getRGB());
            }
        }
    }

    public void mirrorImage() {
        //create tmp RGB
        int rgb;
        //iterate through each row
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                rgb = data.getRGB(x, y);
                data.setRGB(x, y, data.getRGB(width - 1 - x, y));
                data.setRGB(width - 1 - x, y, rgb);
            }
        }
    }

    public void blurImage(int radius) {
        int[][] result = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ArrayList<Color> neighbors = new ArrayList<>();
                // Get the color values of all of the neighbors of a pixel.
                for (int dx = -radius; dx <= radius; dx++) {
                    for (int dy = -radius; dy <= radius; dy++) {
                        int x1 = x + dx;
                        int y1 = y + dy;
                        if (x1 >= 0 && x1 < width && y1 >= 0 && y1 < height)
                            neighbors.add(new Color(data.getRGB(x1, y1)));
                    }
                }
                int numNeighbors = neighbors.size();
                int red = neighbors.stream().mapToInt(Color::getRed).sum() / numNeighbors;
                int green = neighbors.stream().mapToInt(Color::getGreen).sum() / numNeighbors;
                int blue = neighbors.stream().mapToInt(Color::getBlue).sum() / numNeighbors;
                result[x][y] = new Color(red, green, blue).getRGB();
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                data.setRGB(x, y, result[x][y]);
            }
        }
    }
}
