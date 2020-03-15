package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    JOptionPane Msg;

    public Ventana(BTController controller, String titulo, int dimension, int botonSeleccionado) throws HeadlessException {
        super(titulo);

        JPanel panelNorth = new JPanel();
         Msg = new JOptionPane();
        panelNorth.setLayout(new BorderLayout());

        Tablero tablero = new Tablero(controller, dimension, botonSeleccionado);
        controller.setTableroPresentacion(tablero);

        Menu menu = new Menu(controller, botonSeleccionado);
        controller.setMenu(menu);

        controller.setV(this);

        PanelControl panelControl = new PanelControl(controller, dimension);
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

    public void UserMsg(String s){
        Msg.showMessageDialog(getContentPane(),s);
    }
}
