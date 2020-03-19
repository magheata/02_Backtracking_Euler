/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Infraestructura;

import Aplicacion.BTController;
import Dominio.Interfaz.IRecorridoEulerService;
import Dominio.Tablero;

/**
 * Clase que contiene el método de buscar el recorrido euler dado un tablero
 */
public class RecorridoEulerService implements IRecorridoEulerService {

    private BTController controller;
    private int tamaño;
    private boolean hayRecorrido;
    private int casillasRecorridas;
    private Tablero tablero;
    private long duracionEjecucion;
    private Thread worker;


    /**
     *
     * @param controller
     */
    public RecorridoEulerService(BTController controller){
        this.controller = controller;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run() {
        hayRecorrido = false;
        tablero = controller.getTableroDominio();
        casillasRecorridas = 1;
        tamaño = (int) Math.pow(tablero.getDimension(), 2);
        duracionEjecucion = System.nanoTime(); /* Guardamos el momento en el que empieza el proceso*/
        backtrackingEuler(tablero.getInicioPieza().getX(), tablero.getInicioPieza().getY());
        duracionEjecucion = System.nanoTime() - duracionEjecucion; /* Miramos cuánto tiempo ha tardado */
        controller.mostrarCaminoEuler(hayRecorrido);
    }

    /**
     * Método recursivo que busca un camino euleriano
     * @param x
     * @param y
     */
    @Override
    public void backtrackingEuler(int x, int y) {
        int xt, yt;
        // Si no hemos visitado todas las piezas del tablero
        if (casillasRecorridas != tamaño) {
            //Para cada movimiento que puede realizar esta pieza
            for (int i = 0; i < tablero.getPieza().getNumMovs(); i++) {
                // Realizamos el movimiento relativo a la casilla en la que está actualmente la pieza
                xt = x + tablero.getPieza().getMovX(i);
                yt = y + tablero.getPieza().getMovY(i);
                // Si el movimiento es válido y no se ha visitado ya lo visitamos y actualizamos la posición de la pieza
                if (isMovimientoValido(tablero, xt, yt) && !tablero.getCasilla(xt, yt).isVisitada()){
                    casillasRecorridas++;
                    // Visitamos la casilla y le asignamos el índice de visita
                    tablero.getCasilla(xt, yt).setVisitada(true, casillasRecorridas);
                    // Llamamos al método recursivo con la nueva posicion y continuamos buscando el camino
                    backtrackingEuler(xt, yt);
                    // Si no hemos recorrido todas las casillas es que este movimiento no nos sirve
                    if(casillasRecorridas != tamaño){
                        // Quitamos la casilla de las visitadas
                        casillasRecorridas--;
                        tablero.getCasilla(xt, yt).setVisitada(false, -1);
                    } else {
                        // En caso contrario hemos encontrado el camino euleriano, salimos de la recursividad
                        hayRecorrido = true;
                        return;
                    }
                }
            }
        }
    }

    /**
     *
     * @param tablero
     * @param x
     * @param y
     * @return
     */
    private boolean isMovimientoValido(Tablero tablero, int x, int y){
        return (x < tablero.getDimension() && x >= 0) && (y < tablero.getDimension() && y >= 0);
    }

    /**
     *
     * @return
     */
    public long getDuracionEjecucion() {
        return duracionEjecucion;
    }

}
