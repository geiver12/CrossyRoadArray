/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossyroad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author maria.mosquera
 */
public class Menu extends JFrame implements KeyListener {

    int band = 1;
    JPanel Contenedor;
    JButton jugar, salir, creditos, instrucciones, cargar, puntajes, boton;
    IntefazGrafica obj;
    boolean det = false;
    ImageIcon image, imageF;
    JLabel fondo,fondoF;

    public Menu() {

        setTitle("Crossy  Road");
        setSize(800, 680);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        URL url = this.getClass().getResource("/Imagenes/fondo.png");
        image = new ImageIcon(url);
        fondo = new JLabel(image);
        fondo.setSize(800, 680);

        URL urlF = this.getClass().getResource("/Imagenes/ventana.png");
        imageF = new ImageIcon(urlF);
        fondoF = new JLabel(imageF);
        fondoF.setSize(700, 680);

        Contenedor = new JPanel();
        Contenedor.setSize(800, 650);
        Contenedor.setLocation(0, 0);
        Contenedor.setLayout(null);

        CargarComponentes();
        setVisible(true);
        Contenedor.add(fondo);
        
        add(Contenedor);
        
        
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

    }

    public void BotonGuardar() {

        boton = new JButton("Guardar");
        boton.setBounds(700, 200, 100, 30);
        boton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                obj.timer.stop();
                obj.timer2.stop();
                obj.timer3.stop();
                obj.Jugar = false;
                String dato = JOptionPane.showInputDialog("Ingrese su clave numerica de seguridad");
                if (dato != null) {
                    int Seguro = Integer.parseInt(dato);
                    Partida guarda = new Partida(obj.suelo, obj.Puntaje, obj.Moneda, obj.segundos, obj.minutos, obj.Nombre, obj.mascota, obj.Tiempo, Seguro);
                    try {
                        Archivo crearArchivo = new Archivo();
                        try {
                            crearArchivo.reanudarPartida(obj.Nombre, Seguro);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (crearArchivo.GuardarJuego == null) {
                            crearArchivo.GuardarJuego = guarda;
                            crearArchivo.GuardarPartida();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar no puede sobreescribir una partida");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(IntefazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                obj.ReanudarJuego();
                RecuperarEvento();
                repaint();
            }

        });

    }

    public void RecuperarEvento() {
        setFocusableWindowState(true);
        requestFocus();
    }
    
    private void CargarComponentes() {
        ImageIcon inicio = new ImageIcon(getClass().getResource("/Botones/iniciar.png"));
        ImageIcon ayuda = new ImageIcon(getClass().getResource("/Botones/ayuda.png"));
        ImageIcon credito = new ImageIcon(getClass().getResource("/Botones/creditos.png"));
        ImageIcon carga = new ImageIcon(getClass().getResource("/Botones/cargar.png"));
        ImageIcon top10 = new ImageIcon(getClass().getResource("/Botones/top10.png"));
        ImageIcon salida = new ImageIcon(getClass().getResource("/Botones/salir.png"));

        if (band == 1) {
            band = 0;
            jugar = new JButton(inicio);
            instrucciones = new JButton(ayuda);
            creditos = new JButton(credito);
            cargar = new JButton(carga);
            puntajes = new JButton(top10);
            salir = new JButton(salida);

            jugar.setBounds(50, 250, 150, 60);
            instrucciones.setBounds(50, 315, 150, 60);
            creditos.setBounds(50, 380, 150, 60);
            cargar.setBounds(50, 445, 150, 60);
            puntajes.setBounds(50, 510, 150, 60);
            salir.setBounds(50, 575, 150, 60);
            jugar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String Nombre = JOptionPane.showInputDialog(null, "Ingrese Su Nick antes de comenzar");
                    if (Nombre != null) {
                        if (Nombre.length() >= 2) {
                            remove(Contenedor);
                            obj = new IntefazGrafica();
                            obj.Nombre = Nombre;
                            BotonGuardar();
                            add(obj);
                            ImageIcon tienda_i = new ImageIcon(getClass().getResource("/Botones/tienda.png"));
                            JButton tiendaB = new JButton("Tienda");
                            tiendaB.setBounds(710, 150, 80, 20);
                            JLabel tienda = new JLabel(tienda_i);
                            tienda.setBounds(710, 100, 80, 84);
                            add(tiendaB);
                            add(tienda);
                            add(boton);
                            ImageIcon portada_i = new ImageIcon(getClass().getResource("/Imagenes/portada_1.png"));
                            JLabel portada = new JLabel(portada_i);
                            portada.setBounds(700, 0, 100, 680);
                            add(portada);

                            tiendaB.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    tiendaB.setVisible(false);
                                    tienda.setVisible(false);
                                    boton.setVisible(false);
                                    obj.setVisible(false);
                                    JButton volver = new JButton("Anterior");
                                    volver.setBounds(20, 580, 80, 30);
                                    JButton comprarFred= new JButton("Comprar Fred");
                                    comprarFred.setBounds(330, 350, 150, 30);
                                    JButton comprarVelma= new JButton("Comprar Velma");
                                    comprarVelma.setBounds(450, 150, 150, 30);
                                    ImageIcon portada_i = new ImageIcon(getClass().getResource("/Imagenes/portada_1.png"));
                                    JLabel portada = new JLabel(portada_i);
                                    portada.setBounds(700, 0, 100, 680);
                                    add(portada);
                                    ImageIcon tiendaM = new ImageIcon(getClass().getResource("/Imagenes/Tienda.png"));
                                    JLabel tiendam = new JLabel(tiendaM);
                                    tiendam.setBounds(0, 0, 700, 489);
                                    JPanel tiendaMascota = new JPanel();
                                    tiendaMascota.setSize(700, 489);
                                    tiendaMascota.setLocation(0, 70);
                                    tiendaMascota.setLayout(null);
                                    
                                    obj.pausa();
                                    add(volver);
                                    tiendaMascota.add(comprarFred);
                                    tiendaMascota.add(comprarVelma);
                                    tiendaMascota.add(tiendam);
                                    
                                    add(tiendaMascota);
                                    add(fondoF);
                                    tiendaMascota.setVisible(true);
                                   // obj.setVisible(true);
                                    volver.addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent ae) {
                                            tiendaMascota.setVisible(false);

                                            obj.setVisible(true);
                                            tiendaB.setVisible(true);
                                    tienda.setVisible(true);
                                    boton.setVisible(true);
                                            obj.reanudar();
                                            RecuperarEvento();
                                        }

                                    });
                                    
                                    comprarFred.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                            if(obj.Moneda>=50){
                                            obj.mascota_i=obj.fred_i;
                                            obj.Moneda-=50;
                                            }
                                            RecuperarEvento();
                                            
                                        }
                                    });
                                    
                                    comprarVelma.addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(obj.Moneda>=50){
                                            obj.mascota_i=obj.velma_i;
                                            obj.Moneda-=50;
                                            }
                                            RecuperarEvento();
                                        }
                                    });

                                }
                            });

                        }
                    }
                    repaint();
                }
            });
            puntajes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Archivo objeto = new Archivo();

                    try {
                        objeto.LeerRegistro();
                        ImageIcon top= new ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"));
                        JLabel top_= new JLabel(top);
                        top_.setBounds(0, 0, 800, 680);
                        JPanel lista = new JPanel();
                        lista.setLayout(null);
                        lista.setBounds(0, 0, 800, 680);
                        
                        JLabel v[] = new JLabel[10];
                      
                        for (int i = 0, y = 200; i < 10; i++, y += 45) {
                            v[i] = new JLabel(objeto.ObjetoPuntos.Nombres.get(i) +"                                          "+objeto.ObjetoPuntos.Puntos.get(i));
                            v[i].setBounds(520, y, 400, 40);
                            lista.add(v[i]);
                        }
                        
                        Contenedor.setVisible(false);
                        JButton boton = new JButton("VOLVER");
                        boton.setBounds(30, 500, 100, 30);
                        boton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                lista.setVisible(false);
                                Contenedor.setVisible(true);
                            }
                        });
                        lista.add(boton);
                        lista.add(top_);
                        add(lista);
                        
                        repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i = 0; i < objeto.ObjetoPuntos.Nombres.size() && i < 10; i++) {
                        System.out.println(objeto.ObjetoPuntos.Nombres.get(i) + "--" + objeto.ObjetoPuntos.Puntos.get(i));
                    }

                }
            });
            instrucciones.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Contenedor.setVisible(false);
                    JPanel panel = new JPanel();
                    ImageIcon menu = new ImageIcon(getClass().getResource("/Botones/menu.png"));
                    JButton volver = new JButton(menu);
                    volver.setBounds(100, 610, 100, 30);

                    URL url = this.getClass().getResource("/Imagenes/instrucciones.png");
                    ImageIcon image = new ImageIcon(url);
                    JLabel label = new JLabel(image);
                    label.setSize(800, 680);
                    label.setLocation(0, 0);
                    label.setLayout(null);
                    panel.setSize(800, 680);
                    panel.setLocation(0, -28);
                    panel.setLayout(null);
                    panel.add(volver);
                    panel.add(label);

                    panel.setVisible(true);
                    add(panel);

                    volver.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            band = 1;
                            Contenedor.setVisible(true);
                            panel.setVisible(false);
                        }
                    });
                    repaint();
                }
            });

            cargar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Archivo objeto = new Archivo();

                    String Nombre = (JOptionPane.showInputDialog("Ingrese Su Nick antes de comenzar"));
                    int Seguro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese Su clave de seguridad"));
                    if (Nombre != null) {
                        try {
                            try {
                                objeto.reanudarPartida(Nombre, Seguro);
                                if (objeto.GuardarJuego != null) {
                                    remove(Contenedor);
                                    obj = new IntefazGrafica(objeto.GuardarJuego);
                                    add(obj);
                                }
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            creditos.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Contenedor.setVisible(false);
                    JPanel panel = new JPanel();
                    ImageIcon menu = new ImageIcon(getClass().getResource("/Botones/menu.png"));
                    JButton volver = new JButton(menu);
                    volver.setBounds(340, 510, 100, 30);

                    URL url = this.getClass().getResource("/Imagenes/creditos.png");
                    ImageIcon image = new ImageIcon(url);
                    JLabel label = new JLabel(image);
                    label.setSize(800, 680);
                    label.setLocation(0, 0);
                    label.setLayout(null);
                    panel.setSize(800, 680);
                    panel.setLocation(0, -28);
                    panel.setLayout(null);
                    panel.add(volver);
                    panel.add(label);

                    panel.setVisible(true);
                    add(panel);

                    volver.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            band = 1;
                            Contenedor.setVisible(true);
                            panel.setVisible(false);
                        }
                    });
                    repaint();
                }
            });

            salir.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    int resp = JOptionPane.showConfirmDialog(null,
                            "¿Desea Salir?", "Crossy Road",
                            JOptionPane.YES_NO_OPTION);

                    if (resp == JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
            });

            Contenedor.add(jugar);
            Contenedor.add(instrucciones);
            Contenedor.add(creditos);
            Contenedor.add(cargar);
            Contenedor.add(puntajes);
            Contenedor.add(salir);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean r = true;
        if (obj.Jugar) {
            if (e.getKeyCode() == e.VK_UP) {
                obj.Puntaje++;
                obj.mascota.setImagen(obj.mascota_i[3].getImage());
                for (Suelo s : obj.suelo) {
                    if (obj.ColisionCesped(s, -50, 0)) {
                        r = false;
                    }
                }
                if (r) {
                    obj.timer2.start();
                    obj.timer3.start();
                    obj.mascota.moverMascota(1);
                    obj.Tiempo = 0;
                }
                obj.repaint();
            }
            if (e.getKeyCode() == e.VK_DOWN && obj.mascota.rectaObj.y < 600) {
                obj.mascota.setImagen(obj.mascota_i[0].getImage());
                for (Suelo s : obj.suelo) {
                    if (obj.ColisionCesped(s, 50, 0)) {
                        r = false;
                    }
                }
                if (r) {
                    obj.mascota.moverMascota(2);
                }
                obj.Tiempo = 0;
                obj.repaint();
            }
            if (e.getKeyCode() == e.VK_LEFT && obj.mascota.rectaObj.x > 20) {
                obj.mascota.setImagen(obj.mascota_i[1].getImage());
                for (Suelo s : obj.suelo) {
                    if (obj.ColisionCesped(s, 0, -10)) {
                        r = false;
                    }
                }
                if (r) {
                    obj.mascota.moverMascota(3);
                }
                obj.Tiempo = 0;
                obj.repaint();
            }
            if (e.getKeyCode() == e.VK_RIGHT && obj.mascota.rectaObj.x < 660) {
                obj.mascota.setImagen(obj.mascota_i[2].getImage());
                for (Suelo s : obj.suelo) {
                    if (obj.ColisionCesped(s, 0, 10)) {
                        r = false;
                    }
                }
                if (r) {
                    obj.mascota.moverMascota(4);
                }
                obj.Tiempo = 0;
                obj.repaint();
            }
        }

        if (Character.isWhitespace(e.getKeyCode())) {
            obj.Formulario();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
    
}
