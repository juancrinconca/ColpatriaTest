4.Escribir un escenario de login usando gherkin.

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