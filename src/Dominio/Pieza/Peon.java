package Dominio.Pieza;

public class Peon extends Pieza{

    public Peon(){
        imagen = "/Presentacion/Imagenes/peon.png";
        movx = new int[4];
        movy = new int[4];
        int pos = 0;
        //Arriba
        movx[pos] = 0;
        movy[pos++] = -1;
        //Abajo
        movx[pos] = 0;
        movy[pos++] = 1;
        //Izquierda
        movx[pos] = -1;
        movy[pos++] = 0;
        //Derecha
        movx[pos] = 1;
        movy[pos++] = 0;
    }
}
