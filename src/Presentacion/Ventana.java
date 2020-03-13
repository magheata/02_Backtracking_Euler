package Presentacion;

import Aplicacion.BTController;
import Infraestructura.GraphicsService;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    public Ventana(BTController controller, String titulo, int dimension, int botonSeleccionado) throws HeadlessException {
        super(titulo);
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BorderLayout());
        Tablero tablero = new Tablero(controller, dimension, botonSeleccionado);
        controller.setTableroPresentacion(tablero);
        Menu menu = new Menu(controller, botonSeleccionado);
        PanelControl panelControl = new PanelControl(controller, dimension);
        panelNorth.add(panelControl, BorderLayout.NORTH);
        panelNorth.add(menu, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(panelNorth, BorderLayout.NORTH);
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}
