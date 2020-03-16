package Presentacion;

import java.awt.*;

public class FontFactory {
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
