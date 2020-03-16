package Dominio;

import java.awt.*;

public class Casilla {
    private long x;
    private long y;
    protected boolean visitada = false;

    private int ordenVisitada;

    public Casilla(long x, long y){
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public boolean isVisitada() {
        return visitada;
    }

    public void setVisitada(boolean visitada, int ordenVisitada) {
        this.ordenVisitada = ordenVisitada;
        this.visitada = visitada;
    }


    public int getOrdenVisitada() {
        return ordenVisitada;
    }
}

