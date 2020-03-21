package Dominio.Pieza;

import Dominio.Imagen;

public class DamaWhite extends Pieza{
    public DamaWhite(){
        nombre = this.getClass().getName();
        imagen = new Imagen("src/Presentacion/Imagenes/damaswhite77.png");
        movx = new int[]{2, -2, 2, -2, 0, 0, 1, -1};
        movy = new int[]{2, 2, -2, -2, 1, -1, 0, 0};
    }
}
