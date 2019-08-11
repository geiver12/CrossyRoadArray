
package crossyroad;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


public class Mascota extends ObjetoGrafico {
//Rectangle rectaMas = new Rectangle();

    public Mascota(ImageIcon imagen, int x, int y, int ancho, int alto,int TipoSuelo) {
        super(imagen, x, y, ancho, alto,TipoSuelo);
    }
    public void Reiniciar(int x,int y,int ancho,int alto)
    {
     rectaObj.x=x;
     rectaObj.y=y;
     rectaObj.width=ancho;
     rectaObj.height=alto;
    }

    public Mascota() {
    }
    
    public void moverMascota(int direccion){
        
        if(direccion==1 && rectaObj.y>=100){
             rectaObj.y-=50;
             setLocation(rectaObj.x,rectaObj.y); 
        }
        if(direccion==2){
            rectaObj.y+=50;
            setLocation(rectaObj.x,rectaObj.y );
        }
        if(direccion==3){
            rectaObj.x-=10;
            setLocation(rectaObj.x, rectaObj.y);
        }
        if(direccion==4){
            rectaObj.x+=10;
            setLocation(rectaObj.x, rectaObj.y);
        }
    }

  
}
