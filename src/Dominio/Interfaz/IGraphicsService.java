package Dominio.Interfaz;

import Dominio.Pieza.Pieza;

public interface IGraphicsService {
    void setInicioPieza(int x, int y);

    Pieza pintarPieza();
}
