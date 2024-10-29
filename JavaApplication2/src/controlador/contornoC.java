/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author airto
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
    private contornoM  modelo;
    private vistaContorno vista;

    public contornoC(contornoM  modelo, vistaContorno vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.getLoadButton().addActionListener(new LoadImageAction());
        this.vista.getColorButton().addActionListener(new ApplyColorAction());
    }

    private class LoadImageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(vista);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    modelo.loadImage(selectedFile);
                    vista.setImageIcon(new ImageIcon(modelo.getImage()));
                } catch (Exception ex) {
                    vista.showMessage("Error al cargar la imagen: " + ex.getMessage());
                }
            }
        }
    }

    private class ApplyColorAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = vista.showColorChooser();
            if (color != null) {
                BufferedImage coloredImage = modelo.applyLipColor(color);
                if (coloredImage != null) {
                    vista.setImageIcon(new ImageIcon(coloredImage));
                } else {
                    vista.showMessage("Primero, carga una imagen.");
                }
            }
        }
    }
}

