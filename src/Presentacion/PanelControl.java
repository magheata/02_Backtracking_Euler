/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel {

    private final int MIN_DIMENSION = 3;
    private final int MAX_DIMENSION = 8;
    private final String LABEL_INCR_BTN = "+";
    private final String LABEL_DECR_BTN = "-";
    private final String LABEL_START_BTN = "Start";
    private final String LABEL_STOP_BTN = "Stop";
    private final String LABEL_RESTART_BTN = "Restart";
    private final String LABEL_DURACION_EJECUCION = "DURACIÓN EJECUCIÓN:";
    private final int FONT_SIZE = 12;
    private final String MENSAJE_ERROR_PIEZAINICIO = "Debe poner una pieza en el tablero";

    private int dimension;
    private boolean dimensionModificada = false; /* Sirve para saber si se debe repintar el tablero */

    private JButton incrDimensionButton, decrDimensionButton, startButton;
    private JTextArea tiempoTardadoTextArea;
    private JLabel tiempoTardadoLabel;
    private BTController controller;
    private Font font;

    /**
     *
     * @param controller
     * @param dimension
     * @param font fuente que se quiere usar
     */
    public PanelControl(BTController controller, int dimension, Font font){
        this.controller = controller;
        this.dimension = dimension;
        this.font = font.deriveFont(Font.PLAIN, FONT_SIZE);;
        initComponents();
    }

    /**
     *
     */
    private void initComponents(){
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);

        incrDimensionButton = new JButton(LABEL_INCR_BTN);
        decrDimensionButton = new JButton(LABEL_DECR_BTN);
        startButton = new JButton(LABEL_START_BTN);

        tiempoTardadoTextArea = new JTextArea();
        tiempoTardadoTextArea.setSize(new Dimension(20, 10));
        tiempoTardadoTextArea.setVisible(false);

        tiempoTardadoLabel = new JLabel(LABEL_DURACION_EJECUCION);
        tiempoTardadoLabel.setFont(font);
        tiempoTardadoLabel.setVisible(false);

        //region [AÑADIR ACTION LISTENERS A LOS BOTONES]
        incrDimensionButton.addActionListener(e -> {
            ButtonMasAgentesActionPerformed();
            //Si se ha modificado el tablero se notifica para que se pueda repintar
            if (dimensionModificada){
                controller.modificarDimensionesTablero(dimension, true);
            }
        });

        decrDimensionButton.addActionListener(e -> {
            ButtonMenosAgentesActionPerformed();
            //Si se ha modificado el tablero se notifica para que se pueda repintar
            if (dimensionModificada){
                controller.modificarDimensionesTablero(dimension, false);
            }
        });

        startButton.addActionListener(e -> {
            //Si el texto es Start se debe iniciar el proceso
            if (startButton.getText().equals(LABEL_START_BTN)){
                // Si la pieza está colocada se inicia el proceso
                if (controller.isInicioPiezaDefinido()){
                    // Se deshabilitan los botones de +/- dimension del tablero
                    disableDimensionButtons();
                    startButton.setText(LABEL_STOP_BTN);

                    // Deshabilitamos los botones de las piezas
                    controller.modificarAccesoBotones();
                    // Deshabilitamos el tablero
                    controller.modificarAccesoTablero();
                    //Iniciamos el proceso de backtracking
                    controller.startBacktrackingProcess();
                } else {
                    // En caso contrario se notifica al usuario de que falta la pieza
                    controller.mostrarMensajeAlUsuario(MENSAJE_ERROR_PIEZAINICIO);
                }
            } else if (startButton.getText().equals(LABEL_STOP_BTN)){
                //Si el texto es de parar se deben habilitar los botones
                startButton.setText(LABEL_START_BTN);
                enableDimensionButtons();
                controller.modificarAccesoBotones();
                controller.modificarAccesoTablero();
            } else {
                /* En caso contrario el texto es Restart, por lo que se deben resetear todas las variables de
                * control y de interés */
                startButton.setText(LABEL_START_BTN);
                tiempoTardadoTextArea.setText("");
                tiempoTardadoLabel.setVisible(false);
                tiempoTardadoTextArea.setVisible(false);

                // Se limpia el tablero
                controller.resetearTablero();
                // Se elimina la pieza seleccionada
                controller.setPiezaSeleccionada(-1);
                // Se habilitan los botones de las piezas y el tablero
                controller.modificarAccesoBotones();
                controller.modificarAccesoTablero();
                // Se habilitan los botones de +/- del las dimensiones del tablero
                enableDimensionButtons();
            }
        });
        //endregion

        this.add(decrDimensionButton);
        this.add(incrDimensionButton);
        this.add(startButton);
        this.add(tiempoTardadoLabel);
        this.add(tiempoTardadoTextArea);
    }

    /**
     *
     */
    public void ponerBotonReset(){
        startButton.setText(LABEL_RESTART_BTN);
    }

    /**
     *
     * @param texto
     */
    public void setTextoDuracionEjecucion(String texto){
        tiempoTardadoLabel.setVisible(true);
        tiempoTardadoTextArea.setVisible(true);
        tiempoTardadoTextArea.setOpaque(false);
        tiempoTardadoTextArea.setFont(font);
        tiempoTardadoTextArea.setText(texto + "ms");
    }

    //region [PRIVATE METHODS]

    /**
     * Decrementar las dimensiones del tablero si es posible
     */
    private void ButtonMenosAgentesActionPerformed() {
        if (!(dimension <= MIN_DIMENSION)) {
            dimension--;
            dimensionModificada = true;
        } else {
            dimensionModificada = false;
        }
    }

    /**
     * Incrementar las dimensiones del tablero si es posible
     */
    private void ButtonMasAgentesActionPerformed() {
        if (!(dimension >= MAX_DIMENSION)) {
            dimension++;
            dimensionModificada = true;
        } else {
            dimensionModificada = false;
        }
    }

    /**
     *
     */
    private void enableDimensionButtons(){
        incrDimensionButton.setEnabled(true);
        decrDimensionButton.setEnabled(true);
    }

    /**
     *
     */
    private void disableDimensionButtons(){
        incrDimensionButton.setEnabled(true);
        decrDimensionButton.setEnabled(true);
    }
    //endregion
}
