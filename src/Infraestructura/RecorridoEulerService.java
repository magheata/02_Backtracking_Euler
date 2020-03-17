package Infraestructura;

import Aplicacion.BTController;
import Dominio.Tablero;

public class RecorridoEulerService {

    private BTController controller;
    private int tamaño;
    private boolean hayRecorrido;
    private int casillasRecorridas;
    private Tablero tablero;
    public RecorridoEulerService(BTController controller){
        this.controller = controller;
    }

    public void encontrarRecorridoEuleriano(Tablero tablero){
        this.tablero = tablero;
        hayRecorrido = false;
        casillasRecorridas = 1;
        tamaño = (int) Math.pow(tablero.getDimension(), 2);
        backtrackingEuler(tablero, tablero.getInicioPieza().getX(), tablero.getInicioPieza().getY());
        controller.pintarTablero();
        if (hayRecorrido) {
            controller.finalizacion("Hemos encontrado una solución");
        } else {
            controller.finalizacion("No hemos encontrado una solución");
        }
    }

    private void backtrackingEuler(Tablero tablero, int x, int y) {
        int xt, yt;
        if (casillasRecorridas != tamaño) {
            for (int i = 0; i < tablero.getPieza().getNumMovs(); i++) {
                xt = x + tablero.getPieza().getMovX(i);
                yt = y + tablero.getPieza().getMovY(i);
                if (isMovimientoValido(tablero, xt, yt) && !tablero.getCasilla(xt, yt).isVisitada()){
                    casillasRecorridas++;
                    tablero.getCasilla(xt, yt).setVisitada(true, casillasRecorridas);
                    backtrackingEuler(tablero, xt, yt);
                    if(casillasRecorridas != tamaño){
                        casillasRecorridas--;
                        tablero.getCasilla(xt, yt).setVisitada(false, -1);
                    } else {
                        hayRecorrido = true;
                        return;
                    }
                }
            }
        }
    }

    private boolean isMovimientoValido(Tablero tablero, int x, int y){
        return (x < tablero.getDimension() && x >= 0) && (y < tablero.getDimension() && y >= 0);
    }
}
