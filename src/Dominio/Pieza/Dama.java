package Dominio.Pieza;

import Dominio.Imagen;

public class Dama extends Pieza{
    public Dama(){
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/damas77.png");
        movx = new int[8];
        movy = new int[8];
        int pos = 0;

        //Abajo/Derecha
        movx[pos] = 1; //   oblicuo 1
        movy[pos++] = 1; //    oblicuo1

        //Abajo/Izquierda
        movx[pos] = -1; //   oblicuo 2
        movy[pos++] = 1; //    oblicuo2


        //Arriba/Derecha
        movx[pos] = 1; //   oblicuo 2
        movy[pos++] = -1; //    oblicuo2

        //Arriba/Izquierda
        movx[pos] = -1; //   oblicuo 2
        movy[pos++] = -1; //    oblicuo2

        //Abajo
        movx[pos] = 0; // vertical
        movy[pos++] = 1; //vertical

        //Arriba
        movx[pos] = 0; // vertical
        movy[pos++] = -1; //vertical


        //Derecha
        movx[pos] = 1; // horizontal
        movy[pos++] = 0; //horizontal

        //Izquierda
        movx[pos] = -1; // horizontal
        movy[pos++] = 0; //horizontal
    }
}
