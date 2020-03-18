/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;

/**
 * Ventana inicial que aparece al ejecutar el programa. Contiene:
 * 1. Elegir una pieza de las 6 disponibles. Por defecto la pieza seleccionada es el Peón.
 * 2. Elegir el tamaño del tablero: el rango oscila desde los 3 hasta las 8 casillas.
 * 3. Botón de "Start" que lleva a la ventana principal del programa
 */
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
    private final int MIN_DIMENSION = 3;
    private final int MAX_DIMENSION = 8;

    /**
     *  Constructor vacío
     */
    public VentanaInicial() {
        initComponents();
    }

    /**
     * Inicialización de los componentes
     */
    private void initComponents() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //Habilitamos el botón del Peón
        activarButton(peonButton);

        //Deshabilitamos el resto de botones de las piezas
        desactivarBoton(peonWhiteButton);
        desactivarBoton(reinaButton);
        desactivarBoton(caballoButton);
        desactivarBoton(damaButton);
        desactivarBoton(damaWhiteButton);

        startButton.addActionListener(e -> startActionPerformed());

        decrDimensionButton.addActionListener(e -> ButtonMenosAgentesActionPerformed());

        incrDimensionButton.addActionListener(e -> ButtonMasAgentesActionPerformed());

        /* Inicializamos los botones con la imagen correspondiente a la pieza que representan.
        * Para poder hacer esto de forma dinámica se guarda en la clase PiezasTablero los
        * nombres de las piezas en el orden en el que se desea que aparezcan en la ventana y se
        * accede a la foto mediante el iterador.
        * */
        for (int i = 0; i < botones.length; i++){
            JButton boton = botones[i];
            boton.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagesPath.concat(botonesImg[i]))));

            /*
            Se añade un actionListener a cada botón
             */
            boton.addActionListener(e -> {
                Object source = e.getSource();
                for(int j = 0; j < botones.length; j++){
                    //Si es el botón que se acaba de pulsar de activa
                    if(botones[j] == source){
                        activarButton(botones[j]);
                        selectedButton = j;
                    } else {
                        //Si no, se desactiva el botón
                        desactivarBoton(botones[j]);
                    }
                }
            });
        }
    }

    /**
     * Eliminar los bordes y poner el fondo transparente del botón
     * @param button
     */
    private void desactivarBoton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }

    /**
     * Añadir los bordes y poner el fondo del botón
     * @param button
     */
    private void activarButton(JButton button){
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
    }

    /**
     * Crear elemento Ventana y el BTController, y esconder la Ventana Incial
     */
    private void startActionPerformed() {
        BTController controller = new BTController();
        Ventana m = new Ventana(controller, "Euler", Integer.parseInt(dimensionesTextField.getText()), selectedButton);
        m.setVisible(true);
        this.setVisible(false);
    }

    /**
     * Decrementar la dimensión del Tablero
     */
    private void ButtonMenosAgentesActionPerformed() {
        // TODO add your handling code here:
        int numero = Integer.parseInt(dimensionesTextField.getText());
        if (!(numero <= MIN_DIMENSION)) {
            numero--;
            dimensionesTextField.setEditable(true);
            dimensionesTextField.setText(Integer.toString(numero));
            dimensionesTextField.setEditable(false);
        }
    }

    /**
     * Incrementar la dimensión del Tablero
     */
    private void ButtonMasAgentesActionPerformed() {
        int numero = Integer.parseInt(dimensionesTextField.getText());
        if (!(numero >= MAX_DIMENSION)) {
            numero++;
            dimensionesTextField.setEditable(true);
            dimensionesTextField.setText(Integer.toString(numero));
            dimensionesTextField.setEditable(false);
        }
    }
}

