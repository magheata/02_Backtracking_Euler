/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Presentacion;

import Aplicacion.BTController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

/**
 * Elemento gráfico que contendrá las casillas del tablero. En el se pintará la pieza y se pintará
 * el orden en el cual se han visitado las casillas por la Pieza una vez acabada la ejecución.
 */
public class Tablero extends JPanel{
    private static final int SIZE_PANEL = 660;
    private static final int BORDER_GAP = 30;
    private final int FONT_SIZE = 30;

    private int lado;
    private int dimension;
    private int casilla_pieza_x, casilla_pieza_y = -1;

    private boolean tableroHabilitado = true; /* Indica si se puede añadir una pieza al tablero */
    private Font font;
    private BTController controller;

    /**
     * Constructor. Dentro de el se define la fuente que se va a usar para pintar los números del orden de visita
     * para todas las casillas, se crea el Tablero (Dominio) y se define un MouseListener que servirá para detectar
     * que casilla ha seleccionado el usuario.
     * @param controller
     * @param font
     * @param dimension
     * @param piezaSeleccionada
     */
    public Tablero(BTController controller, Font font, int dimension, int piezaSeleccionada){
        this.setDoubleBuffered(true);
        this.font = font.deriveFont(Font.PLAIN, FONT_SIZE);
        this.controller = controller;
        this.controller.crearDominioTablero(dimension, piezaSeleccionada);
        this.dimension = dimension;
        this.lado = (SIZE_PANEL - (BORDER_GAP * 2))/ dimension;

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (tableroHabilitado){
                    casilla_pieza_x = colocarEnCasilla((double) e.getX() / lado);
                    casilla_pieza_y = colocarEnCasilla((double) e.getY() / lado);
                    // Definimos la Casilla incial de la pieza que se ha colocado
                    controller.setInicioPieza(casilla_pieza_x, casilla_pieza_y);
                    // Repintamos el tablero para que aparezca dicha pieza
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

    /**
     *
     * @param g
     */
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

        // Si las coordenadas de la pieza son válidas se pinta dicha pieza en la posición adecuada
        if (casilla_pieza_x != -1 && casilla_pieza_y != -1){
            pintarImagenEnCasilla(g, casilla_pieza_x, casilla_pieza_y);
        }

        // Se pinta el orden de visita de las casillas
        pintarCasillasVisitadas();
    }

    /**
     *
     */
    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE_PANEL, SIZE_PANEL);
    }

    /**
     * Método que sirve para redimensionar el tablero. Si ha
     * @param nuevaDimension
     * @param decrementar
     */
    public void actualizarDimensiones(int nuevaDimension, boolean decrementar){
        int antiguaDimension = this.dimension;
        this.dimension = nuevaDimension;
        this.lado = (SIZE_PANEL - (BORDER_GAP * 2))/ nuevaDimension;
        boolean modificado = false;
        if (decrementar){
            /* Si la pieza estaba puesta en los límites superiores del tablero y se quiere decrementar el tamaño,
            se modifica la posición de la pieza
            */
            if (casilla_pieza_x  == (antiguaDimension - 1)){
                casilla_pieza_x--;
                modificado = true;
            } if(casilla_pieza_y == (antiguaDimension - 1)){
                casilla_pieza_y--;
                modificado = true;
            }
            if (modificado){
                // Si se ha modificado se tiene que actualizar la Casilla inicial de la Pieza
                controller.setInicioPieza(casilla_pieza_x, casilla_pieza_y);
            }
        }
        //Repintamos para que aparezcan los cambios
        repaint();
    }

    //region [MÉTODOS DE PINTADO DE LAS CASILLAS]

    /**
     * Método que sirve para pintar una pieza en una posición del tablero determinada.
     * @param g
     * @param x
     * @param y
     */
    private void pintarImagenEnCasilla(Graphics g, int x, int y){
        controller.getImagenPiezaSeleccionada().paintComponent(g, (x * lado) + BORDER_GAP, (y * lado) + BORDER_GAP, lado);
    }

    /**
     * Método que sirve para pintar en las casillas el orden de visita correspondiente
     */
    private void pintarCasillasVisitadas(){
        Graphics2D g2 = (Graphics2D)this.getGraphics();
        g2.setFont(font);
        g2.setColor(Color.ORANGE);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                /* Comprobamos si la casilla actual se ha visitado, en caso afirmativo se pinta el orden de visita dentro
                de la casilla */
                if (controller.isCasillaVisitada(i, j)){
                    g2.drawString(String.valueOf(controller.getOrdenVisitadaCasilla(i, j)),
                            i * lado + BORDER_GAP + (lado / 2) - (lado/ 4),
                            j * lado + BORDER_GAP + (lado / 2) + (lado/ 4));
                }
            }
        }
    }
    //endregion

    /**
     *
     * @param tableroHabilitado
     */
    public void setTableroHabilitado(boolean tableroHabilitado) {
        this.tableroHabilitado = tableroHabilitado;
    }

    /**
     *
     * @return
     */
    public boolean isTableroHabilitado() {
        return tableroHabilitado;
    }
    //region [MÉTODOS DE RANGO]

    /**
     * Método que sirve para colocar una coordenada dentro del rango del tablero y en la posición deseada
     * @param coordenada coordenada que se quiere revisar
     * @return
     */
    private int colocarEnCasilla(double coordenada){
        int coordenada_casilla;
        // Pasamos a BigDecimal la coordenada
        BigDecimal coordenada_decimal = new BigDecimal(String.valueOf(coordenada));

        // Obtenemos la parte entera de la coordenada
        int coordenada_entero = coordenada_decimal.intValue();

        // Obtenemos la parte decimal de la coordenada
        BigDecimal coordenada_resto = coordenada_decimal.remainder(BigDecimal.ONE);

        String valorAComparar; /* Valor al que se compara el decimal de la coordenada para saber a qué casilla corresponde*/

        if (dimension < 7){
            valorAComparar = "0.3";
        } else if (dimension < 9){
            valorAComparar = "0.5";
        } else {
            valorAComparar = "0.9";
        }

        // Si el decimal es menor significa que se ha seleccionado la coordenada anterior
        if (coordenada_resto.compareTo(new BigDecimal(valorAComparar)) == -1){
            coordenada_casilla = coordenada_entero - 1;
        } else {
            //Si no, estamos en la coordenada adecuada
            coordenada_casilla =  coordenada_entero;
        }

        //Comprobamos que la coordenada está dentro de rango
        if (!dentroDeRango(coordenada_casilla)){
            return cuadrarRango(coordenada_casilla);
        } else {
            return coordenada_casilla;
        }
    }

    /**
     * Sirve para saber si la coordenada está dentro del rango del tablero
     * @param i
     * @return
     */
    private boolean dentroDeRango(int i){
        return (i <= dimension - 1) && i >= 0;
    }

    /**
     * Método que sirve para cuadrar las coordenadas en caso de que se haga click fuera del tablero
     * @param i
     * @return
     */
    private int cuadrarRango(int i){
        if (i < 0){
            i = 0;
        } else {
            i = dimension - 1;
        }
        return i;
    }
    //endregion

    //region [MÉTODOS PARA LAS PIEZAS]

    /**
     * Método que sirve para eliminar una pieza del tablero
     */
    public void quitarPieza(){
        pintarPieza(-1, -1);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void pintarPieza (int x , int y){
        this.casilla_pieza_x = x;
        this.casilla_pieza_y = y;
        repaint();
    }
    //endregion
}
