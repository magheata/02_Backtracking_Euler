package Presentacion;

import Dominio.Datos;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    public Ventana(String titulo, int dimension) throws HeadlessException {
        super(titulo);
        Datos datos = new Datos(dimension);
        Tablero tablero = new Tablero(dimension, datos);
        Menu menu = new Menu();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(menu, BorderLayout.NORTH);
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}
