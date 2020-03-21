package Dominio.Pieza;

import Dominio.Imagen;

public class PeonWhite extends Pieza{
    public PeonWhite(){
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/peonwhite77.png");
        movx = new int[]{0, 1, -1};
        movy = new int[]{1, 0, 0};
    }
}
