package Aplicacion;

import Dominio.Imagen;
import Dominio.Interfaz.IController;
import Dominio.Pieza.Pieza;
import Dominio.PiezasTablero;
import Infraestructura.Backtracking;
import Presentacion.Menu;
import Presentacion.PanelControl;
import Presentacion.Tablero;

public class BTController implements IController {

    private Dominio.Tablero tableroDominio;
    private Tablero tableroPresentacion;
    private Menu menu;
    private Backtracking backtracking;
    private PanelControl panelControl;
    private String piezaSeleccionada = "";
    private String piezasPath = "Dominio.Pieza";

    public BTController() {}

    // GETTERS AND SETTERS
    public void setTableroPresentacion(Tablero tableroPresentacion) {
        this.tableroPresentacion = tableroPresentacion;
    }

    public void setPiezaSeleccionada(int pieza){
        try {
            tableroDominio.setClasePieza(piezasPath.concat(".").concat(PiezasTablero.values()[pieza].name()));
            piezaSeleccionada = tableroDominio.getPieza().getNombre();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setPanelControl(PanelControl panelControl) {
        this.panelControl = panelControl;
    }

    public void modificarDimensionesTablero(int nuevasDimensiones, boolean incrementar){
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
        this.backtracking = new Backtracking(this);
    }

    @Override
    public Pieza pintarPieza() {
        return null;
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
