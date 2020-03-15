package Presentacion;

import Aplicacion.BTController;
import Infraestructura.GraphicsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tablero extends JPanel{
    private static final int SIZEPANEL = 660;
    private static final int BORDER_GAP = 30;
    private long lado;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    private int dimension;

    private long pieza_x, pieza_y= -1;
    private GraphicsService graphicsService;
    private Dominio.Tablero tableroDominio;
    private ImageIcon imgpieza;

    public Tablero(BTController controller, int dimension, int piezaSeleccionada){
        this.tableroDominio = new Dominio.Tablero(dimension, piezaSeleccionada);
        this.graphicsService = new GraphicsService(tableroDominio, this);
        controller.setTableroDominio(tableroDominio);
        this.dimension = dimension;
        lado = (SIZEPANEL - (BORDER_GAP * 2))/ dimension;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("X: " + e.getX() / lado + " Y: " + e.getY() / lado);
                pieza_x = (e.getX() / lado);
                pieza_y = (e.getY() / lado);
                System.out.println("X:" + e.getX() + " Y:" + e.getY());
                if (!dentroDeRango(pieza_x)){
                    pieza_x = cuadrarRango(pieza_x);
                }
                if (!dentroDeRango(pieza_y)){
                    pieza_y = cuadrarRango(pieza_y);
                }
                graphicsService.setInicioPieza(pieza_x, pieza_y);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setImagenPieza(tableroDominio);
    }

    private boolean dentroDeRango(long i){
        return (i <= dimension - 1) && i >= 0;
    }

    private long cuadrarRango(long i){
        if (i < 0){
            i = 0;
        } else {
            i = dimension - 1;
        }
        return i;
    }

    public void setImagenPieza(Dominio.Tablero tableroDominio) {
        //cogemos la pieza
        String pieza = tableroDominio.getPieza().getNombre();
        String nombrePieza = pieza.split("\\.")[2].toLowerCase() + "77";
        imgpieza = new ImageIcon("Presentacion/Imagenes/" + nombrePieza + ".png");
    }

    @Override
    public void paint(Graphics g) {
        pintarTablero(g);
    }

    public void setTableroDominio(Dominio.Tablero tableroDominio) {
        this.tableroDominio = tableroDominio;
    }

    private void pintarTablero(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //calculamos el ancho y alto de la casilla
        int ladoCasilla = (int) lado;
        //Pintamos el tablero
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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

        if (pieza_x != -1 && pieza_y != -1){
            pintarImagenEnCasilla(tableroDominio, g, pieza_x, pieza_y);
        }
    }

    public void pintarImagenEnCasilla(Dominio.Tablero tableroDominio, Graphics g, long x, long y){
        pieza_x = (x * lado);
        pieza_y = (y * lado);
        tableroDominio.getImagen().paintComponent(g, (int) pieza_x + BORDER_GAP, (int) pieza_y + BORDER_GAP, (int) lado);
    }

    public void actualizarDimensiones(int nuevaDimension){
        this.dimension = nuevaDimension;
        this.lado = (SIZEPANEL - (BORDER_GAP * 2))/ nuevaDimension;
        this.pieza_y = -1;
        this.pieza_x = -1;
        repaint();
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
