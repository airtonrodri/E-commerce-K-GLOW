/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.chatbotm;
import vista.vista;

/**
 *
 * @author LAB-USR-LNORTE
 */
public class chatbotc {
    private chatbotm Modelo;
    private vista Vista;

    public chatbotc(chatbotm Modelo, vista Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;

        // Agregando el ActionListener al botón Enviar
        this.Vista.enviarPregunta(e -> procesarPregunta());
    }

    private void procesarPregunta() {
        String pregunta = Vista.getPregunta();
        Vista.mostrarRespuestaUsuario(pregunta); 
        String respuesta = Modelo.getRespuesta(pregunta);
        Vista.mostrarRespuesta(respuesta);
    }
    
}
