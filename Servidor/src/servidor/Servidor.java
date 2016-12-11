package servidor;

import AdministracionClientes.Clientes;
import AdministracionClientes.Cliente;
//import Cifrado.ControladorPrincipal;
import Interfaces.Server;
import interfaz.InterfazCliente;
import interfaz.InterfazServidor;
//import static java.lang.Math.round;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Random;
import java.util.StringTokenizer;

public class Servidor extends UnicastRemoteObject implements InterfazServidor{
    private Connection con;
    private Statement stm;
    private Statement stm1;
    private Statement stm2;
    private Statement stm3;
    private ResultSet rs;
    private ResultSet rs1;
    private ResultSet rs2;
    private ResultSet rs3;
    private Server server = Server.getInstance();
    ////////////////////////////
    //ENCRIPTAR O DESENCRIPTAR
    ///////////////////////////
    //private ControladorPrincipal ppal = new ControladorPrincipal(server);
    private Clientes clientesAuthenticated = new Clientes();
  
    public Servidor() throws RemoteException{}
    
    // Funcion del lado del servidor que permite
    // al cliente registrarse con su nombre de usuario
    // contraseña de usuario y clave publica
    @Override
    public synchronized boolean registrarCliente(InterfazCliente cliente, String username, String password) throws RemoteException{
        if(!this.clientesAuthenticated.authCliente(username, password,cliente)){
            System.out.println("Intento de autenticación de "+username+" fallido");
            return false;
        }
        server.imprimirConexion(username,password);
        System.out.println("Cliente "+username+" autenticado con éxito");
        return true;
    }

    /*@Override
    public void paso3(String destino, String paramString) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public void paso4(String destino, String nonce) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paso5(String destino, String nonce) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    //Aqui el servidor le entrega al cliente el nonce recibido - 1
    //entrega la clave sesion, entrega el nombre del destinario
    //entrega la clave de sesion y el origen encriptado con la clave publica de B
    //y todo esta encriptado con la clave publica de A
    //@Override
    /*public synchronized String paso2(String mensaje) throws RemoteException{
        StringTokenizer token = new StringTokenizer(mensaje, ";"); 
        String origen = token.nextToken();
        String destino = token.nextToken();
        int A = Integer.parseInt(token.nextToken());
        Cliente clienteDestino=clientesAuthenticated.getCliente(destino);
        Cliente clienteOrigen=clientesAuthenticated.getCliente(origen);
        if(clienteDestino==null || clienteOrigen==null ){
            return "";
        }
        //String clavedestino = clienteDestino.getClavePublica();
        //String claveorigen = clienteOrigen.getClavePublica();
        //String clavesesion = Integer.toString(round(10000000 +  new Random().nextFloat()*90000000));
        //String mensajedestino = clavesesion+";"+origen;
        /////////////
        //ENCRIPTAR
        /////////////
        //String mensajedestino = ppal.ejecutarAlgoritmoCifrado(clavedestino, mensajedestino);
        //String mensajeorigen = Integer.toString(nonce)+";"+destino+";"+clavesesion+";"+mensajedestino;
        ///////////////
        //DESENCRIPTAR
        ///////////////
        //mensajeorigen = ppal.ejecutarAlgoritmoCifrado(claveorigen,mensajeorigen);
        //System.out.println("O: "+origen+" - D: "+destino+" Mensaje: "+mensajeorigen);
        
        return mensajeorigen;
    }*/
    
    
    // Funcion de lado del servidor que permite a A enviar el mensaje correspondiente en el paso 3
    // a B
    @Override
    public synchronized void paso3(String clienteDestino, String mensaje) throws RemoteException{
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,0);
    }
    
    /*
    // Funcion de lado del servidor que permite a B enviar su nonce a A
    //@Override
    public void paso4(String destino, String nonce) throws RemoteException {
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(destino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(nonce,4);
    }
    // Funcion de lado del servidor que permite a A enviar el nonce-1 de B
    // a B
    //@Override
    public void paso5(String destino, String nonce) throws RemoteException {
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(destino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(nonce,5);
    }*/

    @Override
    public String paso2(String mensaje) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}