
package AdministracionClientes;

import interfaz.InterfazCliente;

//clase que encapsulado el tipo de dato Cliente
//posee su nombre de usuario, contraseña
//interfaz de cliente (que es su interfaz RMI) y su clave publica
public class Cliente {
    private String username;
    private String password;
    private InterfazCliente ic;
    private String clavePublica;

    public Cliente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Cliente(String username, String password, InterfazCliente ic, String clavePublica) {
        this.username = username;
        this.password = password;
        this.ic = ic;
        this.clavePublica = clavePublica;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InterfazCliente getIc() {
        return ic;
    }

    public void setIc(InterfazCliente ic) {
        this.ic = ic;
    }

    public String getClavePublica() {
        return clavePublica;
    }

    public void setClavePublica(String clavePublica) {
        this.clavePublica = clavePublica;
    }
    
}
