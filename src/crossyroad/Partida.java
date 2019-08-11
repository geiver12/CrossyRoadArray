
package crossyroad;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;


public class Partida implements Serializable{
                
            int Puntaje,Monedas,segundos,minutos;
            String Nombre;
            int Seguro;
            Rectangle mascota;
            ArrayList<Rectangle> PosicionSuelo;
            ArrayList<Integer> Tiposuelo;
            ArrayList<ArrayList<Rectangle>> array ;
            int Tiempo;
            

    public Partida(ArrayList<Suelo> objeto,int Puntaje, int Monedas, int segundos, int minutos, String Nombre, Mascota mascota,int Tiempo,int Seguro) {
        PosicionSuelo=new ArrayList<Rectangle>();
        Tiposuelo=new ArrayList<Integer>();
        array = new ArrayList<ArrayList<Rectangle>>();
        this.Tiempo=Tiempo;
        int i=0;
        for(Suelo s:objeto){
            array.add(new ArrayList<Rectangle>());
        PosicionSuelo.add(s.rectaObj);
        Tiposuelo.add(s.TipoSuelo);
                for(Obstaculos o:s.obstaculos)
                {
                    Rectangle r=new Rectangle(o.Posx,o.PosY,o.Velocidad,o.TipoObjeto);
                    array.get(i).add(r);
                }
                i++;
        }
        this.Seguro=Seguro;
        this.Puntaje = Puntaje;
        this.Monedas = Monedas;
        this.segundos = segundos;
        this.minutos = minutos;
        this.Nombre = Nombre;
        this.mascota = mascota.rectaObj;
    }
            
}
