package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

    // se declara las funciones que tiene disponible el servidor
public abstract interface InterfazServidor extends Remote{
    public static final ArrayList clientes = new ArrayList();
    public abstract boolean registrarCliente(InterfazCliente cliente, String username, String password, String clave) throws RemoteException;
    public abstract String paso2(String mensaje) throws RemoteException;
    public abstract void paso3(String destino, String paramString) throws RemoteException;
    public abstract void paso4(String destino, String nonce) throws RemoteException;
    public abstract void paso5(String destino, String nonce) throws RemoteException;
}
