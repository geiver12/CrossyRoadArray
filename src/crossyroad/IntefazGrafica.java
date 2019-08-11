package crossyroad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*
*
*/
/**
 * Es la clase que contiene los metodos principales para el funcionamiento del juego.
 * @author Administrador
 */
public class IntefazGrafica extends JPanel {

    ArrayList<Suelo> suelo;
    int ContadorSuelos;
    ImageIcon[] suelo_i;
    Timer timer, timer2, timer3;
    Mascota mascota;
    ImageIcon[] mascota_i,velma_i,fred_i;
    String Nombre;
    int aleatorio, cont_Aleatorio, segundos, minutos, Puntaje, Moneda, Tiempo;
    Obstaculos obj;
    boolean Jugar;

    /**
     * Metodo que inicializa los componentes.
     */
    public void InitComponentes() {
        setSize(700, 650);
        setLocation(0, 0);
        setLayout(null);
        suelo = new ArrayList();
        suelo_i = new ImageIcon[4];
        mascota_i= new ImageIcon[4];
        fred_i= new ImageIcon[4];
        velma_i= new ImageIcon[4];
        
        Jugar = true;
    }
    
    /**
     * Constructor sin parametros que inicializa las variables y llama a los metodos.
     */
    public IntefazGrafica() {
        
        InitComponentes();
        cargarImagen();
        mascota = new Mascota(mascota_i[3], 300, 450, 27, 29, 5);
        add(mascota);

        CargarVariables();
        Mapa();
        ImageIcon portada_i = new ImageIcon(getClass().getResource("/Imagenes/portada.png"));
        JLabel portada = new JLabel(portada_i);
        portada.setBounds(0, 0, 700, 50);
        add(portada);
        Actualizar();
    }

    /**
    *Constructor parametrico que inicializa el juego y llama sus metodos. Recibe un objeto de tipo Partida
    */
    public IntefazGrafica(Partida p) {
        InitComponentes();
        cargarImagen();
        Puntaje = p.Puntaje;
        Nombre = p.Nombre;
        segundos = p.segundos;
        minutos = p.minutos;
        Moneda = p.Monedas;
        mascota = new Mascota();
        mascota.rectaObj = p.mascota;
        mascota.imagen = mascota_i[3].getImage();
        Tiempo = p.Tiempo;
        add(mascota);

        for (int i = 0; i < p.PosicionSuelo.size(); i++) {
            suelo.add(i, new Suelo(p.PosicionSuelo.get(i), suelo_i[p.Tiposuelo.get(i)], p.Tiposuelo.get(i)));
            suelo.get(i).obstaculos = new ArrayList<>();
            for (int j = 0; j < p.array.get(i).size(); j++) {
                suelo.get(i).obstaculos.add(new Obstaculos(p.array.get(i).get(j).x, p.array.get(i).get(j).y, getClass().getResource("/Autos/carWhite2.png"), p.array.get(i).get(j).width, p.array.get(i).get(j).height));
            }
            this.add(suelo.get(suelo.size() - 1));
        }
        for (Suelo s : suelo) {
            for (Obstaculos o : s.obstaculos) {
                if (s.TipoSuelo == 0) {
                    if (o.TipoObjeto == 1) {
                        o.ruta = getClass().getResource("/Autos/carWhite1.png");
                    } else {
                        o.ruta = getClass().getResource("/Autos/carWhite2.png");
                    }
                }
                if (s.TipoSuelo == 1) {
                    if (o.TipoObjeto == 0) {
                        o.ruta = getClass().getResource("/Imagenes/tortuga2.png");
                    } else {
                        o.ruta = getClass().getResource("/Imagenes/tronco1.png");
                    }

                }
                if (s.TipoSuelo == 2) {
                    if (o.TipoObjeto == 1) {
                        o.ruta = getClass().getResource("/Imagenes/piedra1.png");
                    } else if (o.TipoObjeto == 2) {
                        o.ruta = getClass().getResource("/Imagenes/Tree.png");
                    } else {
                        o.ruta = getClass().getResource("/Imagenes/coin1.png");
                    }
                }
                if (s.TipoSuelo == 3) {
                    if (o.TipoObjeto == 1) {
                        o.ruta = getClass().getResource("/Autos/tren.png");
                    } else {
                        o.ruta = getClass().getResource("/Autos/tren_1.png");
                    }
                }
            }
        }

        Actualizar();
    }

    public void CargarVariables() {
        ContadorSuelos = 12;
        ContadorSuelos = 0;
        segundos = 0;
        minutos = 0;
        aleatorio = 0;
        cont_Aleatorio = 0;
        Puntaje = 0;
        Tiempo = 0;
        Moneda = 0;

    }

    public void paint(Graphics g) {

        super.paint(g);

        
        Font f = new Font("Buxton Sketch", Font.ITALIC, 25);
        g.setFont(f);
        g.setColor(Color.blue);
        g.drawString("NOMBRE     "+Nombre, 10, 18);
        g.drawString("MONEDAS  "+Moneda, 410, 18);
        g.drawString("TIEMPO     "+minutos + ":" + segundos, 410, 38);
        g.drawString("PUNTAJE  " + Puntaje, 10, 38);
        for (Suelo s : suelo) {
            s.PintarImagen(g);
            for (int j = 0; j < s.obstaculos.size(); j++) {
                s.obstaculos.get(j).PintarImagen(g);
            }
        }
        mascota.PintarImagen(g);
        if (Tiempo >= 10) {
            obj.PintarImagen(g);
        }
    }

    public void cargarImagen() {
        int num;
        num = 1;
        for (int i = 0; i < 4; i++) {
            suelo_i[i] = new ImageIcon(getClass().getResource("/Imagenes/" + num + ".jpg"));
            num++;
        }

        for (int i = 0; i < 4; i++) {
            mascota_i[i] = new ImageIcon(getClass().getResource("/Mascotas/Scooby" + (i + 1) + ".png"));
        }
        for (int i = 0; i < 4; i++) {
            fred_i[i] = new ImageIcon(getClass().getResource("/Mascotas/fred" + (i + 1) + ".png"));
        }
        for (int i = 0; i < 4; i++) {
            velma_i[i] = new ImageIcon(getClass().getResource("/Mascotas/vilma" + (i + 1) + ".png"));
        }
    }

    public void Mapa() {
        Random rand = new Random();
        int aux = 0, x = 0, band = 0, cont = 0;

        //add(mascota);
        for (int i = 0, y = 50; i < 8; i++, y += 50) {

            aux = rand.nextInt(4);

            while (cont >= 2 && aux == band) {
                aux = rand.nextInt(4);
            }

            if (band == aux) {
                cont++;
            } else {
                band = aux;
                cont = 0;
            }

            GenerarMapa(aux, y, i);
        }
        GenerarMapa(2, 450, 8);
        GenerarMapa(2, 500, 9);
        GenerarMapa(2, 550, 10);
        GenerarMapa(2, 600, 11);
        for (int i = 0; i < 12; i++) {
            add(suelo.get(i));
        }
    }

    public void GenerarMapa(int TipoSuelo, int y, int id) {

        suelo.add(id, new Suelo(suelo_i[TipoSuelo], 0, y, 700, 50, TipoSuelo));//2::cesped//0::calle//1::agua//3::tren
        this.add(suelo.get(suelo.size() - 1));
    }

    public void Actualizar() {
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Tiempo < 10) {
                    boolean ver = true;
                    for (Suelo s : suelo) {
                        s.ActualizarObjetos();
                        if (ver) {
                            for (int o = 0; o < s.obstaculos.size() && ver; o++) {
                                if (ColisionAgua(s, 0, 0, false) && s.obstaculos.get(0).TipoObjeto == 2) {
                                    mascota.moverMascota(4);
                                    ver = false;
                                } else if (ColisionAgua(s, 0, 0, false) && s.obstaculos.get(0).TipoObjeto == 3) {
                                    mascota.moverMascota(3);
                                    ver = false;
                                }
                            }
                        }
                    }
                    if (Colision() || mascota.rectaObj.y > 650) {
                        DetenerTimer(true);
                    }
                } else {
                    if (!obj.Colision(mascota.rectaObj.y, mascota.rectaObj.x)) {
                        obj.MoverObstaculos();
                    } else {
                        DetenerTimer(true);

                    }

                }
                repaint();
            }
        });
        timer.start();

        timer2 = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GenerarCamino();
                repaint();
            }
        });
        timer3 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos < 60) {
                    segundos++;
                } else {
                    minutos++;
                    segundos = 0;
                }
                Tiempo++;
                if (Tiempo == 10) {
                    obj = new Obstaculos(650, mascota.rectaObj.y-15, getClass().getResource("/Imagenes/aguila.png"), -10, 0);
                    DetenerTimer(false);
                    timer.start();
                }
                repaint();
            }

        });
        timer.start();

    }

    public void GenerarCamino() {

        for (Suelo suelo : suelo) {
            suelo.SumarEnY();
        }
        if (aleatorio == 0) {
            ValidaCalles(2);
        }
        if (aleatorio == 1) {
            ValidaCalles(1);
        }
        if (aleatorio == 2) {
            ValidaCalles(2);
        }
        if (aleatorio == 3) {
            ValidaCalles(1);
        }
       
        

        GenerarMapa(aleatorio, 50, ContadorSuelos++);
        mascota.rectaObj.y += 50;
        repaint();
    }

    public void ValidaCalles(int numcalles) {
        Random r = new Random();
        int aleatorio = r.nextInt(4);

        while (this.aleatorio == aleatorio && cont_Aleatorio >= numcalles) {
            aleatorio = r.nextInt(4);
        }

        if (aleatorio == this.aleatorio) {
            cont_Aleatorio++;
        } else {
            cont_Aleatorio = 0;
            this.aleatorio = aleatorio;
        }

    }

    public void DetenerTimer(boolean ver) {
        timer.stop();
        timer2.stop();
        timer3.stop();
        Jugar = false;
        GuardarPuntaje();
        if (ver) {
            Formulario();
        }

    }

    public void Formulario() {
        int resp = JOptionPane.showConfirmDialog(null, "Reiniciar?");
        if (resp == 0) {

            Reiniciar();
        }

    }

    public boolean Colision() {
        boolean ver = false;
        for (Suelo s : suelo) {
            if (ColisionCarretera(s, 0, 0)) {
                return true;
            }
            if (ColisionAgua(s, 0, 0, true)) {
                return true;
            }
            if (ColisionCesped(s, 0, 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean ColisionCarretera(Suelo s, int y, int x) {
        if ((s.TipoSuelo == 0 || s.TipoSuelo == 3) && s.colision(mascota.rectaObj.y, mascota.rectaObj.x)) {
            return true;
        }
        return false;
    }

    public boolean ColisionAgua(Suelo s, int y, int x, boolean alternador) {
        if (alternador) {
            if (s.TipoSuelo == 1 && !s.colision(mascota.rectaObj.y, mascota.rectaObj.x) && (mascota.rectaObj.y <= s.rectaObj.y + 50 && mascota.rectaObj.y >= s.rectaObj.y)) {
                return true;
            }
        } else if (s.TipoSuelo == 1 && s.colision(mascota.rectaObj.y, mascota.rectaObj.x) && (mascota.rectaObj.y <= s.rectaObj.y + 50 && mascota.rectaObj.y >= s.rectaObj.y)) {
            return true;
        }

        return false;
    }

    public boolean ColisionCesped(Suelo s, int y, int x) {
        for (int i = 0; i < s.obstaculos.size(); i++) {
            if (s.TipoSuelo == 2 && s.colision(mascota.rectaObj.y + y, mascota.rectaObj.x + x) && s.obstaculos.get(i).TipoObjeto == 3) {
                Moneda++;
                s.obstaculos.remove(i);
            }
        }

        if (s.TipoSuelo == 2 && s.colision(mascota.rectaObj.y + y, mascota.rectaObj.x + x)) {
            return true;
        }

        return false;
    }

    public void Reiniciar() {

        this.CargarVariables();
        suelo.clear();
        Mapa();
        mascota.Reiniciar(300, 460, 27, 29);
        ReanudarJuego();
    }

    public void ReanudarJuego() {
        timer.start();
        timer2.start();
        timer3.start();
        Jugar = true;
    }

    public void GuardarPuntaje() {
        Archivo arc = new Archivo();

        try {
            try {
                arc.LeerRegistro();
            } catch (IOException ex) {
                Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (arc.ObjetoPuntos == null) {
            try {
                Puntaje p = new Puntaje();
                p.Nombres.add(Nombre);
                p.Puntos.add(Puntaje);
                arc.ObjetoPuntos = p;
                arc.GuardarRegistro();
                try {
                    arc.GuardarPartida();
                } catch (IOException ex) {
                    Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Puntaje p = arc.ObjetoPuntos;
            boolean ver = true;
            for (int i = 0; i < p.Nombres.size(); i++) {
                if (Puntaje >= p.Puntos.get(i)) {
                    p.Puntos.add(i, Puntaje);
                    p.Nombres.add(i, Nombre);
                    ver = false;
                    break;
                }
            }
            if (ver) {
                p.Puntos.add(Puntaje);
                p.Nombres.add(Nombre);
            }
            if (p.Puntos.size() - 1 >= 10) {
                p.Puntos.remove(10);
                p.Nombres.remove(10);
            }
            arc.ObjetoPuntos = p;
            try {
                arc.GuardarRegistro();
            } catch (IOException ex) {
                Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public void pausa(){
        timer.stop();
        timer2.stop();
        timer3.stop();
    }
    public void reanudar(){
        timer.start();
        timer2.start();
        timer3.start();
    }
}
