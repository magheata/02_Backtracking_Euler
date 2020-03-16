package Infraestructura;

import Aplicacion.BTController;
import Dominio.Casilla;
import Dominio.Tablero;

import java.util.Stack;

public class Backtracking {

    private BTController controller;
    private int casillasRecorridas = 1;
    private int tamaño;
    private boolean acabado = false;
    private Tablero tablero;
    private Stack<Casilla> stackCasillas;

    public Backtracking(BTController controller, Tablero tablero){
        this.controller = controller;
        this.tablero = tablero;
        stackCasillas = new Stack<>();
        BT(tablero, (int) tablero.getInicioPieza().getX(), (int) tablero.getInicioPieza().getY());
    }

    public void BT(Tablero tablero, int x, int y){
        tamaño= (int) Math.pow(tablero.getDimension(), 2);
        int visitada=1;
        mover(x,y,visitada);
        if (acabado) {
            controller.finalizacion("Hemos encontrado una solución");
        } else {
            controller.finalizacion("No hemos encontrado una solución");
        }
    }

    private void mover(int x, int y, int visitada) {
        // x e y temporales
        int xt = 0;
        int yt = 0;
        if (visitada < tamaño) {
            for (int i = 0; i < tablero.getPieza().getNumMovs() && !acabado; i++) {
                xt = x + tablero.getPieza().getMovX(i);
                yt = y + tablero.getPieza().getMovY(i);
                if (isMovimientoValido(xt, yt)){
                    if (!tablero.getCasilla(xt, yt).isVisitada()) {
                        controller.pintarPieza(xt, yt,0);
                      /*  try{
                            Thread.sleep(500);
                        } catch(Exception ex){

                        }*/
                        controller.pintarPieza(x, y, visitada);
                        tablero.getCasilla(xt, yt).setVisitada(true);
                        visitada++;
                        mover(xt, yt,visitada);
                        if(visitada!=tamaño){
                            visitada--;
                            tablero.getCasilla(xt, yt).setVisitada(false);
                            controller.pintarPieza(x,y,0);
                        }else{
                            acabado=true;

                        }
                        }
                        System.out.println(visitada);
                    }
                }
            }
        }


    /* private boolean hayRecorridoEuler(int x, int y, int visitada) {
         //Inicio pieza
         stackCasillas.push(new Casilla(x, y));
         int xt, yt;
         while (visitada < tamaño && !stackCasillas.empty()) {
             Casilla casillaAnterior = stackCasillas.pop();
             x = (int) casillaAnterior.getX();
             y = (int) casillaAnterior.getY();
             for (int i = 0; i < tablero.getPieza().getNumMovs(); i++) {
                 xt = x + tablero.getPieza().getMovX(i);
                 yt = y + tablero.getPieza().getMovY(i);
                 visitada++;
                 if (isMovimientoValido(xt, yt) && !tablero.getCasilla(xt, yt).isVisitada()){
                     stackCasillas.push(new Casilla(xt, yt));
                     controller.pintarPieza(xt, yt,0);
                     try{
                         Thread.sleep(500);
                     } catch(Exception ex){
                     }
                     //controller.pintarPieza(x, y, visitada);
                     tablero.getCasilla(xt, yt).setVisitada(true);
                     x = xt;
                     y = yt;
                     System.out.println(visitada);
                 } else {
                     visitada--;
                 }
             }
         }
         if (visitada == tamaño){
             return true;
         } else {
             return false;
         }
     }*/

    private boolean isMovimientoValido(int x, int y){
        return x < tablero.getDimension() && x >= 0 && y < tablero.getDimension() && y >= 0;
    }
}

