/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Dominio.Interfaz;

import Dominio.Tablero;

public interface IRecorridoEulerService {
    void encontrarRecorridoEuleriano(Tablero tablero);
    void backtrackingEuler(int x, int y);
}
