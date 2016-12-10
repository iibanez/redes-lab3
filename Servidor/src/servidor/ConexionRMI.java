package servidor;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConexionRMI{
    private static Registry registry;
    
    // Se crea registro en localhost con puerto 1099
    // se registra implementacion "Implementacion"
    public Registry getRegistry() throws RemoteException, AlreadyBoundException{
        startRegistry(1099);
        registry.bind("Implementacion", registry);
        return registry;
    }
    //Se desregistra "Implementacion" en el registro
    public boolean detener()    throws RemoteException  {
        try    {
            registry.unbind("Implementacion");
        }
        catch (NotBoundException ex)    {
            return false;
        }
        catch (AccessException ex)    {
            return false;
        }
        return true;
    }
    //Se optiene registro en el puerto "Puerto"
    private static void startRegistry(int Puerto) throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(Puerto);
            registry.list();
        } catch (RemoteException e) {
            registry = LocateRegistry.createRegistry(Puerto);
            registry.list();
        }
    }
}
