package AdministracionClientes;

import interfaz.InterfazCliente;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Clientes {
    //Crea un arreglo para todos los clientes que estan en la DB
    //y otro para los clientes que efectivamente estan registrados en el servidor
    private List<Cliente> clientes;
    private List<Cliente> clientesAuthenticated;
    //Ruta estatica de la DB
    private final String db = "DBClientes.txt";
    //Se inicializan los arreglos y se cargan los datos de la DB
    public Clientes() {
        clientes = new ArrayList<>();
        clientesAuthenticated = new ArrayList<>();
        this.loadClientes();
    }
    
    //Se leen las lineas del archivo donde se encuentran los usuarios
    //y se agregan estos al arreglo de clientes
    private void loadClientes(){
        /*try {
            BufferedReader br = new BufferedReader(new FileReader(this.db));
            String linea;
            String[] partes;
            while(br.ready()){
                linea = br.readLine();
                partes = linea.split(";");
                this.clientes.add(new Cliente(partes[0],partes[1]));
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error carga de archivo: "+ex.getMessage());
        }
        catch(IOException ex){
            System.out.println("Error carga de archivo: "+ex.getMessage());
        }*/
        this.clientes.add(new Cliente("ignacio","123"));
        this.clientes.add(new Cliente("naty","123"));
    }
    
    //Se agrega cliente a la DB (esta funcion no se usa)
    /*public void addCliente(String username,String password){
        try {
            FileWriter file = new FileWriter(this.db,true);
            String linea = username+";"+password;
            file.write(linea+"\n");
            file.close();
            this.clientes.add(new Cliente(username,password));
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error carga de archivo: "+ex.getMessage());
        }
        catch(IOException ex){
            System.out.println("Error carga de archivo: "+ex.getMessage());
        }
    }*/
    
    //Se revisa en el arreglo de clientes registrados, si el usuario
    //"username" se encuentra registrado
    public Cliente getCliente(String username){
        for(Cliente c: clientesAuthenticated){
            if(c.getUsername().equals(username)){
                return c;
            }
        }
        System.out.println("Cliente: "+username+" no existe dentro de los clientes conectados");
        return null;
    }
    
    //Se registra al cliente "username" con contraseña "password"
    //y se setean los datos InterfazCliente y Clave Publica correspondientemente
    public boolean authCliente(String username,String password,InterfazCliente ic){
        for(Cliente c: clientes){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                this.clientesAuthenticated.add(new Cliente(username,password,ic));
                return true;
            }
        }
        System.out.println("Cliente: "+username+" no existe en la DB o contraseña incorrecta");
        return false;
    } 
    //Funciona que retorna la cantidad de usuarios registrados
    public int clientesLenght(){
        return clientesAuthenticated.size();
    }
}
