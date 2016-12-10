package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

// se declaran las funciones disponibles del cliente
public abstract interface InterfazCliente extends Remote{
    public abstract void notificar(String mensaje,int funcion) throws RemoteException;
}
