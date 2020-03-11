package Dominio;

public class Tablero {
    protected Casilla[][] tablero;

    protected Casilla inicioPieza;

    public Tablero(int dimension){
        tablero = new Casilla[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                tablero[i][j] = new Casilla(i, j);
            }
        }
    }

    public void setInicioPieza(int x, int y){
        this.inicioPieza = new Casilla(x, y);
    }
}
