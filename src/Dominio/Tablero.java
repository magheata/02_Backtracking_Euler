/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Dominio;

import Dominio.Pieza.Pieza;

/**
 *
 */
public class Tablero {

    protected Casilla[][] tablero;
    protected Casilla inicioPieza;
    private int dimension;
    private Pieza pieza = null;

    /**
     *
     * @param dimension
     * @param piezaSeleccionada
     */
    public Tablero(int dimension, int piezaSeleccionada){
        String piezaPath = "Dominio.Pieza.".concat(PiezasTablero.values()[piezaSeleccionada].name());
        setClasePieza(piezaPath);
        this.dimension = dimension;
        inicializarCasillas();
    }

    /**
     *
     */
    private void inicializarCasillas(){
        tablero = new Casilla[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                tablero[i][j] = new Casilla(i,j);
                tablero[i][j].setVisitada(false, -1);
            }
        }
    }

    /**
     *
     */
    public void resetearTablero(){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                tablero[i][j].setVisitada(false, -1);
            }
        }
        this.inicioPieza = new Casilla(-1, -1);
    }

    /**
     * Sirve para saber si la coordenada está dentro del rango del tablero
     * @param i
     * @return
     */
    public boolean dentroDeRango(int i){
        return (i <= dimension - 1) && i >= 0;
    }

    /**
     * Método que sirve para cuadrar las coordenadas en caso de que se haga click fuera del tablero
     * @param i
     * @return
     */
    public int cuadrarRango(int i){
        if (i < 0){
            i = 0;
        } else {
            i = dimension - 1;
        }
        return i;
    }


    //region [GETTERS Y SETTERS]

    /**
     *
     * @param x
     * @param y
     */
    public void setInicioPieza(int x, int y){
        inicioPieza = new Casilla(x, y);
        inicioPieza.setVisitada(true, 1);
        tablero[x][y] = inicioPieza;
    }

    /**
     *
     * @return
     */
    public Casilla getInicioPieza() { return inicioPieza; }

    /**
     *
     * @return
     */
    public int getDimension() { return dimension; }

    /**
     *
     * @return
     */
    public Pieza getPieza() { return pieza; }

    /**
     *
     * @param p
     */
    public void setClasePieza(String p) {
        try {
            Class c = Class.forName(p);
            pieza = (Pieza) c.getDeclaredConstructor().newInstance();
            if (pieza.afectaDimension()) {
                pieza = (Pieza) c.getConstructor(int.class).newInstance(dimension);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public Imagen getImagen() { return pieza.getImagen(); }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Casilla getCasilla(int x , int y) { return tablero[x][y]; }

    /**
     *
     * @param dimension
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
        inicializarCasillas();
    }
    //endregion
}
