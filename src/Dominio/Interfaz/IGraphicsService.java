package Dominio.Interfaz;

import Dominio.Pieza.Pieza;

public interface IGraphicsService {
    void setInicioPieza(long x, long y);

    Pieza pintarPieza();
}
