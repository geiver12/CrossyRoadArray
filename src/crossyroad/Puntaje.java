/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossyroad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Geiver
 */
public class Puntaje implements Serializable{
     ArrayList<Integer> Puntos;
     ArrayList<String> Nombres;

    public Puntaje(ArrayList<String> Nombres, ArrayList<Integer> Puntos) {
        this.Nombres = Nombres;
        this.Puntos = Puntos;
    }

    public Puntaje() {
        Nombres=new ArrayList<String>();
        Puntos=new ArrayList<Integer>();
    }
}