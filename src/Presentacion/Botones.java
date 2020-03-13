package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;

public class Botones{

    public int botonSeleccionado;

    private JButton peonButton, caballoButton, reinaButton, peonWhiteButton, damaButton, damaWhiteButton;
    public JButton[] botones = {peonButton, caballoButton, reinaButton, peonWhiteButton, damaButton, damaWhiteButton};
    private String[] botonesImg = {"peon77.png", "caballo77.png", "reina77.png", "peonwhite77.png", "damas77.png",
            "damaswhite77.png"};

    private String imagesPath = "/Presentacion/Imagenes/";

    private BTController controller;
    public Botones(JPanel panel, BTController controller, int botonSeleccionado){
        this.controller = controller;
        this.botonSeleccionado = botonSeleccionado;
        for(int i = 0; i < botones.length; i++){
            botones[i] = new JButton();
            if (i == botonSeleccionado){
                activarButton(botonSeleccionado, botones[i]);
            } else {
                desactivarBoton(botones[i]);
            }
            botones[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagesPath.concat(botonesImg[i]))));
            botones[i].addActionListener(e -> {
                Object source = e.getSource();
                for (int j = 0; j < botones.length; j++) {
                    if (botones[j] == source) {
                        activarButton(j, botones[j]);
                    } else {
                        desactivarBoton(botones[j]);
                    }
                }
            });
            panel.add(botones[i]);
        }
    }

    private void desactivarBoton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }

    private void activarButton(int botonSeleccionado, JButton button){
        controller.setPiezaSeleccionada(botonSeleccionado);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }
}
