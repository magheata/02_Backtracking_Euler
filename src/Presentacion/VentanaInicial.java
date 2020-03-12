package Presentacion;

import Aplicacion.BTController;
import Dominio.Datos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicial extends JFrame {
    private JButton peonButton;
    private JButton caballoButton;
    private JButton reinaButton;
    private JButton peonBlancoButton;
    private JButton damasButton;
    private JButton damasBlancoButton;
    private JButton startButton;
    private JButton decrDimensionButton;
    private JTextField dimensionesTextField;
    private JButton incrDimensionButton;
    private JPanel mainPanel;
    private JButton[] botones = {peonButton, caballoButton, reinaButton, peonBlancoButton, damasButton, damasBlancoButton};

    public VentanaInicial() {
        initComponents();
    }

    private void initComponents() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        activarButton(peonButton);
        desactivarBoton(peonBlancoButton);
        desactivarBoton(reinaButton);
        desactivarBoton(caballoButton);
        desactivarBoton(damasButton);
        desactivarBoton(damasBlancoButton);

        reinaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/reina56.png"))); // NOI18N
        caballoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/caballo56.png"))); // NOI18N
        peonButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/peon56.png"))); // NOI18N
        peonBlancoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/peon_white56.png"))); // NOI18N
        damasBlancoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/damas_white56.png"))); // NOI18N
        damasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/damas56.png"))); // NOI18N

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
            botones[i].addActionListener(new BotonPiezaActionListener());
        }
    }

    class BotonPiezaActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for(int i = 0; i < botones.length; i++){
                if(botones[i] == source){
                    activarButton(botones[i]);
                } else {
                    desactivarBoton(botones[i]);
                }
            }
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
        // TODO add your handling code here:
        try {
            int tamanyo = Integer.parseInt(dimensionesTextField.getText());
            if (tamanyo < 3 || tamanyo > 20) {
                //errorLabel.setText("El valor introducido está fuera de rango");
            } else {
                Ventana m = new Ventana("Euler", tamanyo);
                BTController controller = new BTController(m);
                m.setVisible(true);
                this.setVisible(false);
            }
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
