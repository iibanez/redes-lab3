
package Interfaces;

import implementaciones.ControladorPrincipal;
import interfaz.InterfazCliente;
import interfaz.InterfazServidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.AllPermission;
//import java.util.Random;
//import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import implementaciones.InterfazClienteImpl;
//import java.util.logging.Level;
//import java.util.logging.Logger;

//Clase que extiende a JFrame, que se ocupa para interactuar con el usuario
public class Cliente extends javax.swing.JFrame {
    private Registry registry;
    private InterfazServidor servidor;
    private InterfazCliente cliente; 
    private String nombreUsuario;
    private String destinatario;
    private String clavePublicaUsuario;
    private String mensajeparadestino;
    private static Cliente propiaVista = null;
    private String origen;
    private String claveDeSesion;
    private ControladorPrincipal ppal;
    
    public Cliente() {
        initComponents();
        this.setVisible(true);
        try {
            this.cliente = new InterfazClienteImpl();
            //ppal = new ControladorPrincipal(this);
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
    
    public void aviso3(String mensaje){
        
    }
    public void aviso4(String mensaje){
        
    }
    public void aviso5(String mensaje){
        
    }
    /*
    //Funcion que para el paso 3, el cliente B recibe el mensaje encriptado
    //enviado por A que contiene la clave de sesion de A, generada por SA
    public void aviso3(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", 0);
        System.out.println("Mensaje recibido encriptado: "+mensaje);
        String recibido = ppal.ejecutarAlgoritmoDescifrado(this.clavePublicaUsuario, mensaje);
        System.out.println("Mensaje recibido desencriptado: "+recibido);
        StringTokenizer token = new StringTokenizer(recibido, ";"); 
        String clavesesion = token.nextToken();
        String origen = token.nextToken();
        this.origen = origen;
        this.ck.setText(clavesesion);
        this.claveDeSesion = clavesesion;
        this.enviador.setText(origen);
        int nonce = new Random().nextInt(1001) + 1;
        this.nonceGenerado.setText(Integer.toString(nonce));
    }
    
    //Funcion que sucede cuando A recibe el nonce generado por B
    //y contesta con el nonce - 1
    //Se muestra por pantalla el nonce recibido y el nonce que se enviara
    public void aviso4(String mensaje){
        System.out.println("Mensaje recibido encriptado: "+mensaje);
        String recibido = ppal.ejecutarAlgoritmoDescifrado(this.ks.getText(), mensaje);
        System.out.println("Mensaje recibido desencriptado: "+recibido);
        JOptionPane.showMessageDialog(this, "Recibido nonce generado "+recibido.trim()+", se va a responder con "+
                Integer.toString(Integer.parseInt(recibido.trim())-1), "Mensaje", 0);
        try {
            String nonceMenos1 = ppal.ejecutarAlgoritmoCifrado(this.ks.getText(), Integer.toString(Integer.parseInt(recibido.trim())-1));
            this.servidor.paso5(this.destino.getText(), nonceMenos1);
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }
    //Funcion que se ocupa cuando B recibe el nonce-1 y lo muestra en su interfaz grafica
    public void aviso5(String mensaje){
        System.out.println("Mensaje recibido encriptado: "+mensaje);
        System.out.println("Clave: "+this.ck.getText());
        String recibido = ppal.ejecutarAlgoritmoDescifrado(this.ck.getText(), mensaje);
        System.out.println("Mensaje recibido desencriptado: "+recibido);
        this.jTextField11.setText(recibido);
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        destino = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        noncegen = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ks = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        encripta2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        clavePublica = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ck = new javax.swing.JTextField();
        enviador = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nonceGenerado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setToolTipText("Needham Stock");

        jLabel1.setText("Conexión con SA");

        jLabel2.setText("Ingrese nombre de usuario:");

        jLabel3.setText("Ingrese contraseña de usuario:");

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Comenzar conexión");

        jLabel5.setText("Ingrese nombre host destino:");

        jLabel6.setText("Nonce generado:");

        noncegen.setEditable(false);

        jButton2.setText("Enviar petición");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Clave de sesión recibida:");

        ks.setEditable(false);
        ks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ksActionPerformed(evt);
            }
        });

        jLabel8.setText("Mensaje encriptado con Kb");

        jButton3.setText("Enviar a destino");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel16.setText("Ingrese clave de encriptación:");

        clavePublica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavePublicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destino)
                            .addComponent(noncegen)
                            .addComponent(ks))
                        .addGap(50, 50, 50)
                        .addComponent(jButton2)
                        .addGap(88, 88, 88))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clavePublica, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(usuario))
                                .addGap(37, 37, 37)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(encripta2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(clavePublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(noncegen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(encripta2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(138, 138, 138))
        );

        jTabbedPane1.addTab("Establecer Conexión", jPanel1);

        jLabel10.setText("Clave de sesión:");

        jLabel11.setText("Origen:");

        ck.setEditable(false);

        enviador.setEditable(false);

        jButton6.setText("Responder");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel12.setText("Mensaje de host origen");

        jLabel13.setText("Nonce generado:");

        nonceGenerado.setEditable(false);
        nonceGenerado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonceGeneradoActionPerformed(evt);
            }
        });

        jLabel14.setText("Nonce recibido:");

        jTextField11.setEditable(false);

        jLabel15.setText("Respuesta host origen");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(enviador, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ck, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(58, 58, 58))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(144, 144, 144)
                            .addComponent(jLabel12))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nonceGenerado, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addComponent(jTextField11)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(ck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(enviador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(nonceGenerado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conexión Entrante", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // al presionar el boton registrar comienza la interaccion con el SA y se registra el cliente
        // cuando la contraseña es correcta, caso contrario se muestra mensaje de error acorde
        this.nombreUsuario=this.usuario.getText();
        this.clavePublicaUsuario = clavePublica.getText();
        if ((this.nombreUsuario.length() != 0) && (clavePublica.getText().length() != 0)) {
            try{
                if (iniciarRegistry()){
                    if(this.servidor.registrarCliente(this.cliente,usuario.getText(),password.getText(),clavePublica.getText())){
                        JOptionPane.showMessageDialog(this, "Conectado a SA", "Mensaje",0);
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ksActionPerformed
    // al presionar el boton "enviar peticion" comienza el paso 2, donde se envia 
    // al SA la peticion de clave de sesion para establecer conexion con B
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        /*
        String userdestino = destino.getText();
        int nonce = new Random().nextInt(1001) + 1;
        String mensaje = this.nombreUsuario+";"+userdestino+";"+Integer.toString(nonce);
        this.noncegen.setText(Integer.toString(nonce));
        String respuesta;
        try {
            respuesta = this.servidor.paso2(mensaje);
            System.out.println("Mensaje encriptado: "+respuesta);
            String recibido = ppal.ejecutarAlgoritmoDescifrado(this.clavePublicaUsuario, respuesta);
            System.out.println("Mensaje desencriptado: "+recibido);
            StringTokenizer token = new StringTokenizer(recibido, ";"); 
            String clavesesion = token.nextToken();
            clavesesion = token.nextToken();
            clavesesion = token.nextToken();
            String mensajeadestino = token.nextToken();
            this.ks.setText(clavesesion);
            this.encripta2.setText(mensajeadestino);
            this.mensajeparadestino = mensajeadestino;
            this.destinatario = userdestino;
        }
        catch (RemoteException ex){
            System.out.println(ex);
        }*/
    }//GEN-LAST:event_jButton2MouseClicked
    // cuando se selecciona el boton enviar a destino comienza el paso 3, en donde A envia a B
    // la clave de sesion y su nombre encriptado con la clave de B, lo que es proporcionado por el
    // SA
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        try{
            if(iniciarRegistry()){
                servidor.paso3(this.destino.getText(),this.mensajeparadestino);
            }
        }
        catch (RemoteException ex){
                System.out.println(ex);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void clavePublicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavePublicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clavePublicaActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed
    // Al seleccionar el boton "responder" se genera el desafio y se envia encriptado
    // con la clave de sesion generada por el SA
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        /*try{
          
            if(iniciarRegistry()){
                String nonceEncriptado = ppal.ejecutarAlgoritmoCifrado(this.ck.getText(),this.nonceGenerado.getText());
                servidor.paso4(this.enviador.getText().trim(),nonceEncriptado);
            }
        }
        catch (RemoteException ex){
                System.out.println(ex);
        }*/
    }//GEN-LAST:event_jButton6ActionPerformed

    private void nonceGeneradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonceGeneradoActionPerformed
    }//GEN-LAST:event_nonceGeneradoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ck;
    private javax.swing.JTextField clavePublica;
    private javax.swing.JTextField destino;
    private javax.swing.JTextField encripta2;
    private javax.swing.JTextField enviador;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField ks;
    private javax.swing.JTextField nonceGenerado;
    private javax.swing.JTextField noncegen;
    private javax.swing.JTextField password;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
