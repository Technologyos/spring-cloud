<h1 align="center">Spring Cloud</h1>

1. **Crear el contenedor:** Para crear el contenedor, ejecutamos el siguiente comando en la terminal: `docker-compose up -d`. Una vez finalizada la creación, nos dirigimos a la siguiente URL de pgAdmin en nuestro navegador: [http://localhost:8080/login?next=%2F](http://localhost:8080/login?next=%2F).

2. **Ingresar credenciales del archivo .env:** Antes de continuar, debemos ingresar nuestras credenciales del archivo `.env` para acceder a pgAdmin. Buscaremos las variables `PGA_EMAIL` y `PGA_PASSWORD` y las ingresaremos en el login.

3. **Crear el servidor en pgAdmin:** En la interfaz de pgAdmin, nos dirigimos a la sección de "Servers". Haciendo clic derecho, desplegaremos un menú en el que seleccionaremos "Register" y luego "Server".

4. **Configuración del servidor:** En la ventana de configuración del servidor, ingresaremos los siguientes datos:

   #### General
    - **name:** postgres (nombre del contenedor de PostgreSQL)

   #### Connection
    - **Host name/addess:** postgres (nombre del contenedor de PostgreSQL)
    - **Post:** 5432
    - **Maintenance database:** space_db
    - **username:** technologyos
    - **password:** YXNhbGF6YXJqQGdtYWlsLmNvbToqR

Después de completar los datos, haremos clic en "Save" para establecer la conexión.

**Nota:** Es importante recordar que estos datos se encuentran en el archivo `.env`, donde previamente hemos configurado nuestras credenciales.
