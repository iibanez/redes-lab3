package implementaciones;

//import Cifrado.CifradoDescifrado;
import Codificador.*;
import Interfaces.Cliente;

public class ControladorPrincipal{
    Cliente vista;
    int bloques = 32; //corresponde al tamaño del bloque que puede cambiar de 32 hasta 255
                           //considerar que al verdadero valor con el mensaje es de 30 hasta 253
                           //debido a que 2 caracteres son para indicar el valor real del mensaje
  
    public ControladorPrincipal(Cliente vista){
        this.vista = vista;
    }
    // funcion que utiliza la implementacion del algoritmo para realizar la 
    //codificación del mensaje
    public String ejecutarAlgoritmoCifrado(String clave, String texto){ 
        //mensaje
        LeerArchivo la = new LeerArchivo(bloques);
        la.setTexto(texto);

        //codificación
        Codificacion c = new Codificacion(clave,la.get_subtexto(), bloques);
        c.codificar();
        return c.get_mensaje();
    }
  
    // Funcion que ejecuta la desencriptacion utilizando el algoritmo
    public String ejecutarAlgoritmoDescifrado(String clave, String textoCodificado){
        Decodificacion d = new Decodificacion(clave,textoCodificado,bloques);
        d.decodificar();
        return d.get_criptomensaje();
    }
  
    public void cerrar(){
        System.exit(0);
    }
}