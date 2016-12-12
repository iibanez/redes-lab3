# Lab 3 de sistemas de comunicación (Protocolo Diffie-Hellman)




## Ejecución
Para realizar la ejecución del programa se deben abrir ambos proyectos en Netbeans, tanto cliente como servidor. 
Del servidor se debe crear una sola instancia y del cliente dos de ellas. Los pasos presentados a continuación, el orden de configuración de las interfaces de cliente es independiente de la interfaz elegida, es decir, puede configurarse en cualquier orden, sin embargo se tomará un caso base para explicar el procedimiento.


### Paso 1
En la interfaz del servidor dar click en "Iniciar Server".


### Paso 2
En la primera interfaz de cliente se debe ingresar nombre y password de usuario, dar click en "registrar" y "aceptar. 
Apareceran mensajes avisando que el usuario es valido, tanto para el servidor como para el cliente. (CLIENTE 1).

```
    Usuario: ignacio
    Password: 123
```
En la segunda interfaz de cliente, se debe realizar el mismo paso anterior pero con los siguientes datos de usuario (CLIENTE 2).
```
    Usuario: naty
    Password: 123
```


### Paso 3
En CLIENTE 1 se debe ingresar el destino con el que vamos a establecer la conexión, en este caso es "naty" y damos click en "Enviar petición", en el CLIENTE 2  aparece un mensaje solicitando conexión del usuario ignacio y que ha establecido el valor de p.


### Paso 4
En CLIENTE 2 procedemos a colocar en destino "ignacio" y dar click en "Enviar petición", en el  CLIENTE 1  aparece un mensaje diciendo que el CLIENTE 2 respondió la solicitud de conexión y establece el valor de g.


### Paso 5
En CLIENTE 1 dar click en “Establecer clave”  y esto muestra un mensaje en el CLIENTE 2 de qué se quiere continuar estableciendo conexión y que le han enviado su valor de B.


### Paso 6
En CLIENTE 2 dar click en “Establecer clave” y esto muestra un mensaje en CLIENTE 1 de que se ha respondido la solicitud de conexión enviando su valor de B y que ambos ya comparten la clave secreta.


### Paso 7
En ambos clientes se ha activado el botón para poder visualizar clave secreta por lo que a ambos clientes se les da click en "Ver clave secreta" la que debe coincidir para lograr establecer la comunicación de manera correcta.


### Paso 8
En CLIENTE 1 donde dice mensaje escriba lo que desea enviar y click en "Enviar a destino".


### Paso 9
En el  CLIENTE 2 le aparece un mensaje dando a conocer que nos ha llegado un mensaje el que se encuentra encriptado y fue desencriptado, lo cual es posible visualizar en nuestra interfaz de cliente.


### Paso 10
Puede continuar realizando el envío de mensajes desde un cliente a otro y poder visualizar los pasos 8 y 9 de maner consecutiva, una vez terminado de enviar los mensajes cerrar ambos clientes, en la pestaña del servidor dar click en "Detener Server" y cerrar ventana.




## Errores




### Problema con usuarios y password
En caso de querer cambiar los usuarios lo puede realizar en la carpeta "Servidor" y en el archivo "DBClientes.txt".




### Problema con destino
El destino al que quiere enviar los mensajes puede no estar creado.


[//]: # ( http://dillinger.io/, https://stackedit.io/editor)
