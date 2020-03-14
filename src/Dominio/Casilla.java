package Dominio;

import java.awt.*;

public class Casilla {
    private int x;
    private int y;
    private Color color;
    protected boolean visitada = false;

    public Casilla(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color=color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

