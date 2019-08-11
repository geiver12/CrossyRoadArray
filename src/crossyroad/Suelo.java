/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossyroad;

import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author maria.mosquera
 */
public class Suelo extends ObjetoGrafico {

    public Suelo() {
    }

    public Suelo( ImageIcon imagen, int x, int y, int ancho, int alto,int TipoSuelo) {
        super(imagen, x, y, ancho, alto,TipoSuelo);
    }
    
    public Suelo(Rectangle obj,ImageIcon img, int tipo)
    {
        super(obj,img,tipo);
    }
}
