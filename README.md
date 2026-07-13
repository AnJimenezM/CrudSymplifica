Sistema crud para la creación de un Dashboard unificado que consuma información de una APIs interna y una externa. La información en el cual
fue desarrollado utilizando Spring boot y vue.js.

se utilizo la base de datos de Postgresql para la creacion de la información de los emepleados y beneficios

Se preparo el backend para configurar el entorno de desarrollo por medio de maven, java21 y estas depedencias:
-Spring web 
-validation
-Lombok
-SpringDataJPA
-JUnit 5
- Mockito

Api externa con OpenStreetMap para consultar las coordenas externas como la latitud y longitud

En la implementación del backend implemente modulos para separar separar la logica utilizando la arquitectura MVC de la siguiente manera.
Entity: para representar las columnas de la base de datos
Repository: interactura con la base de datos
Service: contiene la logica para utilizar los ficheros para crear una conexion a la interaccion entre las peticiones
Controller: controla toda la funcionalidad del crud como: listar todos los empleados, listar por su identificador, actualizar campos y eliminar campos.

se generaron las peticiones http con el fin de probar de que cada servicio integrado utilizando el mvc funcionara correctamente

endpoints:
petición GET http://localhost:8080/employees *para consultar todos los empleados existentes en la base de datos
peticion GET http://localhost:8080/employees/3 *para consultar el empleado por medio de su ID
peticion POST http://localhost:8080/employees *para crear un usuario utilizando el formato JSON.

ejemplo empleado:
{
  "nombre": "Carlos",
  "apellido": "Ramírez",
  "correo": "carlos.ramirez@gmail.com",
  "ciudad": "Medellín",
  "posicion": "Desarrollador Backend"
}
ejemplo beneficio:
{
  "nombre_beneficio": "Auxilio de transporte",
  "monto": 120000,
  "empleado": {
    "id_empleado": 3
  }
}

peticion PUT http://localhost:8080/employees/3 *para actualizar un registro 
peticion DELETE http://localhost:8080/employee/3 *para eliminar un registro

se utilizarón dos modulos para realizar la configuracion y consumo de la API de OpenStreetMap para conectarse dentro del backend asi para 
poderla llamar desde el frontend

#Ejecucion del frontend




comando para iniciar proyecto
npm create vue@latest
npm install 

En la implementación del frontend se crearon modulos para desarrollar la estructura de la tabla donde se muestra  el listado de empleados junto
su respectivo boton para crear empleados desplegando un formulario asi como sus respectivos botones de acciones para ver detalle del empleado como su beneficio y el consumo de la API asi como crear beneficio si el empleado no tiene
editar el empleado para actualizar su registro y el boton eliminar empleado asi como tambien en beneficios

se instalo la libreria dentro de vue para manejar estilos modernos utilizando Taildwind CSS
npm install tailwindcss @tailwindcss/vite

para correr el frontend se utiliza el comando de pnpm run dev se alojara en http://localhost:5173/


#Autor Andres Jimenez
