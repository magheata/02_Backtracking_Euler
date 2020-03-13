package Aplicacion;

import Dominio.PiezasTablero;
import Presentacion.Tablero;

public class BTController {

    private Dominio.Tablero tableroDominio;
    private String piezaSeleccionada = "";

    public Tablero getTableroPresentacion() {
        return tableroPresentacion;
    }

    public void setTableroPresentacion(Tablero tableroPresentacion) {
        this.tableroPresentacion = tableroPresentacion;
    }

    private Tablero tableroPresentacion;
    private String piezasPath = "Dominio.Pieza";

    public BTController() {
    }

    public Dominio.Tablero getTableroDominio() {
        return tableroDominio;
    }

    public void setTableroDominio(Dominio.Tablero tableroDominio) {
        this.tableroDominio = tableroDominio;
    }

    public String getPiezaSeleccionada() {
        return piezaSeleccionada;
    }

    public void setPiezaSeleccionada(String piezaSeleccionada) {
        this.piezaSeleccionada = piezaSeleccionada;
    }

    public void setPiezaSeleccionada(int pieza){
        try {
            tableroDominio.setClasePieza(piezasPath.concat(".").concat(PiezasTablero.values()[pieza].name()));
            piezaSeleccionada = tableroDominio.getPieza().getNombre();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void modificarDimensionesTablero(int nuevasDimensiones){
        tableroPresentacion.actualizarDimensiones(nuevasDimensiones);
    }
}
