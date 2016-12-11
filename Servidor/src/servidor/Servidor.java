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

    @Override
    public synchronized void enviar_a(String clienteDestino, String mensaje) throws RemoteException{
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,0);
    }
    
    @Override
    public synchronized void enviar_mensaje(String clienteDestino, String mensaje) throws RemoteException{
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,1);
    }
    
}