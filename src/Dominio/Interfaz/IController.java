package Dominio.Interfaz;

import Dominio.Imagen;
import Dominio.Pieza.Pieza;

public interface IController {
    void setInicioPieza(long x, long y);
    Imagen getImagenPiezaSeleccionada();
    void crearDominioTablero(int dimension, int piezaSeleccionada);
    void startBacktrackingProcess();
    Pieza pintarPieza();
}
