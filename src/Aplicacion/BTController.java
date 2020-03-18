/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Aplicacion;

import Dominio.Imagen;
import Dominio.Interfaz.IController;
import Dominio.PiezasTablero;
import Infraestructura.RecorridoEulerService;
import Presentacion.Menu;
import Presentacion.PanelControl;
import Presentacion.Tablero;
import Presentacion.Ventana;

/***
 * Controlador del programa que se encarga de realizar la comuniación entre los distintos elementos del proyecto
 */
public class BTController implements IController {
    private Dominio.Tablero tableroDominio;
    private final RecorridoEulerService recorridoEulerService = new RecorridoEulerService(this);
    private Tablero tableroPresentacion;
    private Menu menu;
    private PanelControl panelControl;
    private String piezasPath = "Dominio.Pieza";
    private Ventana ventana;
    private boolean inicioPiezaDefinido;

    /**
     *
     */
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

    public boolean isInicioPiezaDefinido() {
        return inicioPiezaDefinido;
    }
    //endregion

    /**
     *
     * @param nuevasDimensiones
     * @param incrementar
     */
    public void modificarDimensionesTablero(int nuevasDimensiones, boolean incrementar){
        //S
        tableroDominio.setDimension(nuevasDimensiones);
        tableroPresentacion.actualizarDimensiones(nuevasDimensiones, incrementar);
    }

    /**
     *
     * @param x
     * @param y
     */
    @Override
    public void setInicioPieza(int x, int y) {
        tableroDominio.resetearTablero();
        tableroDominio.setInicioPieza(x, y);
        this.inicioPiezaDefinido = true;
    }

    /**
     *
     * @return
     */
    @Override
    public Imagen getImagenPiezaSeleccionada() {
        return tableroDominio.getImagen();
    }

    /**
     *
     * @param dimension
     * @param piezaSeleccionada
     */
    @Override
    public void crearDominioTablero(int dimension, int piezaSeleccionada) {
        this.tableroDominio = new Dominio.Tablero(dimension, piezaSeleccionada);
    }

    /**
     *
     */
    @Override
    public void startBacktrackingProcess() {
        recorridoEulerService.encontrarRecorridoEuleriano(tableroDominio);
    }

    /**
     *
     * @param mensaje
     */
    @Override
    public void finalizacion(String mensaje){
        this.inicioPiezaDefinido = false;
        panelControl.ponerBotonReset();
        ventana.UserMsg(mensaje);
    }

    /**
     *
     */
    @Override
    public void resetearTablero() {
        tableroDominio.resetearTablero();
        tableroPresentacion.quitarPieza();
    }

    /**
     *
     */
    @Override
    public void pintarTablero() {
        tableroPresentacion.repaint();
    }

    /**
     *
     * @param s
     */
    @Override
    public void mostrarMensajeAlUsuario(String s) {
        ventana.UserMsg(s);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean isCasillaVisitada(int x, int y) {
        return tableroDominio.getCasilla(x, y).isVisitada();
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public int getOrdenVisitadaCasilla(int x, int y) {
        return tableroDominio.getCasilla(x, y).getOrdenVisitada();
    }

    /**
     *
     */
    @Override
    public void mostrarDuracionEjecucion() {
        panelControl.setTextoDuracionEjecucion(Long.toString(recorridoEulerService.getDuracionEjecucion()));
    }

    /**
     *
     */
    @Override
    public void modificarAccesoBotones() {
        menu.modificarAccesoBotones();
    }

    /**
     *
     */
    @Override
    public void modificarAccesoTablero() {
        tableroPresentacion.setTableroHabilitado(!tableroPresentacion.isTableroHabilitado());
    }
}
