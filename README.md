# Microservicios: Colegio

Agrupación de los microservicios para la base de datos `Colegio`.

<small>Puedes probar los endpoints usando la colección JSON ubicado en esta misma raíz u obtenerla desde [este enlace](https://api.postman.com/collections/27127339-f8804391-1613-447f-af3e-f8a1a27e2675?access_key=PMAT-01H5RA7BVEXEDWE5SWZKZFB6TP)</small>

---

## Info General

### Microservicios   

Los microservicios presentes en el repositorio son:

- [Puerto: 9000] Eureka Server Discovery (ms-discovery)  
- [Puerto: 9005] Api Gateway (ms-gateway)
- [Puerto: 9010] Config Server (ms-config-server)
- Config data: | Puerto:  
  - Local -> (ms-config-data)
  - Github -> _Ubicado en un repositorio privado_
      ![a](https://cdn.discordapp.com/attachments/1131379840696721489/1131380071203098624/image.png)
- [Puerto: 9001] Microservicio curso (ms-curso)
- [Puerto: 9003] Microservicio profesor (ms-profesor)
- [Puerto: 9002] Microservicio alumno (ms-alumno)

### Correr el proyecto

#### Docker Compose

1. (Opcional) Generar las bases de datos con los scripts ubicados en esta misma carpeta. Este paso no es necesario ya que por default están configurados (usando docker-compose) para usar la base de datos en nube. Sin embargo puede que las peticiones de relaciones entre alumno - curso / profesor - curso **tarden aproximadamente 5s - 7s** en responder, puesto que la base de datos Sql Server de **ms-curso** está alojada en somee.com de manera gratuita (BD Compartida).
2. Generar los JAR para todos los microservicios
3. Ubicarse en la carpeta raiz del proyecto
4. Ejecutar el comando `docker-compose build` para construir las imágenes
5. Ejecutar el comando `docker-compose up` para ejecutar los contenedores y levantar la aplicación
6. La aplicación debería levantarse
7. Para detener y eliminar los contenedores se puede hacer uso del comando `docker-compose down`


#### (JAVA 11) Correr microservicios manualmente

Para correr los microservicios deberemos de correrlos en el siguiente orden: 

1. ms-discovery
2. ms-config   
   **Nota:** Se iniciará por default el uso de **ms-config-data** (configuracion local) por lo que puede optar por una de las siguientes opciones:
   - Cambiar la propiedad **spring.cloud.config.server.native.searchLocations** en el archivo **application.properties** con la ruta exacta de la carpeta _ms-config-data_. Ejemplo:
    `spring.cloud.config.server.native.searchLocations = file:///C:/Users/ricar/OneDrive/Documentos/Prog/Certus/Microservices/microservices-colegio/ms-config-data`
   - Configurar el perfil cloud: Para hacerlo deberemos dirigirnos al archivo **application.properties** y cambiar el perfil a cloud, es decir:
     - De:__spring.profiles.active = ${PERFIL:native}__ 
     - A: __spring.profiles.active = ${PERFIL:cloud}__   
    O simplemente pasarle una variable de entorno **PERFIL=cloud**
3. ms-curso
4. ms-profesor
5. ms-alumno
6. ms-gateway

_**Nota:**_ _Es importante que primero se inicializen completamente tanto eureka server (ms-discovery) como el servidor de configuración (ms-config-server)_


---

<small>Ricardo Manuel Pelaez Limpi</small>    
<small>_Certus - 53X - Diseño de soluciones basadas en microservicios_</small>