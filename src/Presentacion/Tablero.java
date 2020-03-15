package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

public class Tablero extends JPanel{
    private static final int SIZEPANEL = 660;
    private static final int BORDER_GAP = 30;

    private int lado;
    private int dimension;
    private int pieza_x, pieza_y = -1;

    private BTController controller;

    public Tablero(BTController controller, int dimension, int piezaSeleccionada){
        this.controller = controller;
        this.controller.crearDominioTablero(dimension, piezaSeleccionada);
        this.dimension = dimension;
        this.lado = (SIZEPANEL - (BORDER_GAP * 2))/ dimension;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                pieza_x = colocarEnCasilla((double) e.getX() / lado);
                pieza_y = colocarEnCasilla((double) e.getY() / lado);
                controller.setInicioPieza(pieza_x, pieza_y);
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //Pintamos el tablero
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ((i % 2) == 0) { //la fila empieza por blanco
                    if ((j % 2) == 1) {
                        g.fillRect(j * lado + BORDER_GAP, i * lado + BORDER_GAP, lado, lado);
                    }
                } else // la fila empieza por negro
                {
                    if ((j % 2) == 0) {
                        g.fillRect(j * lado + BORDER_GAP, i * lado + BORDER_GAP, lado, lado);
                    }
                }
            }
        }

        if (pieza_x != -1 && pieza_y != -1){
            pintarImagenEnCasilla(g, pieza_x, pieza_y);
        }
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZEPANEL, SIZEPANEL);
    }

    public void pintarImagenEnCasilla(Graphics g, int x, int y){
        pieza_x = (x * lado);
        pieza_y = (y * lado);
        controller.getImagenPiezaSeleccionada().paintComponent(g, (int) pieza_x + BORDER_GAP, (int) pieza_y + BORDER_GAP, (int) lado);
    }

    public void actualizarDimensiones(int nuevaDimension){
        this.dimension = nuevaDimension;
        this.lado = (SIZEPANEL - (BORDER_GAP * 2))/ nuevaDimension;
        this.pieza_y = -1;
        this.pieza_x = -1;
        repaint();
    }

    private int colocarEnCasilla(double i){
        int i_casilla;
        BigDecimal i_decimal = new BigDecimal(String.valueOf(i));
        int i_entero = i_decimal.intValue();
        BigDecimal i_resto = i_decimal.remainder(BigDecimal.ONE);
        String valorAComparar = "";
        if (dimension < 7){
            valorAComparar = "0.3";
        } else if (dimension < 9){
            valorAComparar = "0.5";
        } else {
            valorAComparar = "0.9";
        }
        if (i_resto.compareTo(new BigDecimal(valorAComparar)) == -1){
            i_casilla = i_entero - 1;
        } else {
            i_casilla =  i_entero;
        }

        if (!dentroDeRango(i_casilla)){
            return cuadrarRango(i_casilla);
        } else {
            return i_casilla;
        }
    }

    private boolean dentroDeRango(int i){
        return (i <= dimension - 1) && i >= 0;
    }

    private int cuadrarRango(int i){
        if (i < 0){
            i = 0;
        } else {
            i = dimension - 1;
        }
        return i;
    }
}
