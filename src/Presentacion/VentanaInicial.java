package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicial extends JFrame {
    private JButton peonButton;
    private JButton caballoButton;
    private JButton reinaButton;
    private JButton peonWhiteButton;
    private JButton damaButton;
    private JButton damaWhiteButton;
    private JButton startButton;
    private JButton decrDimensionButton;
    private JTextField dimensionesTextField;
    private JButton incrDimensionButton;
    private JPanel mainPanel;
    private JButton[] botones = {peonButton, caballoButton, reinaButton, peonWhiteButton, damaButton, damaWhiteButton};
    private String[] botonesImg = {"peon56.png", "caballo56.png", "reina56.png", "peonwhite56.png", "damas56.png",
            "damaswhite56.png"};
    private int selectedButton = 0;
    private String imagesPath = "/Presentacion/Imagenes/";

    public VentanaInicial() {
        initComponents();
    }

    private void initComponents() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        activarButton(peonButton);
        desactivarBoton(peonWhiteButton);
        desactivarBoton(reinaButton);
        desactivarBoton(caballoButton);
        desactivarBoton(damaButton);
        desactivarBoton(damaWhiteButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startActionPerformed(e);
            }
        });

        decrDimensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonMenosAgentesActionPerformed(e);
            }
        });
        incrDimensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonMasAgentesActionPerformed(e);
            }
        });

        for (int i = 0; i < botones.length; i++){
            JButton boton = botones[i];
            boton.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagesPath.concat(botonesImg[i]))));
            boton.addActionListener(e -> {
                Object source = e.getSource();
                for(int i1 = 0; i1 < botones.length; i1++){
                    if(botones[i1] == source){
                        activarButton(botones[i1]);
                        selectedButton = i1;
                    } else {
                        desactivarBoton(botones[i1]);
                    }
                }
            });
        }
    }

    private void desactivarBoton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }

    private void activarButton(JButton button){
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }

    private void startActionPerformed(ActionEvent evt) {
        try {
            int tamanyo = Integer.parseInt(dimensionesTextField.getText());
            BTController controller = new BTController();
            Ventana m = new Ventana(controller, "Euler", tamanyo, selectedButton);
            m.setVisible(true);
            this.setVisible(false);
        } catch (HeadlessException | NumberFormatException e) {
            //errorLabel.setText("El valor introducido no es un número");
        }
    }

    private void ButtonMenosAgentesActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        int numero = Integer.parseInt(dimensionesTextField.getText());
        if (!(numero <= 5)) {
            numero--;
            dimensionesTextField.setEditable(true);
            dimensionesTextField.setText(Integer.toString(numero));
            dimensionesTextField.setEditable(false);
        }
    }

    private void ButtonMasAgentesActionPerformed(ActionEvent evt) {
        int numero = Integer.parseInt(dimensionesTextField.getText());
        if (!(numero >= 15)) {
            numero++;
            dimensionesTextField.setEditable(true);
            dimensionesTextField.setText(Integer.toString(numero));
            dimensionesTextField.setEditable(false);
        }
    }

    public String GetDimensions(){
        return dimensionesTextField.getText();
    }
}
