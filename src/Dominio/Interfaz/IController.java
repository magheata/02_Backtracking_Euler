/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Dominio.Interfaz;

import Dominio.Imagen;

public interface IController {
    void setInicioPieza(int x, int y);
    void mostrarCaminoEuler(boolean hayRecorrido);
    Imagen getImagenPiezaSeleccionada();
    void startBacktrackingProcess();
    void modificarAccesoBotones();
    void modificarAccesoTablero();
    void modificarDimensionesTablero(int nuevasDimensiones, boolean decrementar);
    void resetearTablero();
    void pintarTablero();
    void mostrarMensajeAlUsuario(String s);
    boolean isCasillaVisitada(int x, int y);
    int getOrdenVisitadaCasilla(int x, int y);
    void mostrarDuracionEjecucion();
    boolean dentroDeRango(int coordenada);
    int cuadrarRangoEnTablero(int coordenada);
}
