Instructivo Ejecución Automamtización CARNIVAL

Esta automatización fue construida bajo el framewok de selenium v3.8.0 plus apache maven v3.5.4 en lenguaje de programación java sobre el IDE Eclipse. La automatización fue creada como un proyecto MAVEN (Project Object Model POM) por lo cual debe contar con los siguientes pre requisitos:

1.Descargar repositorio Github de la ruta https://github.com/juancrinconca/ColpatriaTest.git

2. Garantizar la correcta instalación de JAVA en la maquina con una versión igual o superior al JDK 1.8. Validar versión con el comando en consola java -version. Ademas garantizar la asociación de la ruta del JDK de java en la variable de entorno path.

3. Garantizar la correcta instalación de apache maven en la maquina con la version 3.5.4. Validar versión con el comando en consola mvn -v. Ademas garantizar la asociación de la ruta de la carpeta bin de MAVEN en la variable de entorno path.

4.Abrir el proyecto descargado como proyecto MAVAN de la siguiente forma: Abrir Eclipse y en el menu buscar Archivo>>Importar...>>Maven>>Proyecto existente Maven, dar click en siguiente y seleccionar la ruta en donde se encuentra el archivo pom.xml del proyecto descargado, dar check al registro que aparece y click en finalizar.

5.Cuando se cargue el árbol en el explorador del proyecto identificar si la carpeta JRE System Library se encuentra en la versión [JavaSE-1.8], si se encuentra en una versión anterior se debe dar click derecho sobre la carpeta y en propiedades seleccionar el ambiente de ejecución la versión [JavaSE 1.8].

6.Garantizar conexión con privilegios a internet para la descarga de las librerías que se realizara de forma automática al importar el proyecto y validar la instalación del componente TestNG que se encuentra en el marketplace de eclipse.

7. Descargar los drivers de conexión a los exploradores a una carpeta en la maquina y configurar su destino modificando en la clase del proyecto Parameters.java, que se encuentra en la carpeta src/main/java, paquete Parameters, en la variable PathDriver dejando la ruta como un string, tomar de ejemplo la que se encuentra configurada en el proyecto.

Ejecución:

1.Para la ejecución de los casos de prueba se debe identificar en el árbol del proyecto en la carpeta src/test/java el archivo testng.xml, sobre este dar click derecho, opción ejecutar como.. y finalmente seleccionar TestNGSuite, con esto se iniciara la ejecución de los casos de prueba.

2.Para modificar el explorador desde el cual se quiere ejecutar es necesario modificar en la clase Parameters.java la variable browser indicando la sigla del nombre del explorador. 
Siglas IE:InternetExplorer; CH:Chrome; FF:FireFox; ED:Edge. 
Se debe garantizar la descarga en la carpeta de drivers el respectivo controlador para el explorador configurado.

3.Al finalizar la ejecución se puede consultar el log de resultados en la ruta del proyecto carpeta test-output/emailable-report.html.

4.Por ultimo se deja documentación técnica del proyecto con el la generación de javadocs en la carpeta del proyecto doc/index.html.

______________________________________________________________________________

Solución: Escribir un escenario de login usando gherkin.

Feature: Se desea validar la funcionalidad de ingreso a un portal web que requiere de autenticación previa. 

Scenario: Ingreso al portal con credenciales validas.
	Given Yo abro un explorador
	And Yo ingreso la URL del portal web
  	And Yo diligencio un nombre de usuario existente
  	And  Yo diligencio una contraseña valida
  	When Yo doy click en el botón Ingresar
  	Then Yo tengo acceso al portal web
	And Yo puedo ver el menú del portal.

Scenario: Denegación ingreso al portal con credenciales invalidas.
	Given Yo abro un explorador
	And Yo ingreso la URL del portal web
  	And Yo diligencio un nombre de usuario existente.
  	And Yo diligencio una contraseña invalida.
  	When Yo doy click en el botón Ingresar
  	Then Yo no tengo acceso al portal web.
	And Yo puedo ver “Credenciales invalidas, Revise sus datos”