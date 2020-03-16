package Aplicacion;

import Dominio.Imagen;
import Dominio.Interfaz.IController;
import Dominio.PiezasTablero;
import Infraestructura.Backtracking;
import Presentacion.Menu;
import Presentacion.PanelControl;
import Presentacion.Tablero;
import Presentacion.Ventana;

import java.awt.*;

public class BTController implements IController {

    private Dominio.Tablero tableroDominio;
    private Tablero tableroPresentacion;
    private Menu menu;
    private PanelControl panelControl;
    private String piezaSeleccionada = "";
    private String piezasPath = "Dominio.Pieza";
    private Ventana ventana;

    public BTController() { }

    //region [GETTERS AND SETTERS]
    public void setTableroPresentacion(Tablero tableroPresentacion) {
        this.tableroPresentacion = tableroPresentacion;
    }

    public void setPiezaSeleccionada(int pieza){
        try {
            if (pieza != -1){
                tableroDominio.setClasePieza(piezasPath.concat(".").concat(PiezasTablero.values()[pieza].name()));
                piezaSeleccionada = tableroDominio.getPieza().getNombre();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setVentana(Ventana ventana) {
        this.ventana = ventana;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setPanelControl(PanelControl panelControl) {
        this.panelControl = panelControl;
    }

    public Dominio.Tablero getTableroDominio() {
        return tableroDominio;
    }

    //endregion

    public void modificarDimensionesTablero(int nuevasDimensiones, boolean incrementar){
        tableroDominio.setDimension(nuevasDimensiones);
        tableroPresentacion.actualizarDimensiones(nuevasDimensiones, incrementar);
    }

    @Override
    public void setInicioPieza(long x, long y) {
        tableroDominio.setInicioPieza(x, y);
    }

    @Override
    public Imagen getImagenPiezaSeleccionada() {
        return tableroDominio.getImagen();
    }

    @Override
    public void crearDominioTablero(int dimension, int piezaSeleccionada) {
        tableroDominio = new Dominio.Tablero(dimension, piezaSeleccionada);
    }

    @Override
    public void startBacktrackingProcess() {
        new Backtracking(this, tableroDominio);
    }

    @Override
    public void pintarPieza(int x, int y, int visitada) {
        if(visitada==0){
            //Pintamos la pieza en la casilla nueva
            tableroPresentacion.pintarPieza(x, y);
        }else{
            //Pintamos el número en la casilla
            tableroPresentacion.pintarCasillaVisitada(x, y, visitada);
        }
    }

    @Override
    public void finalizacion(String s){
        ventana.UserMsg(s);
        panelControl.ponerBotonReset();
    }

    @Override
    public void resetearTablero() {
        tableroDominio.resetearTablero();
        tableroPresentacion.quitarPieza();
    }

    @Override
    public void modificarAccesoBotones() {
        menu.modificarAccesoBotones();
    }

    @Override
    public void modificarAccesoTablero() {
        tableroPresentacion.setTableroHabilitado(!tableroPresentacion.isTableroHabilitado());
    }
}
