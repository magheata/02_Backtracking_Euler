package Presentacion;

import javax.swing.*;

public class VentanaMsg extends JFrame{
    private JTextPane texto;
    private JPanel Mensaje;

    public VentanaMsg(String s){ initComponents(s);}

    private void initComponents(String s) {
        setContentPane(Mensaje);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        texto.setText(s);
        texto.setVisible(true);
    }


}
