/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.instagramm;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.vistainsta;

public class instagramc {

    private instagramm modelo;
    private vistainsta vista;

    public instagramc(instagramm modelo, vistainsta vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        // Asignar acciones a los botones
        this.vista.getBtnAgregarImagen().addActionListener(e -> agregarImagen());
        this.vista.getBtnAumentarBrillo().addActionListener(e -> aplicarFiltroEscalaDeGrises());
        this.vista.getBtnReducirBrillo().addActionListener(e -> invertirColores());
    }

    
    private void agregarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        // Filtro para solo mostrar imágenes
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(vista);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                modelo.cargarImagen(selectedFile);
                vista.actualizarImagen(modelo.getImagen());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void aplicarFiltroEscalaDeGrises() {
        if (modelo.getImagen() != null) {
            modelo.setImagen(modelo.aplicarFiltroEscalaDeGrises(modelo.getImagen()));
            vista.actualizarImagen(modelo.getImagen());
        }
    }

    public void invertirColores() {
        if (modelo.getImagen() != null) {
            modelo.setImagen(modelo.invertirColores(modelo.getImagen()));
            vista.actualizarImagen(modelo.getImagen());
        }
    }
}
