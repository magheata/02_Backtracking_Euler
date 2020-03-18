/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del programa que contiene:
 * 1. Un menú con las distintas piezas que se pueden seleccionar
 * 2. El tablero N x N
 * 3. Un panel de control que contiene:
 *      3.1. Unos botones para modificar el tamaño del tablero
 *      3.2. Un botón de control para Iniciar, Parar o Resetear el programa.
 *      3.3. Un JTextFrame en el que se mostrará el tiempo que ha tardado el programa en ejecutarse
 * 4. Un JOptionPane mediante el cual se comunicarán mensajes al usuario
 */
public class Ventana extends JFrame {

    private JOptionPane messageOptionPane;
    private FontFactory fontFactory;
    private Font font;
    private final String FONT_NAME = "PressStart2P.ttf";

    /**
     * Constructor. Dentro de el se crean los elementos Tablero (Presentación), Menú y PanelControl
     * @param controller
     * @param titulo
     * @param dimension
     * @param botonSeleccionado índice del botón que se ha seleccionado, servirá para seleccionar la pieza elegida
     * @throws HeadlessException
     */
    public Ventana(BTController controller, String titulo, int dimension, int botonSeleccionado) throws HeadlessException {
        super(titulo);
        /* Se define la fuente que se va a utilizay y se les pasa al Tablero y al PanelControl para que la usen */
        this.fontFactory = new FontFactory();
        font = fontFactory.getFont(FONT_NAME);

        JPanel panelNorth = new JPanel();
        messageOptionPane = new JOptionPane();
        panelNorth.setLayout(new BorderLayout());

        Tablero tablero = new Tablero(controller, font, dimension, botonSeleccionado);

        //añadimos la referencia del Tablero al Controlador
        controller.setTableroPresentacion(tablero);

        Menu menu = new Menu(controller, botonSeleccionado);

        //añadimos la referencia del Menu al Controlador
        controller.setMenu(menu);

        controller.setVentana(this);

        PanelControl panelControl = new PanelControl(controller, dimension, font);

        //añadimos la referencia del PanelControl al Controlador
        controller.setPanelControl(panelControl);

        panelNorth.add(panelControl, BorderLayout.NORTH);
        panelNorth.add(menu, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(panelNorth, BorderLayout.NORTH);
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Método para mostrar los mensajes por pantalla al usuario
     * @param mensaje
     */
    public void UserMsg(String mensaje){
        messageOptionPane.showMessageDialog(this.getContentPane(), mensaje);
    }
}
