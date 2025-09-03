# ALDComputerService

**ALDComputerService** es una aplicación diseñada para facilitar la gestión integral de equipos y sus mantenimientos en centros de informática.

## Propósito del Proyecto

ALDComputerService es una aplicación de escritorio desarrollada en Java que permite a los centros de informática gestionar de manera eficiente sus equipos informáticos y los servicios de mantenimiento asociados. La aplicación proporciona una interfaz gráfica intuitiva para el registro, seguimiento y administración de computadoras (Desktop, Laptop, Server) y sus respectivos procesos de reparación y mantenimiento.

## Tecnologías Utilizadas

### Lenguajes y Frameworks
- **Java 17**: Lenguaje de programación principal
- **Java Swing**: Framework para la interfaz gráfica de usuario
- **NetBeans IDE**: Entorno de desarrollo integrado utilizado

### Herramientas de Construcción
- **Apache Ant**: Sistema de construcción y gestión de proyecto

### Base de Datos
- **MariaDB**: Sistema de gestión de base de datos
- **MariaDB Java Client 3.3.2**: Conector JDBC para acceso a la base de datos

### Librerías y Dependencias
- **JasperReports 6.20.6**: Generación de informes en PDF
- **JFreeChart 1.0.19**: Creación de gráficos y charts
- **JCommon 1.0.24**: Librerías comunes para JFreeChart
- **OpenPDF 1.3.39**: Manipulación y generación de documentos PDF
- **Apache Commons**: 
  - Commons BeanUtils 1.9.4
  - Commons Collections 4.4
  - Commons Digester 2.1
  - Commons Logging 1.3.0
- **JavaHelp 2.0.05**: Sistema de ayuda integrado
- **Clock.jar**: Componente personalizado para funcionalidades de reloj y alarmas

## Descripción

La aplicación permite:

- **Gestión de Datos de Establecimientos:**  
  Registrar y editar información del establecimiento (nombre, dirección, teléfono, cantidad de empleados) a través de la opción "Computer Service Data" en el menú.

- **Administración de Ordenadores:**  
  Realizar el alta, edición y eliminación de registros de computadoras (Desktop, Laptop, Server) desde la sección "Computers" en "Computers & Repairs".

- **Servicio de Mantenimiento:**  
  Registrar solicitudes de reparación, hacer seguimiento y actualizar el estado de los mantenimientos en la sección "Repairs".

- **Reloj y Alarma:**  
  Configurar el formato del reloj (12 o 24 horas) y programar alarmas con mensajes personalizados mediante el icono de notificaciones.

- **Generación de Informes:**  
  Crear informes en PDF para facilitar el análisis y seguimiento de las operaciones, accediendo a "Reports > Print Reports...".

## Instalación y Configuración

### Para Usuarios Finales

La instalación en sistemas Ubuntu se realiza mediante un archivo autoejecutable. Para instalar la aplicación:

1. **Descarga:**  
   Descarga el archivo autoejecutable de ALDComputerService desde el sitio oficial o el enlace proporcionado.

2. **Ejecución:**  
   Ubica el archivo descargado y haz doble clic para iniciar el asistente de instalación.

3. **Asistente de Instalación:**  
   Sigue las instrucciones del asistente para completar la instalación.

4. **Configuración Inicial:**  
   Una vez instalada la aplicación, ingresa los datos de tu servicio de computación según lo indicado en el manual.

Consulta el [Manual de Instalación y Administración](docs/ALD/UD6_Barreiro_Liste_Diego_Manual_de_instalación_HTML.html) para más detalles.

### Para Desarrolladores

#### Prerrequisitos
- **Java JDK 17 o superior**
- **Apache Ant** (para construcción del proyecto)
- **NetBeans IDE** (recomendado para desarrollo)
- **MariaDB Server** (para base de datos)

#### Configuración del Entorno de Desarrollo

1. **Clonar el Repositorio:**
   ```bash
   git clone https://github.com/Dieg0bl/ALDComputerService.git
   cd ALDComputerService
   ```

2. **Verificar Dependencias:**
   Las librerías necesarias ya están incluidas en la carpeta `lib/`:
   ```bash
   ls lib/
   ```

3. **Compilar el Proyecto:**
   ```bash
   ant clean
   ant compile
   ```

4. **Generar el JAR Ejecutable:**
   ```bash
   ant jar
   ```

5. **Ejecutar la Aplicación:**
   ```bash
   # Opción 1: Usando Ant
   ant run
   
   # Opción 2: Ejecutando el JAR directamente
   java -cp "lib/*:dist/Barreiro_Liste_Diego_Proxecto_UD2_ALD_Computer_Service.jar" main.Main
   ```

#### Abrir en NetBeans
1. Abrir NetBeans IDE
2. File → Open Project
3. Seleccionar la carpeta del proyecto ALDComputerService
4. El proyecto se abrirá con todas las configuraciones y dependencias

## Estructura del Proyecto

```
ALDComputerService/
├── src/                          # Código fuente
│   ├── main/                     # Clase principal
│   ├── controller/               # Controladores MVC
│   ├── model/                    # Modelos de datos
│   ├── view/                     # Interfaces de usuario
│   ├── reportTemplate/          # Plantillas de informes
│   └── icons/                   # Recursos gráficos
├── lib/                         # Librerías externas
├── docs/                        # Documentación
├── build.xml                    # Configuración de construcción Ant
├── nbproject/                   # Configuración NetBeans
└── README.md                    # Este archivo
```

## Documentación Adicional

Se incluye documentación complementaria para una referencia completa:

- **Guía de Referencia Avanzada:**  
  Detalla en profundidad las funcionalidades avanzadas de la aplicación.  
  [Guía de Referencia Avanzada (PDF)](docs/ALD/UD6_Barreiro_Liste_Diego_Guía_de_Referencia.pdf)

- **Manual de Usuario:**  
  Instrucciones detalladas para el uso de la aplicación.  
  [Manual de Usuario (PDF)](docs/ALD/UD6_Barreiro_Liste_Diego_Manual%20de%20Usuario.pdf)

- **Manual de Instalación y Administración:**  
  Instrucciones detalladas para la instalación y configuración en Ubuntu.  
  [Manual de Instalación y Administración (HTML)](docs/ALD/UD6_Barreiro_Liste_Diego_Manual_de_instalación_HTML.html)

## Uso

- **Gestión de Establecimientos:**  
  Accede a "File > Computer Service Data" para ingresar o modificar la información del establecimiento.

- **Administración de Ordenadores:**  
  Utiliza "File > Computers & Repairs > Computers" para dar de alta, editar o eliminar registros de equipos.

- **Servicio de Mantenimiento:**  
  Registra nuevas solicitudes de reparación y realiza el seguimiento desde "File > Computers & Repairs > Repairs".

- **Reloj y Alarma:**  
  Configura las opciones de reloj y alarmas desde el menú "Notifications".

- **Generación de Informes:**  
  Genera informes PDF mediante "Reports > Print Reports...", asegurándote de tener configurada la conexión a la base de datos si aplica.

## Requisitos del Sistema

### Para Usuarios Finales
- **Sistema Operativo:**  
  Ubuntu (según las instrucciones de instalación), aunque la aplicación puede ejecutarse en otros entornos compatibles con Java.

- **Java Runtime Environment (JRE):**  
  JRE 17 o superior para ejecutar la aplicación.

### Para Desarrolladores
- **Java Development Kit (JDK):**  
  JDK 17 o superior para compilar y desarrollar.

- **Apache Ant:**  
  Sistema de construcción requerido para compilar el proyecto.

- **MariaDB Server:**  
  Base de datos requerida para las funcionalidades de persistencia.

- **NetBeans IDE:**  
  Recomendado para el desarrollo (proyecto configurado para NetBeans).

### Dependencias Incluidas
Las siguientes librerías están incluidas en la carpeta `lib/` y no requieren instalación adicional:
- JasperReports, JFreeChart, OpenPDF, MariaDB Client, Apache Commons, JavaHelp, Clock.jar

## Comandos de Construcción

### Comandos Ant Disponibles
```bash
# Limpiar archivos de construcción
ant clean

# Compilar código fuente
ant compile

# Generar archivo JAR
ant jar

# Compilar y generar JAR (construcción completa)
ant clean jar

# Ejecutar la aplicación (si está configurado)
ant run
```

### Ejecución Manual
```bash
# Ejecutar desde el JAR generado
java -cp "lib/*:dist/Barreiro_Liste_Diego_Proxecto_UD2_ALD_Computer_Service.jar" main.Main

# Alternativamente, con todas las librerías en el classpath
java -cp "lib/Clock.jar:lib/jasperreports-6.20.6.jar:lib/mariadb-java-client-3.3.2.jar:lib/jcommon-1.0.24.jar:lib/commons-logging-1.3.0.jar:lib/commons-digester-2.1.jar:lib/commons-collections4-4.4.jar:lib/commons-beanutils-1.9.4.jar:lib/jfreechart-1.0.19.jar:lib/openpdf-1.3.39.jar:lib/javahelp-2.0.05.jar:dist/Barreiro_Liste_Diego_Proxecto_UD2_ALD_Computer_Service.jar" main.Main
```

## Soporte

Para consultas o problemas durante la instalación y uso de la aplicación, consulta la documentación incluida o comunícate con el soporte técnico a través del sitio oficial.


