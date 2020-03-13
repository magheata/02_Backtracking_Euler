package Dominio;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imagen {
    private BufferedImage img;

    /**
     * Consntructor de la clase que genera la imagen
     *
     * @param s
     */
    public Imagen(String s) {
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    /**
     * Método que pinta la imagen, recibe por parámetro el tamaño que se desea
     * pintar
     *
     * @param g
     * @param x
     * @param y
     */
    public void paintComponent(Graphics g, int x, int y, int size) {
        g.drawImage(img, x, y,size,size, null);
    }
}
