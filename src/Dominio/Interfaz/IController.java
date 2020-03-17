package Dominio.Interfaz;

import Dominio.Imagen;

public interface IController {
    void setInicioPieza(int x, int y);
    Imagen getImagenPiezaSeleccionada();
    void crearDominioTablero(int dimension, int piezaSeleccionada);
    void startBacktrackingProcess();
    void modificarAccesoBotones();
    void modificarAccesoTablero();
    void finalizacion(String s);
    void resetearTablero();
    void pintarTablero();
    void mostrarMensajeAlUsuario(String s);
    boolean isCasillaVisitada(int x, int y);
    int getOrdenVisitadaCasilla(int x, int y);
}
