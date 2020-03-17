package Dominio;

public class Casilla {
    private int x;
    private int y;
    protected boolean visitada = false;

    private int ordenVisitada;

    public Casilla(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Casilla{" +
                "x=" + x +
                ", y=" + y +
                ", visitada=" + visitada +
                ", ordenVisitada=" + ordenVisitada +
                '}';
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

    public void setVisitada(boolean visitada, int ordenVisitada) {
        this.ordenVisitada = ordenVisitada;
        this.visitada = visitada;
    }

    public int getOrdenVisitada() {
        return ordenVisitada;
    }
}

