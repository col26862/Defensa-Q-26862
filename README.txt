Proyecto: Juego Defensa de QTZ2
Nombre César Colorado
carne 26862

Descripción
Simulador de consola para administrar una misión de defensa del satélite Quetzal-2. Usa herencia y polimorfismo para procesar módulos de vuelo, tierra y energía, y organiza la interacción con el patrón MVC.

Estructura
src/  Archivos fuente Java
bin/  Archivos compilados (.class)

Compilar desde la carpeta Juego-Defensa:
  javac -d bin src/*.java

Ejecutar:
  java -cp bin Main

El orden en que se crean los módulos en Main determina el orden de operación en cada ciclo.
