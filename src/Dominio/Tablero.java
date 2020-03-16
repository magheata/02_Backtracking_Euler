package Dominio;

import Dominio.Pieza.Pieza;

public class Tablero {

    protected Casilla[][] tablero;
    protected Casilla inicioPieza;
    private int dimension;
    private Pieza pieza = null;

    public Tablero(int dimension, int piezaSeleccionada){
        String piezaPath = "Dominio.Pieza.".concat(PiezasTablero.values()[piezaSeleccionada].name());
        setClasePieza(piezaPath);
        this.dimension = dimension;
        inicializarCasillas();
    }

    private void inicializarCasillas(){
        tablero = new Casilla[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                tablero[i][j] = new Casilla(i,j);
            }
        }
    }

    public void resetearTablero(){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                tablero[i][j].setVisitada(false, -1);
            }
        }
    }

    //region [GETTERS Y SETTERS]
    public void setInicioPieza(long x, long y){
        this.inicioPieza = new Casilla(x, y);
        this.tablero[(int)x][(int)y].setVisitada(true, 1);
    }

    public Casilla getInicioPieza() {
        return inicioPieza;
    }

    public int getDimension() {
        return dimension;
    }

    public Pieza getPieza() {
        return pieza;
    }

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

    public Imagen getImagen() {
        return pieza.getImagen();
    }

    public Casilla getCasilla(int x , int y) {return tablero[x][y];}

    public void setDimension(int dimension) {
        this.dimension = dimension;
        inicializarCasillas();
    }
    //endregion
}
