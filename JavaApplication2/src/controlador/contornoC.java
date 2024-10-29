/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 * Controlador que conecta el modelo y la vista.
 * Permite cargar una imagen y aplicarle un color.
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import modelo.contornoM;
import vista.vistaContorno;

public class contornoC {
    private contornoM modelo; // El modelo que hace la lógica (cargar y colorear imagen).
    private vistaContorno vista; // La vista que muestra la interfaz gráfica.

    // Constructor que configura el modelo y la vista, y añade las acciones de los botones.
    public contornoC(contornoM modelo, vistaContorno vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Acción al hacer clic en el botón de cargar imagen.
        this.vista.getLoadButton().addActionListener(new LoadImageAction());
        // Acción al hacer clic en el botón de aplicar color.
        this.vista.getColorButton().addActionListener(new ApplyColorAction());
    }

    // Clase interna que maneja la acción de cargar una imagen.
    private class LoadImageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(); // Ventana para elegir el archivo.
            int returnValue = fileChooser.showOpenDialog(vista); // Abre la ventana de selección.

            // Si el usuario selecciona un archivo, lo guardamos.
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile(); // Obtiene el archivo.
                try {
                    modelo.loadImage(selectedFile); // Carga la imagen en el modelo.
                    vista.setImageIcon(new ImageIcon(modelo.getImage())); // Muestra la imagen en la vista.
                } catch (Exception ex) {
                    // Muestra un mensaje si ocurre un error al cargar la imagen.
                    vista.showMessage("Error al cargar la imagen: " + ex.getMessage());
                }
            }
        }
    }

    // Clase interna que maneja la acción de aplicar un color en la imagen.
    private class ApplyColorAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = vista.showColorChooser(); // Abre el selector de color.
            
            // Si el usuario elige un color, intenta aplicarlo en la imagen.
            if (color != null) { // Asegura que se haya seleccionado un color.
                BufferedImage coloredImage = modelo.applyLipColor(color); // Aplica el color a la imagen.
                
                // Si la imagen tiene color, la muestra en la vista; si no, muestra un aviso.
                if (coloredImage != null) {
                    vista.setImageIcon(new ImageIcon(coloredImage)); // Actualiza la vista con la imagen coloreada.
                } else {
                    vista.showMessage("Primero, carga una imagen."); // Mensaje si no hay imagen cargada.
                }
            }
        }
    }
}
