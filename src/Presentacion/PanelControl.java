package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel {

    private JButton incrDimensionButton, decrDimensionButton, startButton;
    private JProgressBar progressBar;
    private BTController controller;
    private int dimension;

    private boolean dimensionModificada = false;

    public PanelControl(BTController controller, int dimension){
        this.controller = controller;
        this.dimension = dimension;
        initComponents();
    }

    private void initComponents(){
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        incrDimensionButton = new JButton();
        incrDimensionButton.setText("+");
        decrDimensionButton = new JButton();
        decrDimensionButton.setText("-");
        startButton = new JButton();
        startButton.setText("Start");

        progressBar = new JProgressBar();

        incrDimensionButton.addActionListener(e -> {
            ButtonMasAgentesActionPerformed();
            if (dimensionModificada){
                controller.modificarDimensionesTablero(dimension, false);
            }
        });

        decrDimensionButton.addActionListener(e -> {
            ButtonMenosAgentesActionPerformed();
            if (dimensionModificada){
                controller.modificarDimensionesTablero(dimension, true);
            }
        });

        startButton.addActionListener(e -> {
            if (startButton.getText() == "Start"){
                incrDimensionButton.setEnabled(false);
                decrDimensionButton.setEnabled(false);
                startButton.setText("Stop");
                controller.startBacktrackingProcess();
            } else if (startButton.getText() == "Start"){
                startButton.setText("Start");
                incrDimensionButton.setEnabled(true);
                decrDimensionButton.setEnabled(true);
            } else {
                //resetear tablero
            }
            controller.modificarAccesoBotones();
            controller.modificarAccesoTablero();
        });

        this.add(decrDimensionButton);
        this.add(incrDimensionButton);
        this.add(startButton);
        this.add(progressBar);
    }
    private void ButtonMenosAgentesActionPerformed() {
        // TODO add your handling code here:
        if (!(dimension <= 5)) {
            dimension--;
            dimensionModificada = true;
        } else {
            dimensionModificada = false;
        }
    }

    private void ButtonMasAgentesActionPerformed() {
        if (!(dimension >= 15)) {
            dimension++;
            dimensionModificada = true;
        } else {
            dimensionModificada = false;
        }
    }
}
