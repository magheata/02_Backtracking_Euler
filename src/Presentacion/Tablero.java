package Presentacion;

import Dominio.Casilla;
import Dominio.Datos;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tablero extends JPanel {
    private static final int SIZEPANEL = 660;
    private static final int BORDER_GAP = 30;
    private long lado;
    private int dimension;


    private Datos dat;
    private ImageIcon imgpieza;

    public Tablero(Datos d) {
        dat = d;
        setImagenPieza(d);
    }
    public Tablero(int dimension, Datos d){
        this.dimension = dimension;
        lado = (SIZEPANEL - (BORDER_GAP * 2))/ dimension;
        dat = d;
        //setImagenPieza(d);
    }


    public void setImagenPieza(Datos d) {
        //cogemos la pieza
        URL imageURL = getClass().getResource(dat.getImagen().toString());
        imgpieza = new ImageIcon(imageURL);
    }

    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int x = 0;
        Columnas
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

        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int dim = dat.getDimension();
        //calculamos el ancho y alto de la casilla
        int ancho = this.getWidth() / dim;
        int alto = this.getHeight() / dim;
        //Pintamos el tablero
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i % 2) == 0) { //la fila empieza por blanco
                    if ((j % 2) == 1) {
                        g.fillRect(j * ancho + BORDER_GAP, i * alto + BORDER_GAP, ancho, alto);
                    }
                } else // la fila empieza por negro
                {
                    if ((j % 2) == 0) {
                        g.fillRect(j * ancho + BORDER_GAP, i * alto + BORDER_GAP, ancho, alto);
                    }
                }
            }
        }
    }
    */
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int dim = dat.getDimension();
        //calculamos el ancho y alto de la casilla
        int ladoCasilla = (int) lado;
        //Pintamos el tablero
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i % 2) == 0) { //la fila empieza por blanco
                    if ((j % 2) == 1) {
                        g.fillRect(j * ladoCasilla + BORDER_GAP, i * ladoCasilla + BORDER_GAP, ladoCasilla, ladoCasilla);
                    }
                } else // la fila empieza por negro
                {
                    if ((j % 2) == 0) {
                        g.fillRect(j * ladoCasilla + BORDER_GAP, i * ladoCasilla + BORDER_GAP, ladoCasilla, ladoCasilla);
                    }
                }
            }
        }
    }

    public void setDatos(Datos d) {
        dat = d;
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

}
