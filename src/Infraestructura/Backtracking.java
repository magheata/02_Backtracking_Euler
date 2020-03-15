package Infraestructura;

import Aplicacion.BTController;
import Dominio.Tablero;

public class Backtracking {

    private BTController controller;
    private int casillasRecorridas = 0;
    private int tamaño;

    public Backtracking(BTController controller){
        this.controller = controller;
    }

    public void BT(Tablero t, int x, int y){

        tamaño= (int) Math.sqrt(t.getDimension());
        mover(t,x,y, casillasRecorridas);
        if (casillasRecorridas == tamaño) {
            controller.finalizacion("Hemos encontrado una solución");
        } else {
            controller.finalizacion("No hemos encontrado una solución");
        }
    }

    private void mover(Tablero t, int x, int y, int visitada) {

        // x e y temporales
        int xt = 0;
        int yt = 0;
        if (visitada < tamaño) {
            for (int i = 0; i < t.getPieza().getNumMovs(); i++) {
                xt = x + t.getPieza().getMovX(i);
                yt = y + t.getPieza().getMovY(i);
                if (!t.getCasilla(xt, yt).isVisitada()) {
                    controller.pintarPieza(xt,yt,0);
                    controller.pintarPieza(x,y,visitada);
                    visitada++;
                    mover(t, xt, yt,visitada);
                    if(visitada!=tamaño){
                        visitada--;
                        controller.pintarPieza(x,y,0);
                    }
                }
            }
        }
    }
}

