/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class instagramm {
    private BufferedImage imagen;

    public void cargarImagen(File file) throws Exception {
        this.imagen = ImageIO.read(file);
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    // Método para aplicar filtro de escala de grises
    public BufferedImage aplicarFiltroEscalaDeGrises(BufferedImage imagen) {
        int width = imagen.getWidth();
        int height = imagen.getHeight();
        BufferedImage gris = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = imagen.getRGB(x, y);
                gris.setRGB(x, y, rgb);
            }
        }
        return gris;
    }

    // Método para invertir colores
    public BufferedImage invertirColores(BufferedImage imagen) {
        int width = imagen.getWidth();
        int height = imagen.getHeight();
        BufferedImage invertida = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = imagen.getRGB(x, y);
                int r = 255 - ((rgb >> 16) & 0xFF);
                int g = 255 - ((rgb >> 8) & 0xFF);
                int b = 255 - (rgb & 0xFF);
                invertida.setRGB(x, y, (rgb & 0xFF000000) | (r << 16) | (g << 8) | b);
            }
        }
        return invertida;
    }
}


