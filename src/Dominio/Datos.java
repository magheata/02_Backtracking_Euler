package Dominio;

import Dominio.Pieza.Pieza;

import java.io.File;
import java.net.URL;

public class Datos {

    private int dimension;
    private Pieza pieza = null;
    private int tablero[][];

    public Datos() {
        regenerar(8);
    }

    public Datos(int n) {
        regenerar(n);
    }

    public void regenerar(int d) {
        dimension = d;
        if (pieza == null) {
            setClasePieza();
        } else {
            setClasePieza(pieza.getNombre());
        }
        tablero = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getPieza() {
        return pieza.getNombre();
    }

    private void setClasePieza() { //Pone la primera pieza del paquete piezas
        //miramos cuantas piezas hay en el paquete datos.piezas
        Package pack = this.getClass().getPackage();
        String paquete = pack.getName(); // Ya tenemos el package de los datos
        paquete = paquete + ".Pieza";
        URL path = getClass().getResource("/" + paquete.replaceAll("\\.", "/"));
        File dir = new File(path.getPath());
        String[] nombres = dir.list();
        String[] aux = new String[nombres.length - 1];
        int pos = 0;
        for (int i = 0; i < nombres.length; i++) {
            if (!(nombres[i].contentEquals("Pieza.class"))) {
                aux[pos++] = nombres[i].substring(0, nombres[i].indexOf(".class"));
            }
        }
        nombres = aux;
        //seguro que la primera no es Pieza.class la instanciamos
        setClasePieza(paquete + "." + nombres[0]);
    }

    public void setClasePieza(String p) {
        try {
            Class c = Class.forName(p);
            pieza = (Pieza) c.newInstance();
            if (pieza.afectaDimension()) {
                pieza = (Pieza) c.getConstructor(int.class).newInstance(dimension);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Pieza getClasePieza() {
        return pieza;
    }

    public Imagen getImagen() {
        return pieza.getImagen();
    }

    public boolean hayPieza(int fila, int columna) {
        return (tablero[fila][columna] == 1);
    }

    public void ponPieza(int fila, int columna) {
        tablero[fila][columna] = 1;
    }

    public void quitaPieza(int fila, int columna) {
        tablero[fila][columna] = 0;
    }

    public int getNumMovs() {
        return pieza.getNumMovs();
    }

    public int getMovX(int i) {
        return pieza.getMovX(i);
    }

    public int getMovY(int i) {
        return pieza.getMovY(i);
    }
}
