/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitmap.transformer;

import java.awt.image.Raster;
import java.io.IOException;

public class BitmapTransformer {
    public static void main(String[] args) {
        //TODO validate 3 args are given
        String imgPath = args[0];
        Bitmap bmp = new Bitmap();
        try {
            bmp.loadFromFile(imgPath);
        } catch (IOException e) {
            System.out.println("File path not found!");
        }
        bmp.printPixels();
//        bmp.makeGrayscale();
        //bmp.mirror();
        try {
            bmp.saveToFile(imgPath + ".out.bmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

