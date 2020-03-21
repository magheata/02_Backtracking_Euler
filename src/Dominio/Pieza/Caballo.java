package Dominio.Pieza;

import Dominio.Imagen;

public class Caballo extends Pieza {

    public Caballo() {
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/caballo77.png");
        movx = new int[]{1, 2, 1, 2, -1, -2, -1, -2};
        movy = new int[]{2, 1, -2, -1, 2, 1, -2, -1};
    }
}
