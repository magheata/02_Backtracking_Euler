/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;

/**
 * Menu en el cuál se puede seleccionar la pieza deseada. Contiene tantos botones como piezas definidas haya en el
 * programa.
 */
public class Menu extends JPanel {

    public int botonSeleccionado;
    private BTController controller;

    private JButton peonButton, caballoButton, reinaButton, peonWhiteButton, damaButton, damaWhiteButton;
    public JButton[] botones = {peonButton, caballoButton, reinaButton, peonWhiteButton, damaButton, damaWhiteButton};
    private String[] botonesImg = {"peon77.png", "caballo77.png", "reina77.png", "peonwhite77.png", "damas77.png",
            "damaswhite77.png"};

    private String imagesPath = "/Presentacion/Imagenes/";

    /**
     * Constructor que crea los botones de forma dinámica.
     * @param controller
     * @param botonSeleccionado
     */
    public Menu(BTController controller, int botonSeleccionado){
        this.controller = controller;
        this.botonSeleccionado = botonSeleccionado;
        for(int i = 0; i < botones.length; i++){
            botones[i] = new JButton();
            if (i == botonSeleccionado){
                activarButton(botonSeleccionado, botones[i]);
            } else {
                desactivarBoton(botones[i]);
            }
            // Añadimos la imagen correpsondiente a la pieza
            botones[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(imagesPath.concat(botonesImg[i]))));
            botones[i].addActionListener(e -> {
                Object source = e.getSource();
                new Thread(() -> {
                    for (int j = 0; j < botones.length; j++) {
                        if (botones[j] == source) {
                            activarButton(j, botones[j]);
                        } else {
                            desactivarBoton(botones[j]);
                        }
                    }
                }).start();
            });
            this.add(botones[i]);
        }
    }

    /**
     *
     */
    public void modificarAccesoBotones(){
        for (int i = 0; i < botones.length; i++){
            botones[i].setEnabled(!botones[i].isEnabled());
        }
    }

    /**
     *
     * @param button
     */
    private void desactivarBoton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }


    /**
     *
     * @param botonSeleccionado
     * @param button
     */
    private void activarButton(int botonSeleccionado, JButton button){
        controller.setPiezaSeleccionada(botonSeleccionado);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }


}
