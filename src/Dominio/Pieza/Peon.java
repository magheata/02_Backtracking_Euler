package Dominio.Pieza;

import Dominio.Imagen;

public class Peon extends Pieza{

    public Peon(){
        imagen = new Imagen("/Presentacion/Imagenes/peon.png");
        movx = new int[1];
        movy = new int[1];
        int pos = 0;
        //Arriba
        movx[pos] = 0;
        movy[pos++] = 1;
    }
}
