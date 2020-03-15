package Dominio.Interfaz;

import Dominio.Imagen;
import Dominio.Pieza.Pieza;

public interface IController {
    void setInicioPieza(long x, long y);
    Imagen getImagenPiezaSeleccionada();
    void crearDominioTablero(int dimension, int piezaSeleccionada);
    void startBacktrackingProcess();
    void pintarPieza(int x, int y, int visitada);
    void finalizacion(String s);

}
