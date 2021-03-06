package unitTests;

import org.junit.jupiter.api.Test;
import renderer.ImageWriter;

import java.awt.*;

class ImageWriterTest {

    @Test
    void ImageWiterWriteToImageTest() {
        ImageWriter imageWriter = new ImageWriter("kuku was here", 1600, 1000, 800, 500);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.YELLOW);
                } else {
                    imageWriter.writePixel(j, i, Color.BLUE);
                }
            }
        }
        imageWriter.writeToImage();
    }
}