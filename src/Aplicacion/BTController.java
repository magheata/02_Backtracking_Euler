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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
    private boolean procesoAcabado = true;

    /* Sirve para añadir PropertyChangeListeners a las variables de esta clase */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     *
     */
    public BTController() {}

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    /**
     *
     * @param nuevasDimensiones
     * @param decrementar
     */
    @Override
    public void modificarDimensionesTablero(int nuevasDimensiones, boolean decrementar){
        int antiguaDimension = this.tableroDominio.getDimension();
        tableroDominio.setDimension(nuevasDimensiones);
        if (inicioPiezaDefinido){
            int coordenada_x = tableroDominio.getInicioPieza().getX();
            int coordenada_y = tableroDominio.getInicioPieza().getY();
            if (decrementar){
            /* Si la pieza estaba puesta en los límites superiores del tablero y se quiere decrementar el tamaño,
            se modifica la posición de la pieza
            */
                if (coordenada_x  == (antiguaDimension - 1)){
                    coordenada_x--;
                } if(coordenada_y == (antiguaDimension - 1)){
                    coordenada_y--;
                }
            }
            setInicioPieza(coordenada_x, coordenada_y);
        }
        tableroPresentacion.actualizarDimensiones(nuevasDimensiones);
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
     */
    @Override
    public void startBacktrackingProcess() {
        procesoAcabado = false;
        recorridoEulerService.start();
    }

    @Override
    public void mostrarCaminoEuler(boolean hayRecorrido) {
        procesoAcabado = true;
        changes.firePropertyChange("procesoAcabado", false, true);
        mostrarDuracionEjecucion(); // Mostramos el tiempo total que ha tardado
        panelControl.ponerBotonReset();
        if (hayRecorrido) {
            pintarTablero(); // Pintamos el tablero con las casillas actualizadas
            mostrarMensajeAlUsuario("Hemos encontrado una solución");
        } else {
            mostrarMensajeAlUsuario("No hemos encontrado una solución");
        }
    }

    /**
     *
     */
    @Override
    public void resetearTablero() {
        tableroDominio.resetearTablero();
        tableroPresentacion.quitarPieza();
        this.inicioPiezaDefinido = false;
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

    @Override
    public boolean dentroDeRango(int coordenada) {
        return tableroDominio.dentroDeRango(coordenada);
    }

    @Override
    public int cuadrarRangoEnTablero(int coordenada) {
        return tableroDominio.cuadrarRango(coordenada);
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

    //region [GETTERS AND SETTERS]
    public void setTableroPresentacion(Tablero tableroPresentacion) {
        this.tableroPresentacion = tableroPresentacion;
    }

    public void setPiezaSeleccionada(int pieza){
        try {
            if (pieza != -1 && tableroDominio != null){
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

    public void setTableroDominio(Dominio.Tablero tableroDominio) {
        this.tableroDominio = tableroDominio;
    }

    public boolean isInicioPiezaDefinido() {
        return inicioPiezaDefinido;
    }

    public Dominio.Tablero getTableroDominio() { return tableroDominio; }

    public boolean isProcesoAcabado() { return procesoAcabado; }

    //endregion
}
