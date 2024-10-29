/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author airto
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class contornoM {
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void loadImage(File file) throws Exception {
        image = ImageIO.read(file);
    }

    public BufferedImage applyLipColor(Color color) {
        if (image == null) return null;

        BufferedImage coloredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                Color pixelColor = new Color(pixel, true);

                // Lógica simple para identificar la región de los labios
                if (pixelColor.getRed() > 100 && pixelColor.getGreen() < 80 && pixelColor.getBlue() < 80) {
                    coloredImage.setRGB(x, y, color.getRGB());
                } else {
                    coloredImage.setRGB(x, y, pixel);
                }
            }
        }

        return coloredImage;
    }
}

