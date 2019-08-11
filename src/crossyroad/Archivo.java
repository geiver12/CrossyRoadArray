
package crossyroad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Archivo {
    Puntaje ObjetoPuntos;
    Partida GuardarJuego;
    
    public Archivo(Partida partida)
    {
        GuardarJuego = partida;
    }
    public Archivo(Puntaje p)
    {
        ObjetoPuntos=p;
    }

    public Archivo() {
        ObjetoPuntos=null;
    }
    
       public void GuardarRegistro() throws FileNotFoundException, IOException
    {
        FileOutputStream fileOut=new FileOutputStream("Puntaje.obj");
        ObjectOutputStream salida=new ObjectOutputStream(fileOut);
        salida.writeObject(ObjetoPuntos);
        salida.close();
    }
       
    public void LeerRegistro() throws FileNotFoundException, IOException, ClassNotFoundException
    {
      FileInputStream fileIn=new FileInputStream("Puntaje.obj");
      ObjetoPuntos=null;
      while(fileIn.available()>0){
      ObjectInputStream entrada=new ObjectInputStream(fileIn);
      ObjetoPuntos=(Puntaje)entrada.readObject();
          
        }
      fileIn.close();
    }
    
    public void GuardarPartida() throws FileNotFoundException, IOException
    {
        FileOutputStream fileOut=new FileOutputStream("Archivo.obj",true);
        ObjectOutputStream salida=new ObjectOutputStream(fileOut);
        salida.writeObject(GuardarJuego);
        salida.close();
    }
    public void reanudarPartida(String Nombre,int Seguro) throws FileNotFoundException, IOException, ClassNotFoundException
    {
      FileInputStream fileIn=new FileInputStream("Archivo.obj");
      Partida GuardarJuego;
      this.GuardarJuego=null;
        
      while(fileIn.available()>0){
      ObjectInputStream entrada=new ObjectInputStream(fileIn);
      GuardarJuego=(Partida)entrada.readObject();
      if(Nombre.equals(GuardarJuego.Nombre) &&  Seguro==GuardarJuego.Seguro){
          this.GuardarJuego=GuardarJuego;
          System.out.println(Nombre);
      }
          System.out.println(GuardarJuego.Nombre+"---"+GuardarJuego.Seguro);
          System.out.println(Nombre+"...."+Seguro);
      
        }
      fileIn.close();
    }
            
}
