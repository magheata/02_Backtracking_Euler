package Infraestructura;

import Dominio.Tablero;

public class Backtracking {
    int v = 0;
    void BT(Tablero t, int x, int y){


    mover(t,x,y,v);
    if (v== t.getDimension()) {
        System.out.println("Hay solución");
    }else{
        System.out.println("No hay solución");
       }



    }







    public void mover(Tablero t, int x, int y, int v) {
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
                }
            }
        }
    }
}

