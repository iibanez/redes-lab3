package Codificador;


public class Decodificacion{
	
	private char array_key[];
	private char array_criptomensaje[];
	private int p_trasposicion  = 0;
	private int bloques;
	
	//es el constructor de la clase decodificación
	//key: corresponde a la clave ingresada por el usuario
	//mensaje: corresponde al mensaje codificado
	//bloques: es el largode los bloques
	public Decodificacion(String key, String mensaje, int bloques){
		this.bloques = bloques;
		this.array_key = completar_clave(key);
		this.array_criptomensaje = mensaje.toCharArray();
	}
	
	//es para setear el mensaje que se va a decodificar
	public void set_criptomensaje(String mensaje){
		this.array_criptomensaje = mensaje.toCharArray();
	}
	
	//se obtiene el mensaje decodificado
	public String get_criptomensaje(){
		String hex = new String(this.array_criptomensaje, (this.bloques-2), 2);
		int n = (int) Long.parseLong(hex, 16);
		String m = new String(this.array_criptomensaje,0,n);
		return m;
	}
	
	//se completa la clave al tamaño del mensaje para realizar la decodifcación
	private char[] completar_clave(String key){
		//duplicar clave
		int i = 0;
		while(key.length()<this.bloques){
			key = key + key.charAt(i);
			i++;
		}
		
		return key.toCharArray();
	}
	
	//se realiza la sustitución pero de manera inversa en la decodificación
	private void sustitucion(){
		
		for(int i=0;i<this.array_criptomensaje.length;i++){
			this.array_criptomensaje[i] = (char)(this.array_criptomensaje[i] - this.array_key[i]);
		}
		
	}
	
	//se realiza la transposición de manera inversa para volver las
	//las columnas a su posición original
	private void transposicion(){
		
		//Se genera la manera de hacer la trasnpociion
		if(this.p_trasposicion == -1){
			this.p_trasposicion = array_key.length-8;
		}
		
		int p = 0;
		int[] ordenar = new int[8];
		for(int i=p_trasposicion;i<(8+p_trasposicion);i++){
			ordenar[p] = (int)array_key[i]; 
			p++;
		}
		this.p_trasposicion--;
		
		char[][] matriz = new char[4][8]; //matriz de transposicion

		//confirmar que es factible el mensaja
		int m = this.p_trasposicion+1;
		if((m+32)>this.bloques){
			m = 0;
		}
		int m1 = m;

		//realizar la transposicion del mensaje
		p = 0;
		for(int j=0; j<8;j++){
			for(int i=0;i<8;i++){
				if((ordenar[p] > ordenar[i] && ordenar[i]!=-1) || (ordenar[p]==-1 && ordenar[i]!=-1)){
					p = i;
				}
			}
			ordenar[p] = -1;
			matriz[0][p] = this.array_criptomensaje[m];
			m++;
			matriz[1][p] = this.array_criptomensaje[m];
			m++;
			matriz[2][p] = this.array_criptomensaje[m];
			m++;
			matriz[3][p] = this.array_criptomensaje[m];
			m++;
		}
		
		//se coloca el mensaje en la matriz de transposicion
		p = m1;
		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				this.array_criptomensaje[p] = matriz[i][j];
				p++;
			}
		}
		
		
		
	}
	
	//se inicia el decodificador
	public void decodificar(){
		
 		this.p_trasposicion = 6;
		int productos = this.array_key.length-1;

		while(productos>=0){
			
			transposicion();
			sustitucion();
			productos--;
		
		}
	}
}	
