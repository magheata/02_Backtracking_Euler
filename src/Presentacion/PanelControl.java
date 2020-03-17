package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel {

    private JButton incrDimensionButton, decrDimensionButton, startButton;
    private JProgressBar progressBar;
    private BTController controller;
    private int dimension;
    private final int MIN_DIMENSION = 3;
    private final int MAX_DIMENSION = 8;
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
                controller.modificarDimensionesTablero(dimension, true);
            }
        });

        decrDimensionButton.addActionListener(e -> {
            ButtonMenosAgentesActionPerformed();
            if (dimensionModificada){
                controller.modificarDimensionesTablero(dimension, false);
            }
        });

        startButton.addActionListener(e -> {
            if (startButton.getText() == "Start"){
                if (controller.isInicioPiezaDefinido()){
                    incrDimensionButton.setEnabled(false);
                    decrDimensionButton.setEnabled(false);
                    startButton.setText("Stop");
                    controller.modificarAccesoBotones();
                    controller.modificarAccesoTablero();
                    controller.startBacktrackingProcess();
                } else {
                    controller.mostrarMensajeAlUsuario("Debe poner una pieza en el tablero");
                }
            } else if (startButton.getText() == "Start"){
                startButton.setText("Stop");
                incrDimensionButton.setEnabled(true);
                decrDimensionButton.setEnabled(true);
                controller.modificarAccesoBotones();
                controller.modificarAccesoTablero();
            } else {
                startButton.setText("Start");
                controller.resetearTablero();
                controller.setPiezaSeleccionada(-1);
                controller.modificarAccesoBotones();
                controller.modificarAccesoTablero();
                incrDimensionButton.setEnabled(true);
                decrDimensionButton.setEnabled(true);
            }

        });

        this.add(decrDimensionButton);
        this.add(incrDimensionButton);
        this.add(startButton);
        this.add(progressBar);
    }

    public void ponerBotonReset(){
        startButton.setText("Reset");
    }

    private void ButtonMenosAgentesActionPerformed() {
        // TODO add your handling code here:
        if (!(dimension <= MIN_DIMENSION)) {
            dimension--;
            dimensionModificada = true;
        } else {
            dimensionModificada = false;
        }
    }

    private void ButtonMasAgentesActionPerformed() {
        if (!(dimension >= MAX_DIMENSION)) {
            dimension++;
            dimensionModificada = true;
        } else {
            dimensionModificada = false;
        }
    }
}
