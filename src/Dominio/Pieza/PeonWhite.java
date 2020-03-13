package Dominio.Pieza;

import Dominio.Imagen;

public class PeonWhite extends Pieza{
    public PeonWhite(){
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/peonwhite77.png");
        movx = new int[1];
        movy = new int[1];
        int pos = 0;
        //Arriba
        movx[pos] = 0;
        movy[pos++] = 1;
    }
}
