package Aplicacion;

import Dominio.Datos;
import Dominio.Interfaz.IBTController;
import Dominio.Pieza.Peon;
import Dominio.Pieza.Pieza;
import Presentacion.Ventana;
import Presentacion.VentanaInicial;

public class BTController implements IBTController {

    private Ventana window;
    private Datos datos;
    private Pieza piezaSeleccionada = new Peon();

    public BTController(Ventana window, Pieza piezaSeleccionada) {
        this.window = window;
        this.piezaSeleccionada = piezaSeleccionada;
    }
}
