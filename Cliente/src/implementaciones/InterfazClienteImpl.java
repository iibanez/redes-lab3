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
        //diferentes notificaciones de mensaje
        if(funcion == 0){
            //cuando se encuentra en curso establecer la conexión
             Cliente.getInstanciaVista().trasmitir_a(mensaje);
        }
        else if(funcion == 1){
            //cuando se comienza a realizar el envio de datos
            Cliente.getInstanciaVista().mostrar_mensaje(mensaje);
        }
    }
}
