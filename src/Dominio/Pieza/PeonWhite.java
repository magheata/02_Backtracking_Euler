package Dominio.Pieza;

import Dominio.Imagen;

public class PeonWhite extends Pieza{
    public PeonWhite(){
        imagen = new Imagen("/Presentacion/Imagenes/peon_white.png");
        movx = new int[1];
        movy = new int[1];
        int pos = 0;
        //Arriba
        movx[pos] = 0;
        movy[pos++] = 1;
    }
}
