package Dominio.Pieza;

import Dominio.Imagen;

public class Dama extends Pieza{
    public Dama(){
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/damas77.png");
        movx = new int[1];
        movy = new int[1];
        int pos = 0;
        //Arriba
        movx[pos] = 0;
        movy[pos++] = 1;
    }
}
