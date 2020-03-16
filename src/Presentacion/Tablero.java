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
    private int casilla_pieza_x, casilla_pieza_y = -1;

    private boolean tableroHabilitado = true;

    private BTController controller;

    public Tablero(BTController controller, int dimension, int piezaSeleccionada){
        this.controller = controller;
        this.controller.crearDominioTablero(dimension, piezaSeleccionada);
        this.dimension = dimension;
        this.lado = (SIZEPANEL - (BORDER_GAP * 2))/ dimension;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (tableroHabilitado){
                    casilla_pieza_x = colocarEnCasilla((double) e.getX() / lado);
                    casilla_pieza_y = colocarEnCasilla((double) e.getY() / lado);
                    controller.setInicioPieza(casilla_pieza_x, casilla_pieza_y);
                    repaint();
                }
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

        if (casilla_pieza_x != -1 && casilla_pieza_y != -1){
            pintarImagenEnCasilla(g, casilla_pieza_x, casilla_pieza_y);
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

    private void pintarImagenEnCasilla(Graphics g, int x, int y){
        controller.getImagenPiezaSeleccionada().paintComponent(g, (int) (x * lado) + BORDER_GAP, (int) (y * lado) + BORDER_GAP, (int) lado);
    }

    public void actualizarDimensiones(int nuevaDimension, boolean decrementar){
        int antiguaDimension = this.dimension;
        this.dimension = nuevaDimension;
        this.lado = (SIZEPANEL - (BORDER_GAP * 2))/ nuevaDimension;
        boolean modificado = false;
        if (decrementar){
            if (casilla_pieza_x  == (antiguaDimension - 1)){
                casilla_pieza_x--;
                modificado = true;
            } if(casilla_pieza_y == (antiguaDimension - 1)){
                casilla_pieza_y--;
                modificado = true;
            }
            if (modificado){
                controller.setInicioPieza(casilla_pieza_x, casilla_pieza_y);
            }
        }
        repaint();
    }

    public void setTableroHabilitado(boolean tableroHabilitado) {
        this.tableroHabilitado = tableroHabilitado;
    }

    public boolean isTableroHabilitado() {
        return tableroHabilitado;
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

    public void quitarPieza(){
        pintarPieza(-1, -1);
    }

    public void pintarPieza (int x , int y){
        this.casilla_pieza_x=x;
        this.casilla_pieza_y=y;
        repaint();
    }
}
