package Main;

import Interfaces.Cliente;

public class main {
    public static void main(String[] args) {
        //Se ocupa patron SINGLETON por tanto aquí se inicializa la vista
        Cliente.getInstanciaVista();
    }
}
