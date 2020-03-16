package Dominio;

import java.awt.*;

public class Casilla {
    private long x;
    private long y;
    private Color color;
    protected boolean visitada = false;

    public Casilla(long x, long y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

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

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
        this.color= Color.yellow;
    }
}

