package Infraestructura;

import Aplicacion.BTController;
import Dominio.Tablero;

public class Backtracking {

    private BTController controller;
    private int casillasRecorridas = 1;
    private int tamaño;
    private Tablero tablero;

    public Backtracking(BTController controller, Tablero tablero){
        this.controller = controller;
        this.tablero = tablero;
        BT(tablero, (int) tablero.getInicioPieza().getX(), (int) tablero.getInicioPieza().getY());
    }

    public void BT(Tablero tablero, int x, int y){
        tamaño= (int) Math.pow(tablero.getDimension(), 2);
        mover(tablero, x, y, casillasRecorridas);
        if (casillasRecorridas == tamaño) {
            controller.finalizacion("Hemos encontrado una solución");
        } else {
            controller.finalizacion("No hemos encontrado una solución");
        }
    }

    private void mover(Tablero tablero, int x, int y, int visitada) {
        // x e y temporales
        int xt = 0;
        int yt = 0;
        if (visitada < tamaño) {
            for (int i = 0; i < tablero.getPieza().getNumMovs(); i++) {
                xt = x + tablero.getPieza().getMovX(i);
                yt = y + tablero.getPieza().getMovY(i);
                if (isMovimientoValido(tablero, xt, yt)){
                    if (!tablero.getCasilla(xt, yt).isVisitada()) {
                        controller.pintarPieza(xt, yt,0);
                        controller.pintarPieza(x, y, visitada);
                        tablero.getCasilla(xt, yt).setVisitada(true);
                        visitada++;
                        mover(tablero, xt, yt,visitada);
                        if(visitada!=tamaño){
                            visitada--;
                            tablero.getCasilla(xt, yt).setVisitada(false);
                            controller.pintarPieza(x,y,0);
                        }
                        System.out.println(visitada);
                    }
                }
            }
        }
    }

    private boolean isMovimientoValido(Tablero tablero, int x, int y){
        return x < tablero.getDimension() && x >= 0 && y < tablero.getDimension() && y >= 0;
    }
}

