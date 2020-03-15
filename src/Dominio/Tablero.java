package Dominio;

import Dominio.Pieza.Pieza;

import java.awt.*;

public class Tablero {

    protected Casilla[][] tablero;
    protected Casilla inicioPieza;
    private int dimension;
    private Pieza pieza = null;
    private int piezaSeleccionada;

    public Tablero(int dimension, int piezaSeleccionada){
        this.piezaSeleccionada = piezaSeleccionada;
        String piezaPath = "Dominio.Pieza.".concat(PiezasTablero.values()[piezaSeleccionada].name());
        setClasePieza(piezaPath);
        this.dimension = dimension;
        tablero = new Casilla[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                tablero[i][j] = new Casilla(i,j, new Color(0, 0, 0));
            }
        }
    }

    public void setInicioPieza(long x, long y){
        this.inicioPieza = new Casilla(x, y, new Color(0, 0, 0));
        this.tablero[(int)x][(int)y].setVisitada(true);
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
            pieza = (Pieza) c.newInstance();
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
}
