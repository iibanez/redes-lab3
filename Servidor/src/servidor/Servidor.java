package servidor;

import AdministracionClientes.Clientes;
import AdministracionClientes.Cliente;
import Interfaces.Server;
import interfaz.InterfazCliente;
import interfaz.InterfazServidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements InterfazServidor{
    
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
    
    //se encarga de relizar el envio de la peticion de conexión
    @Override
    public synchronized void enviar_a(String clienteDestino, String mensaje) throws RemoteException{
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,0);
    }
    
    //encargada de enviar mensajes entre A y B
    @Override
    public synchronized void enviar_mensaje(String clienteDestino, String mensaje) throws RemoteException{
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,1);
    }

    @Override
    public void enviar_p(String clienteDestino, String mensaje) throws RemoteException {
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,2);
    }

    @Override
    public void enviar_g(String clienteDestino, String mensaje) throws RemoteException {
        Cliente clienteDestinoC = this.clientesAuthenticated.getCliente(clienteDestino);
        InterfazCliente ic = clienteDestinoC.getIc();
        ic.notificar(mensaje,3);
    }
    
    
}