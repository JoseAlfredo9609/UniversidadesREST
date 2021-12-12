# UniversidadesREST
Paso a paso para ejecutar la aplicaci�n:

### Caso Practico
- Proyecto basado en una universidad.
- Necesidad b�sica CRUD de todas las entidades.
- M�todos de consultas particulares.
- Aplicaci�n expuesta como servicio Rest.

**Las entidades que manejara el sistemas son:**

+ Aula
+ Pabell�n
+ Carrera
+ Persona:
    * Alumno
    * Profesor
    * Empleado

------------

**Datos por entidad:**

- Datos b�sicos de para alumnos, profesores y empleados son: Nombre, Apellido, Dni y Direcci�n, los datos de este ultimo son: Calle, Numero, Piso, Departamento, C�digo Postal, Localidad.

- Profesor tiene sueldo

- Empledo tiene sueldo y tipo de empleado (Mantenimiento, Administrativo). 

- Aula tiene:  Numero, Tama�o (mts x mts), Cantidad de pupitres, Tipo de pizarr�n (Tiza o Pizarra).

- Pabellon tiene: Tama�o (mts�), Nombre y Direcci�n con los mismos datos que persona.

- Carrera tiene: Nombre, Cantidad de Materias y Cantidad de A�os estimados.


------------

**Datos extras:**

- Cada entidad tiene un ID propio como tambi�n, Fecha de alta y modificaci�n.

- Al dar de alta un Alumno, se le puede asignar una Carrera y para el caso de los Profesores se le puede asignar una o mas Carreras.

- Al dar de alta un Aula, se le puede asignar un Pabell�n y este ultimo puede contener varias Aulas.

- Al dar de alta un Empleado, se le puede asignar un Pabell�n.

------------

1. Instalar PostgreSQL
- Al momento  de instalar el motor de base de datos tomar en cuenta el siguiente requermiento:
- contrasena: admin

(Si tiene instalado PostgreSQL omitir este paso y configure la contrase�a de la base de datos en proyecto en la seccion src/main/resources application.properties) en el proyecto.

2. Luego de instalar el motor de base de datos debe:

- Crear base de datos universidad.

- create schema universidad;

3. Clonar y descargar el presente proyecto:

- https://github.com/JoseAlfredo9609/UniversidadesREST.git

4. Importar el proyecto como MAVEN en la IDE spring tool suite
**se debe tener instalada la librer�a lombook**

5. Ejecutar el proyecto **debug as spring boot app**

------------