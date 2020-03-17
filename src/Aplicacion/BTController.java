package Aplicacion;

import Dominio.Imagen;
import Dominio.Interfaz.IController;
import Dominio.PiezasTablero;
import Infraestructura.RecorridoEulerService;
import Presentacion.Menu;
import Presentacion.PanelControl;
import Presentacion.Tablero;
import Presentacion.Ventana;

public class BTController implements IController {
    private Dominio.Tablero tableroDominio;
    private final RecorridoEulerService recorridoEulerService = new RecorridoEulerService(this);
    private Tablero tableroPresentacion;
    private Menu menu;
    private PanelControl panelControl;
    private String piezasPath = "Dominio.Pieza";
    private Ventana ventana;
    private boolean inicioPiezaDefinido;

    public BTController() {

    }

    //region [GETTERS AND SETTERS]
    public void setTableroPresentacion(Tablero tableroPresentacion) {
        this.tableroPresentacion = tableroPresentacion;
    }

    public void setPiezaSeleccionada(int pieza){
        try {
            if (pieza != -1){
                resetearTablero();
                this.inicioPiezaDefinido = false;
                tableroDominio.setClasePieza(piezasPath.concat(".").concat(PiezasTablero.values()[pieza].name()));
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

    public boolean isInicioPiezaDefinido() {
        return inicioPiezaDefinido;
    }
    //endregion

    public void modificarDimensionesTablero(int nuevasDimensiones, boolean incrementar){
        tableroDominio.setDimension(nuevasDimensiones);
        tableroPresentacion.actualizarDimensiones(nuevasDimensiones, incrementar);
    }

    @Override
    public void setInicioPieza(int x, int y) {
        tableroDominio.resetearTablero();
        tableroDominio.setInicioPieza(x, y);
        this.inicioPiezaDefinido = true;
    }

    @Override
    public Imagen getImagenPiezaSeleccionada() {
        return tableroDominio.getImagen();
    }

    @Override
    public void crearDominioTablero(int dimension, int piezaSeleccionada) {
        this.tableroDominio = new Dominio.Tablero(dimension, piezaSeleccionada);
    }

    @Override
    public void startBacktrackingProcess() {
        recorridoEulerService.encontrarRecorridoEuleriano(tableroDominio);
    }

    @Override
    public void finalizacion(String mensaje){
        this.inicioPiezaDefinido = false;
        panelControl.ponerBotonReset();
        ventana.UserMsg(mensaje);
    }

    @Override
    public void resetearTablero() {
        tableroDominio.resetearTablero();
        tableroPresentacion.quitarPieza();
    }

    @Override
    public void pintarTablero() {
        tableroPresentacion.repaint();
    }

    @Override
    public void mostrarMensajeAlUsuario(String s) {
        ventana.UserMsg(s);
    }

    @Override
    public boolean isCasillaVisitada(int x, int y) {
        return tableroDominio.getCasilla(x, y).isVisitada();
    }

    @Override
    public int getOrdenVisitadaCasilla(int x, int y) {
        return tableroDominio.getCasilla(x, y).getOrdenVisitada();
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
