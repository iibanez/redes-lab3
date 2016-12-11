package Codificador;

import java.util.Random;

public class Codificacion{
	
	private char array_key[];
	private char array_mensaje[];
	private int p_trasposicion  = 0;
	private int bloques;
	
	//constructor de la clase codificación
	//key: corresponde a la clave ingresada por el usuario
	//mensaje: corresponde al troso de bloque que sera decodificado
	//bloques: es el tamaño de bloques con el cual estamos trabajando
	public Codificacion(String key, String mensaje, int bloques){
		this.bloques = bloques;
		this.array_mensaje = completar_mensaje(mensaje);
		this.array_key = completar_clave(key);
	}
	
	//es para cambiar el mensaje que se codificara
	public void set_mensaje(String mensaje){
		this.array_mensaje = completar_mensaje(mensaje);
	}
	
	//se obtiene el mensaje codificado
	public String get_mensaje(){
		return String.valueOf(array_mensaje);
	}
	
	//la clave se va duplicando hasta que alcanza el tamaño del bloque
	private char[] completar_clave(String key){
		//duplicar clave
		int i = 0;
		int largo = key.length();
		while(key.length()<this.bloques){
			key = key + key.charAt(i);
			i++;
		}
		
		return key.toCharArray();
	}
	
	//el mensaje si es menor a 100 caracteres se debe completar además se les debe ir añadiendo
	//el largo del mensaje al final de cada bloque
	private char[] completar_mensaje(String mensaje){
		int len_real = mensaje.length();
		if(len_real<(this.bloques-2)){
			Random rn = new Random();
			int i = mensaje.length();
			StringBuilder sb = new StringBuilder();
			while(i<(this.bloques-2)){
				sb.append(Character.toChars(rn.nextInt(255 - 32 + 1) + 32)); //random entre 32 y 255
				i++;
			}
			mensaje = mensaje + sb.toString();
		}
		String largo = Integer.toHexString(len_real);
		if(largo.length() == 1){
			largo =  '0' + largo;
		}
		mensaje = mensaje + largo;
		
		return mensaje.toCharArray();
	}
	
	//se realiza la sustitución del texto sumandole el valor
	//de la clave
	private void sustitucion(){
		for(int i=0;i<array_mensaje.length;i++){
			this.array_mensaje[i] = (char)(this.array_mensaje[i] + this.array_key[i]);
		}
		
	}
	
	//se realiza la trasposición de 8 filas respecto a la clave
	private void transposicion(){
		
		//Se genera la manera de hacer la transposicion
		int[] ordenar = new int[8];
		if((this.p_trasposicion+8)>array_key.length){
			this.p_trasposicion = 0;
		}
		int p = 0;
		for(int i=p_trasposicion;i<(8+p_trasposicion);i++){
			ordenar[p] = (int)array_key[i]; 
			p++;
		}
		this.p_trasposicion++;
		
		//se coloca el mensaje en la matriz de transposicion
		char[][] matriz = new char[4][8];

		//confirmar que es factible el mensaja
		p = this.p_trasposicion-1;
		if((p+32)>this.bloques){
			p = 0;
		}

		int m = p;

		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				matriz[i][j] = this.array_mensaje[p];
				p++;
			}
		}
		
		//realizar la transposicion del mensaje
		p = 0;
		for(int j=0; j<8;j++){
			for(int i=0;i<8;i++){
				if((ordenar[p] > ordenar[i] && ordenar[i]!=-1) || (ordenar[p]==-1 && ordenar[i]!=-1)){
					p = i;
				}
			}
			ordenar[p] = -1;
			this.array_mensaje[m] = matriz[0][p];
			m++;
			this.array_mensaje[m] = matriz[1][p];
			m++;
			this.array_mensaje[m] = matriz[2][p];
			m++;
			this.array_mensaje[m] = matriz[3][p]; 
			m++;
		}
		
		
		
	}
	
	//es la encargada de realizar la sustitución y codificación
	//en el mensaje de texto
	public void codificar(){
		
		this.p_trasposicion = 0;
		int productos = 0;
		
		while(productos<this.array_key.length){
			
			sustitucion();
			transposicion();
			productos++;
		
		}
	}
}	
