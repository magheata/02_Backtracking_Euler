package Infraestructura;

import Dominio.Interfaz.IGraphicsService;
import Dominio.Pieza.Pieza;
import Dominio.Tablero;

public class GraphicsService implements IGraphicsService {

    private Tablero tableroDominio;
    private Presentacion.Tablero tableroPresentacion;

    public GraphicsService(Tablero tableroDominio, Presentacion.Tablero tableroPresentacion){
        this.tableroDominio = tableroDominio;
        this.tableroPresentacion = tableroPresentacion;
    }

    @Override
    public void setInicioPieza(long x, long y){
        tableroDominio.setInicioPieza(x, y);
    }

    @Override
    public Pieza pintarPieza() {
        return null;
    }
}
