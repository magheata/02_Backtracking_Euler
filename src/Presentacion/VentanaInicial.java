package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicial extends JFrame {
    private static final int SIZEPANEL = 660;
    private JButton peonButton;
    private JButton caballoButton;
    private JButton reinaButton;
    private JButton button1Button;
    private JButton button2Button;
    private JButton button3Button;
    private JButton startButton;
    private JButton decrDimensionButton;
    private JTextField dimensionesTextField;
    private JButton incrDimensionButton;
    private JPanel mainPanel;


    public VentanaInicial() {
        initComponents();
    }

    private void initComponents() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        activarButton(peonButton);
        desactivarBoton(reinaButton);
        desactivarBoton(caballoButton);

        reinaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/reina56.png"))); // NOI18N
        caballoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/caballo56.png"))); // NOI18N
        peonButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/peon56.png"))); // NOI18N

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
        peonButton.addActionListener(new BotonPiezaActionListener());
        caballoButton.addActionListener(new BotonPiezaActionListener());
        reinaButton.addActionListener(new BotonPiezaActionListener());
    }

    class BotonPiezaActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == peonButton) {
                activarButton(peonButton);
                desactivarBoton(caballoButton);
                desactivarBoton(reinaButton);
                //paintPointsToWindow(AsymptoticCostsTypes.O1, 1);
            } else if (source == caballoButton) {
                activarButton(caballoButton);
                desactivarBoton(reinaButton);
                desactivarBoton(peonButton);
                //paintPointsToWindow(AsymptoticCostsTypes.ON, 2);
            } else if (source == reinaButton) {
                activarButton(reinaButton);
                desactivarBoton(caballoButton);
                desactivarBoton(peonButton);
                //paintPointsToWindow(AsymptoticCostsTypes.ONLOGN, 3);
            } else {
                //paintPointsToWindow(AsymptoticCostsTypes.ONSQR, 4);
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
        if (!(numero <= 1)) {
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
}
