package bitmap.transformer;

import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BitmapTest {
    static String[] testImages = {
            "src/test/resources/img.bmp"
    };

    static String validPath = "src/test/resources/img.bmp";

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
                    assertEquals("every pixel is equal to the reflected pixel in the mirrored image",
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
            bmp.blurImage();
            assertEquals(bmp.data.getRGB(5,5), -524040);
        }
    }

    @Test(expected = IOException.class)
    public void loadFromFileThrowsTest() throws Exception {
        String InvalidPath ="I'm not really here";
        Bitmap bmp = new Bitmap();
        bmp.loadFromFile(InvalidPath);
    }

    @Test public void loadFromFileDoesNotThrowTest() throws Exception {
        System.out.println(validPath);
        Bitmap bmp = new Bitmap();
        bmp.loadFromFile(validPath);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveToFileThrowsNoDataTest() throws Exception {
        Bitmap bmp = new Bitmap();
        bmp.saveToFile("phh");
    }

    @Test public void saveToFileDoesNotThrowTest() throws Exception {
        //prep test
        String tmpFilePath = "src/test/resources/test.bmp";
        //run test
        Bitmap bmp = new Bitmap();
        bmp.loadFromFile(validPath);
        bmp.saveToFile(tmpFilePath);
        //clean up dirs after test is ran
        File tmpFile = new File(tmpFilePath);
        tmpFile.delete();
    }
}
