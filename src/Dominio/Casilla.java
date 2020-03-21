/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Dominio;

/**
 *
 */
public class Casilla {
    private int x;
    private int y;
    protected boolean visitada = false;
    private int ordenVisitada;

    /**
     *
     * @param x
     * @param y
     */
    public Casilla(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Casilla{" +
                "x=" + x +
                ", y=" + y +
                ", visitada=" + visitada +
                ", ordenVisitada=" + ordenVisitada +
                '}';
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return
     */
    public boolean isVisitada() {
        return visitada;
    }

    /**
     *
     * @param visitada
     * @param ordenVisitada
     */
    public void setVisitada(boolean visitada, int ordenVisitada) {
        this.ordenVisitada = ordenVisitada;
        this.visitada = visitada;
    }

    /**
     *
     * @return
     */
    public int getOrdenVisitada() {
        return ordenVisitada;
    }
}

