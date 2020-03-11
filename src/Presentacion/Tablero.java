package Presentacion;

import Dominio.Casilla;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    private static final int SIZEPANEL = 660;
    private static final int BORDER_GAP = 30;
    private long lado;
    private int dimension;

    public Tablero(int dimension){
        this.dimension = dimension;
        lado = (SIZEPANEL - (BORDER_GAP * 2))/ dimension;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZEPANEL, SIZEPANEL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int x = 0;
        //Columnas
        for (int i = 0; i <= this.dimension; i++) {
            g2.drawLine(BORDER_GAP + x, getHeight() - BORDER_GAP, BORDER_GAP + x, BORDER_GAP);
            x += lado;
        }
        //Filas
        int y = 0;
        for (int j = 0; j <= this.dimension; j++) {
            g2.drawLine(BORDER_GAP, BORDER_GAP + y, getWidth() - BORDER_GAP, BORDER_GAP + y);
            y += lado;
        }
    }
}
