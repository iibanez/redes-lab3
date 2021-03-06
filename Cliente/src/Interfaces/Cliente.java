package Interfaces;

import implementaciones.ControladorPrincipal;
import interfaz.InterfazCliente;
import interfaz.InterfazServidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AllPermission;
import java.util.Random;
import javax.swing.JOptionPane;
import implementaciones.InterfazClienteImpl;
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
//Clase que extiende a JFrame, que se ocupa para interactuar con el usuario
public class Cliente extends javax.swing.JFrame {
    //http://mimosa.pntic.mec.es/jgomez53/matema/conocer/10000_primos.htm
    private BigInteger p;
    private BigInteger g;
    private Registry registry;
    private InterfazServidor servidor;
    private InterfazCliente cliente; 
    private String nombreUsuario;
    private String passwordUsuario;
    private static Cliente propiaVista = null;
    private ControladorPrincipal ppal;
    private BigInteger nonce,a,b,key;
    
    //inicializa los componentes de la vista
    public Cliente() {
        initComponents();
        this.setVisible(true);
        this.boton_receptor.setEnabled(false);
        this.boton_enviar_mensaje.setEnabled(false);
        this.boton_ver_clave.setEnabled(false);
        this.mensaje_enviar.setEnabled(false);
        this.mensaje_dec.setEnabled(false);
        this.mensaje_cod.setEnabled(false);
        this.boton_crear_clave.setEnabled(false);
        
        try {
            this.cliente = new InterfazClienteImpl();
            ppal = new ControladorPrincipal(this);
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }    
    
    //Patron singleton
    public static Cliente getInstanciaVista(){
        if (propiaVista == null) {
            propiaVista = new Cliente();
        }
        return propiaVista;
    }
    
    //Desde el cliente se busca al servidor en el registro de RMI que deja "Implementacion"
    //como el nombre para ser encontrado
    public boolean iniciarRegistry() throws RemoteException{
        try{
            AllPermission a = new AllPermission();
            System.setProperty("java.security.policy", "rmi.policy");
            startRegistry("127.0.0.1", 1099);
            this.servidor = (InterfazServidor)this.registry.lookup("Implementacion");
            return true;
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        return false;
    }
    
    //Se pide en el host "host" y en el puerto "puerto"
    //el registro de RMI
    private void startRegistry(String host, int Puerto) throws RemoteException{
        try{
            this.registry = LocateRegistry.getRegistry(host, Puerto);
            this.registry.list();
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }
    
    //es la respuesta correspondiente a cuando el usuario quiere realizar
    //el paso de valores entre usuarios para lograr obtener una clave secreta
    public void trasmitir_a(String mensaje){
        
        //se recibe el mensaje y es dividido
        StringTokenizer token = new StringTokenizer(mensaje, ";"); 
        String origen = (String) token.nextElement();
        String destino = (String) token.nextElement();
        b = new BigInteger((String)token.nextElement());
        
        if(this.a != null){
            Icon icon = new ImageIcon(getClass().getResource("/Recursos/correcto.png"));
            JOptionPane.showMessageDialog(this, "El usuario "+ origen+ " ha respondido su solicitud enviando su valor \nde B, por lo que ambos ya comparten la clave secreta.", "Conexión", JOptionPane.INFORMATION_MESSAGE, icon);
        }else{
            Icon icon = new ImageIcon(getClass().getResource("/Recursos/comunicar.png"));
            JOptionPane.showMessageDialog(this, "El usuario "+ origen+ " quiere continuar estableciendo la conexión con \nusted "+destino+" y le ha enviado el valor de B.", "Conexión", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        if(nonce!=null && b!=null){
            this.boton_ver_clave.setEnabled(true);
        }
        
        //se muestra el valor que es recibido
        this.a_entrada.setText(String.valueOf(b));

    }
    
    //corresponde a la función encargada de realizar el envio del mensaje desde
    //un origen a un destino
    public void mostrar_mensaje(String mensaje){
        
        //se recibe el mensaje y es dividido
        String mensaje_d = ppal.ejecutarAlgoritmoDescifrado(String.valueOf(key), mensaje);
        StringTokenizer token = new StringTokenizer(mensaje_d, ";"); 
        String origen = (String) token.nextElement();
        String destino = (String) token.nextElement();
        String msg = (String)token.nextElement();
        this.mensaje_dec.setText(msg);
        this.mensaje_cod.setText(mensaje);
        
        Icon icon = new ImageIcon(getClass().getResource("/Recursos/mensaje.png"));
        JOptionPane.showMessageDialog(this, "Usted "+ destino+ " ha recibido un mensaje de "+ origen+ ", \nel que ha sido decodificado.", "Comunicación", JOptionPane.INFORMATION_MESSAGE, icon);
        
    }
    
    //se inicia la comunicación trasmitiendo el valor de p
    public void mostrar_p(String mensaje){
        
        //se recibe el mensaje y es dividido 
        StringTokenizer token = new StringTokenizer(mensaje, ";"); 
        String origen = (String) token.nextElement();
        String destino = (String) token.nextElement();
        
        //mostrar el valor de p o g dependiendo de lo que se este estableciendo
        p = new BigInteger((String)token.nextElement());
        this.valor_p.setText(String.valueOf(p));
        //mostrar mensaje
        Icon icon = new ImageIcon(getClass().getResource("/Recursos/letra_p.jpg"));
        JOptionPane.showMessageDialog(this, "Usted "+ destino+ " ha recibido un mensaje de "+ origen+ " solicitando \nconexión y estableciendo el valor de p.", "Comunicación", JOptionPane.INFORMATION_MESSAGE, icon);
        this.destino.setText(origen);
    }
    
    //se inicia la comunicación trasmitiendo el valor de g
    public void mostrar_g(String mensaje){
        
        //se recibe el mensaje y es dividid
        StringTokenizer token = new StringTokenizer(mensaje, ";"); 
        String origen = (String) token.nextElement();
        String destino = (String) token.nextElement();
        
        //mostrar el valor de p o g dependiendo de lo que se este estableciendo
        g = new BigInteger((String)token.nextElement());
        this.valor_g.setText(String.valueOf(g));
        
        //mostrar mensaje
        Icon icon = new ImageIcon(getClass().getResource("/Recursos/letra_g.png"));
        JOptionPane.showMessageDialog(this, "Usted "+ destino+ " ha recibido un mensaje de "+ origen+ " respondiendo \nsolicitud y estableciendo el valor de g.", "Comunicación", JOptionPane.INFORMATION_MESSAGE, icon);
        this.boton_crear_clave.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        boton_registrar = new javax.swing.JButton();
        iniciar_c = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        a_salida = new javax.swing.JTextField();
        boton_receptor = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ks = new javax.swing.JTextField();
        boton_enviar_mensaje = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        destino = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensaje_enviar = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensaje_cod = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        mensaje_dec = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        a_entrada = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        random = new javax.swing.JTextField();
        boton_ver_clave = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        valor_p = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        valor_g = new javax.swing.JTextField();
        boton_crear_clave = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setToolTipText("Needham Stock");

        jLabel1.setText("Conexión con Servidor");

        jLabel2.setText("Ingrese nombre de usuario:");

        jLabel3.setText("Ingrese contraseña de usuario:");

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        boton_registrar.setText("Registrar");
        boton_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_registrarActionPerformed(evt);
            }
        });

        iniciar_c.setText("Comenzar Conexión");

        jLabel5.setText("a  = g^A mod p  (se envia):");

        jLabel6.setText("b (recibe) :");

        a_salida.setEditable(false);
        a_salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_salidaActionPerformed(evt);
            }
        });

        boton_receptor.setText("Enviar petición");
        boton_receptor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_receptorMouseClicked(evt);
            }
        });
        boton_receptor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_receptorActionPerformed(evt);
            }
        });

        jLabel7.setText("K = b^A mod p (Secret Key):");

        ks.setEditable(false);
        ks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ksActionPerformed(evt);
            }
        });

        boton_enviar_mensaje.setText("Enviar a destino");
        boton_enviar_mensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_enviar_mensajeMouseClicked(evt);
            }
        });
        boton_enviar_mensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_enviar_mensajeActionPerformed(evt);
            }
        });

        jLabel16.setText("Destino: ");

        destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinoActionPerformed(evt);
            }
        });

        jLabel9.setText("Mensaje: ");

        mensaje_enviar.setColumns(20);
        mensaje_enviar.setRows(5);
        jScrollPane1.setViewportView(mensaje_enviar);

        jLabel10.setText("Mensaje Recibido Codificado:");

        mensaje_cod.setColumns(20);
        mensaje_cod.setRows(5);
        jScrollPane2.setViewportView(mensaje_cod);

        jLabel11.setText("Mensaje Recibido Decodificado:");

        mensaje_dec.setColumns(20);
        mensaje_dec.setRows(5);
        jScrollPane3.setViewportView(mensaje_dec);

        jLabel12.setText("Transmisión de datos");

        a_entrada.setEditable(false);
        a_entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_entradaActionPerformed(evt);
            }
        });

        jLabel8.setText("A (Random) :");

        random.setEditable(false);
        random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomActionPerformed(evt);
            }
        });

        boton_ver_clave.setText("Ver clave secreta");
        boton_ver_clave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_ver_claveMouseClicked(evt);
            }
        });
        boton_ver_clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ver_claveActionPerformed(evt);
            }
        });

        jLabel13.setText("Definir p:");

        valor_p.setEditable(false);
        valor_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valor_pActionPerformed(evt);
            }
        });

        jLabel14.setText("Definir g:");

        valor_g.setEditable(false);
        valor_g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valor_gActionPerformed(evt);
            }
        });

        boton_crear_clave.setText("Establecer clave");
        boton_crear_clave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_crear_claveMouseClicked(evt);
            }
        });
        boton_crear_clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_crear_claveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_salida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ks, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addComponent(boton_ver_clave, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(valor_g, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                .addComponent(valor_p, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(random, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(iniciar_c, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(destino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boton_registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boton_receptor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boton_crear_clave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boton_enviar_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_registrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(iniciar_c)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_receptor)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(valor_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(valor_g, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(random, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_crear_clave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(a_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(a_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_ver_clave))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_enviar_mensaje))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Establecer Conexión y Comunicación", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_ver_claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ver_claveActionPerformed
        //se calcula el valor de la clave secreta
        key = b.modPow(nonce, p);

        //se publica el valor de la clave secreta
        this.ks.setText(String.valueOf(key));

        //activar para escribir un mensaje y poder enviarlo
        this.boton_ver_clave.setEnabled(false);
        this.mensaje_enviar.setEnabled(true);
        this.boton_enviar_mensaje.setEnabled(true);
    }//GEN-LAST:event_boton_ver_claveActionPerformed

    private void boton_ver_claveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_ver_claveMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_ver_claveMouseClicked

    private void randomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_randomActionPerformed

    private void a_entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_entradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_entradaActionPerformed

    private void destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destinoActionPerformed

    private void boton_enviar_mensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_enviar_mensajeActionPerformed

        String userDestino = destino.getText();
        String mensaje_1 = this.nombreUsuario+";"+userDestino+";"+mensaje_enviar.getText();
        //es codificado el mensaje
        String codificado = ppal.ejecutarAlgoritmoCifrado(String.valueOf(key), mensaje_1);

        try {
            //es realizado el envio del mensaje
            this.servidor.enviar_mensaje(userDestino, codificado);
        }
        catch (RemoteException ex){
            System.out.println(ex);
        }

    }//GEN-LAST:event_boton_enviar_mensajeActionPerformed

    private void boton_enviar_mensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_enviar_mensajeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_enviar_mensajeMouseClicked

    private void ksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ksActionPerformed

    private void boton_receptorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_receptorActionPerformed

        this.boton_receptor.setEnabled(false);
        this.destino.setEnabled(false);
        //obtener el destino
        String userDestino = destino.getText();
        
        //se calcula el valor del nonce
        int num = new Random().nextInt(9900) + 1;
        String mensaje = "";
        int opcion = 0;
        try {
            if(p==null){
                p = new BigInteger(ppal.numeroPrimoAleatorio(num));
                this.valor_p.setText(String.valueOf(p));
                //se crea el mensaje para enviar de A a B
                mensaje = this.nombreUsuario+";"+userDestino+";"+String.valueOf(p);
                opcion  = 0;
            }else{
                g = new BigInteger(ppal.numeroPrimoAleatorio(num));
                this.valor_g.setText(String.valueOf(g));
                //se crea el mensaje para enviar de A a B
                mensaje = this.nombreUsuario+";"+userDestino+";"+String.valueOf(g);
                opcion = 1;
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(opcion == 0){
                this.servidor.enviar_p(userDestino, mensaje);
            }else{
                this.servidor.enviar_g(userDestino, mensaje);
                this.boton_crear_clave.setEnabled(true);
            }
        }
        catch (RemoteException ex){
            this.boton_receptor.setEnabled(true);
            this.destino.setEnabled(true);
            System.out.println(ex);
        }
    }//GEN-LAST:event_boton_receptorActionPerformed

    private void boton_receptorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_receptorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_receptorMouseClicked

    private void a_salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_salidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_salidaActionPerformed

    private void boton_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_registrarActionPerformed
        // al presionar el boton registrar comienza la interaccion con el SA y se registra el cliente
        // cuando la contraseña es correcta, caso contrario se muestra mensaje de error acorde
        this.nombreUsuario=this.usuario.getText();
        this.passwordUsuario = this.password.getText();
        if ((this.nombreUsuario.length() != 0) && (this.passwordUsuario.length() != 0)) {
            try{
                if (iniciarRegistry()){
                    if(this.servidor.registrarCliente(this.cliente,nombreUsuario,passwordUsuario)){
                        Icon icon = new ImageIcon(getClass().getResource("/Recursos/correcto.png"));
                        JOptionPane.showMessageDialog(this, "Conectado a Servidor" , "Identificación", JOptionPane.INFORMATION_MESSAGE, icon);

                        this.usuario.setEnabled(false);
                        this.password.setEnabled(false);
                        this.boton_registrar.setEnabled(false);
                        this.boton_receptor.setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos", "Mensaje", 0);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "No se pudo Conectar", "Mensaje", 0);
                }
            }
            catch (RemoteException ex){
                System.out.println("Error al registrar el cliente: "+ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Debe indicar nombre y clave publica", "Mensaje", 0);
        }
    }//GEN-LAST:event_boton_registrarActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed

    private void valor_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valor_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valor_pActionPerformed

    private void valor_gActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valor_gActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valor_gActionPerformed

    private void boton_crear_claveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_crear_claveMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_crear_claveMouseClicked

    private void boton_crear_claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_crear_claveActionPerformed
        this.boton_crear_clave.setEnabled(false);
        //obtener el destino
        String userDestino = destino.getText();

        //se calcula el valor del nonce
        nonce = new BigInteger(String.valueOf(new Random().nextInt(1001) + 1));

        if(nonce!=null && b!=null){
            this.boton_ver_clave.setEnabled(true);
        }

        //se muestra el nonce
        this.random.setText(String.valueOf(nonce));

        //se calcula el valor de a que es enviado
        a = g.modPow(nonce, p);
        //se muestra el valor
        this.a_salida.setText(String.valueOf(a));

        //se crea el mensaje para enviar de A a B
        String mensaje = this.nombreUsuario+";"+userDestino+";"+String.valueOf(a);
        try {
            this.servidor.enviar_a(userDestino, mensaje);
        }
        catch (RemoteException ex){
            this.boton_receptor.setEnabled(true);
            this.destino.setEnabled(true);
            System.out.println(ex);
        }
    }//GEN-LAST:event_boton_crear_claveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a_entrada;
    private javax.swing.JTextField a_salida;
    private javax.swing.JButton boton_crear_clave;
    private javax.swing.JButton boton_enviar_mensaje;
    private javax.swing.JButton boton_receptor;
    private javax.swing.JButton boton_registrar;
    private javax.swing.JButton boton_ver_clave;
    private javax.swing.JTextField destino;
    private javax.swing.JLabel iniciar_c;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField ks;
    private javax.swing.JTextArea mensaje_cod;
    private javax.swing.JTextArea mensaje_dec;
    private javax.swing.JTextArea mensaje_enviar;
    private javax.swing.JTextField password;
    private javax.swing.JTextField random;
    private javax.swing.JTextField usuario;
    private javax.swing.JTextField valor_g;
    private javax.swing.JTextField valor_p;
    // End of variables declaration//GEN-END:variables
}
