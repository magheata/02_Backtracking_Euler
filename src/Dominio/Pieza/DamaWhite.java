package Dominio.Pieza;

import Dominio.Imagen;

public class DamaWhite extends Pieza{
    public DamaWhite(){
        imagen = new Imagen("/Presentacion/Imagenes/dama_white.png");
        movx = new int[1];
        movy = new int[1];
        int pos = 0;
        //Arriba
        movx[pos] = 0;
        movy[pos++] = 1;
    }
}
