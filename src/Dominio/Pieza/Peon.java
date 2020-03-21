package Dominio.Pieza;

import Dominio.Imagen;

public class Peon extends Pieza{

    public Peon(){
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/peon77.png");
        movx = new int[]{0};
        movy = new int[]{1};
    }
}
