/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossyroad;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author maria.mosquera
 */
public class ObjetoGrafico extends JLabel {
    
    Rectangle rectaObj= new Rectangle();
    int TipoSuelo;
    Image imagen;
    ArrayList<Obstaculos> obstaculos;

    public ObjetoGrafico() { }
    
    ObjetoGrafico(Rectangle obj,ImageIcon imagen,int tipo)
    {
        rectaObj=obj;
        TipoSuelo=tipo;
        this.imagen=imagen.getImage();
    }
    public ObjetoGrafico(ImageIcon imagen,int x,int y,int ancho,int alto,int TipoSuelo) {
     this.imagen = imagen.getImage();
     rectaObj.x=x;
     rectaObj.y=y;
     rectaObj.width=ancho;
     rectaObj.height=alto;
     this.TipoSuelo=TipoSuelo;
     
     obstaculos=new ArrayList<>();
     CargarObstaculos();
    }
    public void CargarObstaculos()
    {
        
        Random rmd=new Random();
        int dato=rmd.nextInt(2),dato2,dato3,dato4,antecesor=0,posicion=rmd.nextInt(150);
        
        if(TipoSuelo==0){
            for(int c=0,r=100;c<3;c++,r+=100){
               if(dato==1)
                obstaculos.add(new Obstaculos(posicion+antecesor+r+72,rectaObj.y,getClass().getResource("/Autos/maquina.png"),10,1));
               else{
                obstaculos.add(new Obstaculos(posicion+antecesor+r+72,rectaObj.y,getClass().getResource("/Autos/maquina_1.png"),-10,2 ));
               antecesor+=(r+72);
                   
            }
        }
      }
         if(TipoSuelo==1){
             dato2=rmd.nextInt(3);
            for(int c=0,r=100;c<3;c++,r+=100){
                if(dato2==0)
                    obstaculos.add(new Obstaculos(rmd.nextInt(r)+antecesor,rectaObj.y,getClass().getResource("/Imagenes/tortuga2.png"),0 ,1));
                else
                {
                        if(dato2==1)
                            obstaculos.add(new Obstaculos(rmd.nextInt(r)+antecesor,rectaObj.y+10,getClass().getResource("/Imagenes/tronco1.png"),10,2 ));
                        else
                            obstaculos.add(new Obstaculos(rmd.nextInt(r)+antecesor,rectaObj.y+10,getClass().getResource("/Imagenes/tronco1.png"),-10,3 ));    
                }
                antecesor+=r+72;
                        
                
            }
         }
            if(TipoSuelo==2 && rectaObj.y!=450){ 
               
             for(int c=0,r=100;c<3;c++,r+=100){
                 dato3= rmd.nextInt(2);
                 if(dato3==1)
                  obstaculos.add(new Obstaculos(rmd.nextInt(r)+antecesor,rectaObj.y,getClass().getResource("/Imagenes/rocs.png"),0,1));
                 else
                  obstaculos.add(new Obstaculos(rmd.nextInt(r)+antecesor,rectaObj.y,getClass().getResource("/Imagenes/tree.png"),0,2));
                 
                 antecesor+=r+72;
               }
             boolean ver=true;
                while(ver==true){
                    int x=rmd.nextInt(600);
                    for(int i=0;i<3;i++){
                        if(!obstaculos.get(i).Colision(x,rectaObj.y+10)){
                    obstaculos.add(new Obstaculos(x,rectaObj.y+10,getClass().getResource("/Imagenes/coin1.png"),0,3));
                    ver=false;
                        }
                    }
                }
            }
         
            if(TipoSuelo==3){
                dato4=rmd.nextInt(2);
                    if(dato4==1)
                        obstaculos.add(new Obstaculos(-100,rectaObj.y,getClass().getResource("/Autos/tren.png"),10,1 ));
                       
                    else
                 obstaculos.add(new Obstaculos(600,rectaObj.y,getClass().getResource("/Autos/tren_1.png"),-10,2 ));       
             }
            
    }
            public boolean colision(int y,int x)
            {
                boolean ver=false;
                for (Obstaculos s: obstaculos) {
                    if(s.Colision(y, x))
                      ver=true;
                }
                    return ver;
            }
    
    public void PintarImagen(Graphics g) {

        g.drawImage(imagen, (int) rectaObj.getX(), (int) rectaObj.getY(), null);
      
    }         
    public void SumarEnY()
    {
         rectaObj.y+=50;
          for (Obstaculos s : obstaculos) 
          s.PosY+=50;    
    }
   
  
    public void ActualizarObjetos()
    {
        for (Obstaculos s : this.obstaculos) {
            if(s.Posx>800)
                s.Posx=-100;
            if(s.Posx<-100)
                s.Posx=800;
            
            s.MoverObstaculos();
        }
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
 
}
