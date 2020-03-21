/**
 * @authors Miruna Andreea Gheata, Rafael Adrián Gil Cañestro
 */
package Presentacion;

import java.awt.*;

/**
 *
 */
public class FontFactory {

    /**
     * Método que sirve para cargar una fuente
     * @param name
     * @return
     */
    public Font getFont(String name) {
        Font font = null;
        if (name == null) {
            font = new Font("sans", Font.PLAIN, 24);
        }
        try {
            String fname = "/Presentacion/Fuentes/" + name;
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(fname));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }
}
