/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication29;

// Importación de bibliotecas necesarias
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

// Clase principal que extiende JFrame para crear la ventana de la aplicación
public class LipFilterApp extends JFrame {
    private JLabel imageLabel; // Etiqueta para mostrar la imagen cargada
    private BufferedImage image; // Variable para almacenar la imagen cargada

    // Constructor de la clase LipFilterApp
    public LipFilterApp() {
        // Configuración de la ventana
        setTitle("Filtro de Maquillaje para Labios"); // Título de la ventana
        setSize(600, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierre de la aplicación al cerrar la ventana
        setLayout(new BorderLayout()); // Layout de la ventana

        // Etiqueta para mostrar la imagen
        imageLabel = new JLabel("No hay imagen cargada"); // Mensaje inicial
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Centrar texto en la etiqueta
        add(imageLabel, BorderLayout.CENTER); // Añadir etiqueta al centro de la ventana

        // Botón para cargar la imagen
        JButton loadButton = new JButton("Cargar Imagen"); // Crear botón
        loadButton.addActionListener(new ActionListener() { // Añadir acción al botón
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage(); // Llamar al método para cargar imagen
            }
        });

        // Botón para seleccionar el color
        JButton colorButton = new JButton("Seleccionar Color"); // Crear botón
        colorButton.addActionListener(new ActionListener() { // Añadir acción al botón
            @Override
            public void actionPerformed(ActionEvent e) {
                if (image != null) { // Verificar si hay una imagen cargada
                    // Mostrar un selector de color
                    Color color = JColorChooser.showDialog(null, "Elige un color para los labios", Color.RED);
                    if (color != null) {
                        applyLipColor(color); // Aplicar el color seleccionado a los labios
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Primero, carga una imagen."); // Mensaje si no hay imagen
                }
            }
        });

        // Panel para contener los botones
        JPanel buttonPanel = new JPanel(); // Crear panel
        buttonPanel.add(loadButton); // Añadir botón de cargar
        buttonPanel.add(colorButton); // Añadir botón de seleccionar color
        add(buttonPanel, BorderLayout.SOUTH); // Añadir panel al fondo de la ventana
    }

    // Método para cargar una imagen desde el sistema de archivos
    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser(); // Crear un selector de archivos
        int returnValue = fileChooser.showOpenDialog(this); // Mostrar diálogo de apertura
        if (returnValue == JFileChooser.APPROVE_OPTION) { // Si se seleccionó un archivo
            File selectedFile = fileChooser.getSelectedFile(); // Obtener el archivo seleccionado
            try {
                image = ImageIO.read(selectedFile); // Leer la imagen
                if (image != null) {
                    // Mostrar la imagen en la etiqueta y limpiar el mensaje de texto
                    imageLabel.setIcon(new ImageIcon(image));
                    imageLabel.setText(""); // Limpiar texto cuando hay imagen
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen."); // Mensaje de error
                }
            } catch (Exception ex) {
                // Manejo de errores al cargar la imagen
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
                ex.printStackTrace(); // Imprimir traza del error en la consola
            }
        }
    }

    // Método para aplicar el color seleccionado a los labios en la imagen
    private void applyLipColor(Color color) {
        // Crear una nueva imagen en blanco con el mismo tamaño que la original
        BufferedImage coloredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Aplicar el color a la imagen original
        for (int x = 0; x < image.getWidth(); x++) { // Recorrer cada pixel en ancho
            for (int y = 0; y < image.getHeight(); y++) { // Recorrer cada pixel en alto
                int pixel = image.getRGB(x, y); // Obtener el color del pixel
                Color pixelColor = new Color(pixel, true); // Crear un objeto Color a partir del pixel

                // Lógica mejorada para identificar la región de los labios
                if (isLipColor(pixelColor)) { // Verificar si el pixel es un color de labios
                    coloredImage.setRGB(x, y, color.getRGB()); // Cambiar el color del pixel a color elegido
                } else {
                    coloredImage.setRGB(x, y, pixel); // Mantener el color original
                }
            }
        }

        imageLabel.setIcon(new ImageIcon(coloredImage)); // Mostrar la imagen coloreada
        imageLabel.repaint(); // Actualizar la etiqueta para reflejar los cambios
    }

    // Método para determinar si el color es un color de labios
    private boolean isLipColor(Color color) {
        // Rango de colores para labios (ajustar según sea necesario)
        int redThreshold = 100;  // Rango superior para el rojo
        int greenThreshold = 80;  // Rango superior para el verde
        int blueThreshold = 80;   // Rango superior para el azul

        // Condición para detectar un color de labios (se pueden ajustar estos valores)
        return (color.getRed() > redThreshold) && (color.getGreen() < greenThreshold) && (color.getBlue() < greenThreshold);
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LipFilterApp app = new LipFilterApp(); // Crear instancia de LipFilterApp
            app.setVisible(true); // Hacer visible la ventana
        });
    }
}

