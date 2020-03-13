package Presentacion;

import Aplicacion.BTController;
import Dominio.Interfaz.IGraphicsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelControl extends JPanel {

    private JButton incrDimensionButton, decrDimensionButton, startButton;
    private JProgressBar progressBar;
    private BTController controller;
    private int dimension;
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

        incrDimensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonMasAgentesActionPerformed(e);
                controller.modificarDimensionesTablero(dimension);
            }
        });

        decrDimensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonMenosAgentesActionPerformed(e);
                controller.modificarDimensionesTablero(dimension);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.getText() == "Start"){
                    incrDimensionButton.setEnabled(false);
                    decrDimensionButton.setEnabled(false);
                    startButton.setText("Stop");
                } else {
                    startButton.setText("Start");
                    incrDimensionButton.setEnabled(true);
                    decrDimensionButton.setEnabled(true);
                }
            }
        });

        this.add(decrDimensionButton);
        this.add(incrDimensionButton);
        this.add(startButton);
        this.add(progressBar);
    }
    private void ButtonMenosAgentesActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        if (!(dimension <= 5)) {
            dimension--;
        }
    }

    private void ButtonMasAgentesActionPerformed(ActionEvent evt) {
        if (!(dimension >= 15)) {
            dimension++;
        }
    }
}
