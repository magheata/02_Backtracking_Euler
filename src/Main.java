import Presentacion.VentanaInicial;

import javax.swing.*;

/**
 * Clase que inicializa la ejecución del programa
 */
public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(() -> {
                VentanaInicial window = new VentanaInicial();
                window.setVisible(true);
                window.pack();
            });
        }
        catch (Exception e) {
            // handle exception
        }
    }
}
