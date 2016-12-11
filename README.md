# Lab 3 de sistemas de comunicación (Protocolo Diffie-Hellman)


## Ejecución
Para realizar la ejecución del proyecto se debe abrir ambos proyectos en Netbeans tanto cliente como servidor, en servidor se debe crear una instancia y en cliente dos de ellas.


### Paso 1
En la interfaz del servidor dar click en "Iniciar Server".


### Paso 2
En la primera interfaz de cliente se debe ingresar nombre y password de usuario, dar click en "registrar" y "aceptar" ambos mensajes que aparecen tanto en el servidor como cliente avisando que el usuario es válido (CLIENTE 1).
```
    Usuario: ignacio
    Password: 123
```
En la segunda interfaz de cliente realizar lo mismo anterior pero con los siguientes datos de usuario (CLIENTE 2).
```
    Usuario: naty
    Password: 123
```


### Paso 3
En CLIENTE 1 (“ignacio”) se debe ingresar el destino con el que vamos a iniciar la conexión que en este caso es "naty" y damos click en "Enviar petición".


### Paso 4
En CLIENTE 2 nos mostrar un mensaje de que el usuario ignacio (CLIENTE 1) quiere iniciar una conexión con nosotros damos click en aceptar y procedemos a colocar en destino "ignacio" y dar click en "Enviar petición".


### Paso 5
El CLIENTE 1 nos va a mostrar un mensaje de que la conexión fue aceptada.


### Paso 6
En ambos clientes se nos ha activado el botón para poder visualizar nuestra clave secreta por lo que a ambos clientes le damos click en "Ver clave secreta" la que debería coincidir para poder lograr establecer la comunicación de manera correcta.


### Paso 7
Desde cualquier cliente en la parte donde dice mensaje escriba lo que desea enviar y click en "Enviar a destino".


### Paso 8
En el cliente opuesto al que fue enviado el mensaje nos aparecerá un mensaje dándonos a conocer que nos ha llegado un mensaje el que se encuentra encriptado y fue desencriptado, lo cual es posible visualizar en nuestra interfaz de cliente.


### Paso 9
Puede continuar realizando el envío de mensajes desde un cliente a otro y poder visualizar los pasos 7 y 8 de maner consecutiva, una vez terminado de enviar los mensajes cerrar ambos clientes, en la pestaña del servidor dar click en "Detener Server" y cerrar ventana.


## Errores


### Problema con usuarios y password
En caso de querer cambiar los usuarios lo puede realizar en la carpeta "Servidor" y en el archivo "DBClientes.txt".


### Problema con destino
El destino al que quiere enviar los mensajes puede no estar creado.

[//]: # ( http://dillinger.io/, https://stackedit.io/editor)
