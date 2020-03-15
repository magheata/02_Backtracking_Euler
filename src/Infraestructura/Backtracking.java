package Infraestructura;

import Aplicacion.BTController;
import Dominio.Tablero;

public class Backtracking {

    private BTController controller;
    private int casillasRecorridas = 0;

    public Backtracking(BTController controller){
        this.controller = controller;
    }

    public void BT(Tablero t, int x, int y){
        mover(t,x,y, casillasRecorridas);
        if (casillasRecorridas == t.getDimension()) {
            System.out.println("Hay solución");
        } else {
            System.out.println("No hay solución");
        }
    }

    private void mover(Tablero t, int x, int y, int v) {
        // x e y temporales
        int xt = 0;
        int yt = 0;
        if (v < t.getDimension()) {
            for (int i = 0; i < t.getPieza().getNumMovs(); i++) {
                xt = x + t.getPieza().getMovX(i);
                yt = y + t.getPieza().getMovY(i);
                if (!t.getCasilla(xt, yt).isVisitada()) {
                    v++;
                    mover(t, xt, yt,v);
                    if(v!=t.getDimension()){
                        v--;
                    }
                }
            }
        }
    }
}

