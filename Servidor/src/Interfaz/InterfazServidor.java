package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// se declaran las funciones disponibles por el servidor
public abstract interface InterfazServidor extends Remote{
    public static final ArrayList clientes = new ArrayList();
    public abstract boolean registrarCliente(InterfazCliente cliente, String username, String password) throws RemoteException;
    public abstract void enviar_p(String destino,String paramString) throws RemoteException;
    public abstract void enviar_g(String destino,String paramString) throws RemoteException;
    public abstract void enviar_a(String destino,String paramString) throws RemoteException;
    public abstract void enviar_mensaje(String destino,String paramString) throws RemoteException;
}