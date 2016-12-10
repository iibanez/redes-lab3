package implementaciones;

//import Cifrado.CifradoDescifrado;
//import Cifrado.SubClave;
import Interfaces.Cliente;
import java.util.ArrayList;
import java.util.Vector;

public class ControladorPrincipal{
    private String nombreArchivo;
    /*private SubClave subclaves;
    private CifradoDescifrado cifrar;
    private CifradoDescifrado descifrar;
    private boolean esCifrado;*/
    private ArrayList mensajes = new ArrayList();
    Cliente vista;
  
    public ControladorPrincipal(Cliente vista){
        this.vista = vista;
        this.nombreArchivo = null;
    }
    // funcion que utiliza la implementacion de DES para encriptar un string
    // que te copiamos
    /*public String ejecutarAlgoritmoCifrado(String clave, String texto){ 
        String cifrado;
        String iv = "00000000";
        this.esCifrado = true;

        this.subclaves = new SubClave();
        this.subclaves.generarSubClaves(clave);

        this.cifrar = new CifradoDescifrado();

        Vector auxiliar = new Vector();

        auxiliar.add(this.subclaves.getSubClaveUno());
        auxiliar.add(this.subclaves.getSubClaveDos());
        auxiliar.add(this.subclaves.getSubClaveTres());
        auxiliar.add(this.subclaves.getSubClaveCuatro());
        auxiliar.add(this.subclaves.getSubClaveCinco());
        auxiliar.add(this.subclaves.getSubClaveSeis());
        auxiliar.add(this.subclaves.getSubClaveSiete());
        auxiliar.add(this.subclaves.getSubClaveOcho());
        auxiliar.add(this.subclaves.getSubClaveNueve());
        auxiliar.add(this.subclaves.getSubClaveDiez());
        auxiliar.add(this.subclaves.getSubClaveOnce());
        auxiliar.add(this.subclaves.getSubClaveDoce());
        auxiliar.add(this.subclaves.getSubClaveTrece());
        auxiliar.add(this.subclaves.getSubClaveCatorce());
        auxiliar.add(this.subclaves.getSubClaveQuince());
        auxiliar.add(this.subclaves.getSubClaveDiezySeis());

        this.cifrar.setSubClaves(auxiliar);

        this.cifrar.ejecutarCifrado(texto, iv);

        cifrado = this.cifrar.getTextoCifradoFinal();
        return cifrado;
    }
  
    // Funcion que ejecuta la desencriptacion utilizando el algoritmo DES
  public String ejecutarAlgoritmoDescifrado(String clave, String texto){
    String descifrado;
    String iv = "00000000";
    this.esCifrado = false;

    this.subclaves = new SubClave();
    this.subclaves.generarSubClaves(clave);

    this.descifrar = new CifradoDescifrado();

    Vector auxiliar = new Vector();
    auxiliar.add(this.subclaves.getSubClaveUno());
    auxiliar.add(this.subclaves.getSubClaveDos());
    auxiliar.add(this.subclaves.getSubClaveTres());
    auxiliar.add(this.subclaves.getSubClaveCuatro());
    auxiliar.add(this.subclaves.getSubClaveCinco());
    auxiliar.add(this.subclaves.getSubClaveSeis());
    auxiliar.add(this.subclaves.getSubClaveSiete());
    auxiliar.add(this.subclaves.getSubClaveOcho());
    auxiliar.add(this.subclaves.getSubClaveNueve());
    auxiliar.add(this.subclaves.getSubClaveDiez());
    auxiliar.add(this.subclaves.getSubClaveOnce());
    auxiliar.add(this.subclaves.getSubClaveDoce());
    auxiliar.add(this.subclaves.getSubClaveTrece());
    auxiliar.add(this.subclaves.getSubClaveCatorce());
    auxiliar.add(this.subclaves.getSubClaveQuince());
    auxiliar.add(this.subclaves.getSubClaveDiezySeis());

    this.descifrar.setSubClaves(auxiliar);

    this.descifrar.ejecutarDescifrado(texto, iv);

    descifrado = this.descifrar.getTextoCifradoFinal();
    return descifrado;
    }
  */
    public void cerrar(){
        System.exit(0);
    }
}