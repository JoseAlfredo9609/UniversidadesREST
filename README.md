# UniversidadesREST
Paso a paso para ejecutar la aplicación:

### Caso Practico
- Proyecto basado en una universidad.
- Necesidad básica CRUD de todas las entidades.
- Métodos de consultas particulares.
- Aplicación expuesta como servicio Rest.

**Las entidades que manejara el sistemas son:**

+ Aula
+ Pabellón
+ Carrera
+ Persona:
    * Alumno
    * Profesor
    * Empleado

------------

**Datos por entidad:**

- Datos básicos de para alumnos, profesores y empleados son: Nombre, Apellido, Dni y Dirección, los datos de este ultimo son: Calle, Numero, Piso, Departamento, Código Postal, Localidad.

- Profesor tiene sueldo

- Empledo tiene sueldo y tipo de empleado (Mantenimiento, Administrativo). 

- Aula tiene:  Numero, Tamaño (mts x mts), Cantidad de pupitres, Tipo de pizarrón (Tiza o Pizarra).

- Pabellon tiene: Tamaño (mts²), Nombre y Dirección con los mismos datos que persona.

- Carrera tiene: Nombre, Cantidad de Materias y Cantidad de Años estimados.


------------

**Datos extras:**

- Cada entidad tiene un ID propio como también, Fecha de alta y modificación.

- Al dar de alta un Alumno, se le puede asignar una Carrera y para el caso de los Profesores se le puede asignar una o mas Carreras.

- Al dar de alta un Aula, se le puede asignar un Pabellón y este ultimo puede contener varias Aulas.

- Al dar de alta un Empleado, se le puede asignar un Pabellón.

------------

1. Instalar PostgreSQL
- Al momento  de instalar el motor de base de datos tomar en cuenta el siguiente requermiento:
- contrasena: admin

(Si tiene instalado PostgreSQL omitir este paso y configure la contraseña de la base de datos en proyecto en la seccion src/main/resources application.properties) en el proyecto.

2. Luego de instalar el motor de base de datos debe:

- Crear base de datos universidad.

- create schema universidad;

3. Clonar y descargar el presente proyecto:

- https://github.com/JoseAlfredo9609/UniversidadesREST.git

4. Importar el proyecto como MAVEN en la IDE spring tool suite
**se debe tener instalada la librería lombook**

5. Ejecutar el proyecto **debug as spring boot app**

------------