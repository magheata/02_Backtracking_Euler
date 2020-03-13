package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;

public class Menu extends JPanel {

    private JButton[] botones;

    public Menu(BTController controller, int botonSeleccionado){
        Botones botones = new Botones(this, controller, botonSeleccionado);
        this.botones = botones.botones;
    }
}
