package implementaciones;

import interfaz.InterfazCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Interfaces.Cliente;

    // clase que implementa las funciones que disponibiliza el cliente 
public class InterfazClienteImpl extends UnicastRemoteObject implements InterfazCliente{
    public InterfazClienteImpl() throws RemoteException{
    }

    @Override
    // funcion que notifica dependiendo del paso al cliente que corresponde
    public void notificar(String mensaje,int funcion) throws RemoteException{
        if(funcion == 0){
             Cliente.getInstanciaVista().trasmitir_a(mensaje);
        }
        //else if
    }
}
