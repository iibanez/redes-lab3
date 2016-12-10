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
        if(funcion==3){
            Cliente.getInstanciaVista().aviso3(mensaje);
        }
        else if(funcion==4){
            Cliente.getInstanciaVista().aviso4(mensaje);
        }
        else if(funcion==5){
            Cliente.getInstanciaVista().aviso5(mensaje);
        }
    }
}
