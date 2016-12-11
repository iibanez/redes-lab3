import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo{

	private String nombre_archivo;
	private String texto = "";
	private int inicio = 0;
	private int fin  = 0;
	private int bloques;
	private int salir = 1;

	//constructor de la clase LeerArchivo
	//nombre: corresponde al nombre del archivo de texto a leer
	//bloques: corresponde al largo de los bloques en la lectura
	public LeerArchivo(String nombre, int bloques){
		this.bloques = bloques - 2;
		this.nombre_archivo =  nombre;
	}

	//encargada de la lectura del archivo de texto uniendo todas las lineas
	//en un string
	public void leerContenido() throws FileNotFoundException, IOException {
    	String cadena;
    	FileReader f = new FileReader(this.nombre_archivo);
    	BufferedReader b = new BufferedReader(f);
    	int i = 0;
    	while((cadena = b.readLine())!=null) {
    		if(texto!=""){
    			this.texto = this.texto +"\n"+ cadena;
    		}else{
    			this.texto = cadena;
    		}
    	}
      	b.close();
      	this.fin = this.texto.length();
	}
        
        public void setTexto(String text){
            this.texto = text;
            this.fin = this.texto.length();
        }
        
	//obtener el pedaso de texto para generar la codificació
	public String get_subtexto(){
		int sacar = inicio + this.bloques;
		if(sacar>this.fin){
			this.salir = 0;
			return this.texto.substring(this.inicio,this.fin);
		}
		String mensaje = this.texto.substring(this.inicio,sacar);
		this.inicio = sacar;
		return mensaje;
	}

	//nos dice cuando terminamos de realizar toda la codificación
	//del archivo de texto
	public int get_salir(){
		return this.salir;
	}

}
