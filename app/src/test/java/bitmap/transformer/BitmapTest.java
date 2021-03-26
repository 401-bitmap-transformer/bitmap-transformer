package bitmap.transformer;

import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BitmapTest {
    static String[] testImages = {
            "src/test/resources/img.bmp"
    };

    @Test
    public void convertToGrayscaleTest() throws Exception {
        for (String inputPath : testImages) {
            Bitmap bmp = new Bitmap();
            bmp.loadFromFile(inputPath);
            int jump = Math.max(1, (bmp.width + bmp.height) / 20);
            int numX = bmp.width / jump;
            int numY = bmp.height / jump;
            bmp.convertToGrayscale();
            for (int x = 0; x < numX; x++) {
                for (int y = 0; y < numY; y++) {
                    Color color = new Color(bmp.data.getRGB(x * jump, y * jump));
                    assertTrue("All sampled pixels have the same R, G, and B value",
                            color.getBlue() == color.getRed() && color.getRed() == color.getGreen()
                            );
                }
            }
        }
    }

    @Test
    public void mirrorImageTest() throws Exception {
        for (String inputPath : testImages) {
            Bitmap bmp = new Bitmap();
            bmp.loadFromFile(inputPath);
            int jump = Math.max(1, (bmp.width + bmp.height) / 20);
            int numX = bmp.width / jump;
            int numY = bmp.height / jump;
            int[][] samplePixels = new int[numX][numY];
            for (int x = 0; x < numX; x++) {
                for (int y = 0; y < numY; y++) {
                    samplePixels[x][y] = bmp.data.getRGB(x * jump, y * jump);
                }
            }
            bmp.mirrorImage();
            for (int x = 0; x < numX; x++) {
                for (int y = 0; y < numY; y++) {
                    assertEquals("every pixel is equal to the reflected pixel in the mirroed image",
                            samplePixels[x][y], bmp.data.getRGB(bmp.width - 1 - x * jump, y * jump)
                    );
                }
            }
        }
    }

    @Test public void blurImageTest() throws Exception {
        for (String inputPath : testImages) {
            Bitmap bmp = new Bitmap();
            bmp.loadFromFile(inputPath);
            int jump = Math.max(1, (bmp.width + bmp.height) / 20);
            int numX = bmp.width / jump;
            int numY = bmp.height / jump;
            bmp.blurImage(10);
            for (int x = 0; x < numX; x++) {
                for (int y = 0; y < numY; y++) {
                    bmp.data.getRGB(x * jump, y * jump);
                }
            }
        }
    }

    @Test public void loadFromFileThrowsTest() {
    }

    @Test public void loadFromFileDoesNotThrowTest() {
    }

    @Test public void saveToFileThrowsTest() {
    }

    @Test public void saveToFileDoesNotThrowTest() {
    }
}
