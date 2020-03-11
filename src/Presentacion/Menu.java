package Presentacion;

import javax.swing.*;

public class Menu extends JPanel {

    public JButton reinaBtn;
    public JButton caballoBtn;
    public JButton peonBtn;


    public Menu(){
        reinaBtn = new JButton();
        caballoBtn = new JButton();
        peonBtn = new JButton();
        reinaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/reina56.png"))); // NOI18N
        caballoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/caballo56.png"))); // NOI18N
        peonBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/peon56.png"))); // NOI18N
        this.add(reinaBtn);
        this.add(caballoBtn);
        this.add(peonBtn);
    }
}
