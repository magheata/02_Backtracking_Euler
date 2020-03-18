import Presentacion.VentanaInicial;

import javax.swing.*;

/**
 *
 */
public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            VentanaInicial window = new VentanaInicial();
            window.setVisible(true);
            window.pack();
        }
        catch (Exception e) {
            // handle exception
        }
    }
}
