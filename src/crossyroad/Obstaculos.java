
package crossyroad;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.reflect.Constructor;
import java.net.URL;
import javax.swing.ImageIcon;

public class Obstaculos {
        int  Posx,PosY,Velocidad,TipoObjeto;
        URL ruta;
        Image imagen;
        
      public Obstaculos(int posx,int posy, URL ruta,int Velocidad,int TipoObjeto)
      {
          this.Posx=posx;
          this.PosY=posy;
          this.ruta=ruta;   
          this.Velocidad=Velocidad;
          this.TipoObjeto=TipoObjeto;
    
      }
      
      
      public  void PintarImagen(Graphics g)
      {
           Toolkit t = Toolkit.getDefaultToolkit ();
           imagen = t.getImage (ruta);
           g.drawImage(imagen,Posx,PosY,null);
      }
      public  void MoverObstaculos()
      {
          Posx+=Velocidad;
      }
      
   public boolean Colision(int y,int x)
    {
       ImageIcon img = new ImageIcon(ruta);	
        if(((y+12>=PosY && y+12<PosY+img.getIconHeight()) || (PosY>=y && PosY<=y+25))&& ((x>=Posx && x<=Posx+img.getIconWidth()-5) || (Posx>=x && Posx<=x+29)))
            return true;
        return false;
    }
}
