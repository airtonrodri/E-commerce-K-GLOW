/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Modelo que maneja la carga de imágenes y la aplicación de color en ciertas áreas.
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class contornoM {
    private BufferedImage image; // Imagen cargada en memoria.

    // Método para obtener la imagen actual.
    public BufferedImage getImage() {
        return image;
    }

    // Método para cargar una imagen desde un archivo.
    public void loadImage(File file) throws Exception {
        image = ImageIO.read(file); // Lee y guarda la imagen.
    }

    // Método que aplica color en áreas específicas de la imagen (simulando color en los labios).
    public BufferedImage applyLipColor(Color color) {
        if (image == null) return null; // Si no hay imagen cargada, retorna null.

        // Crea una nueva imagen para aplicar el color.
        BufferedImage coloredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Recorre todos los píxeles de la imagen original.
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y); // Obtiene el color del píxel actual.
                Color pixelColor = new Color(pixel, true); // Convierte el color a objeto Color.

                // Si el píxel cumple ciertas condiciones, lo considera como área de labios.
                if (pixelColor.getRed() > 100 && pixelColor.getGreen() < 80 && pixelColor.getBlue() < 80) {
                    // Si es un área de labios, aplica el color seleccionado.
                    coloredImage.setRGB(x, y, color.getRGB());
                } else {
                    // Si no es área de labios, mantiene el color original del píxel.
                    coloredImage.setRGB(x, y, pixel);
                }
            }
        }

        return coloredImage; // Retorna la imagen con el color aplicado.
    }
}
