# **Proyecto de Gestión de Stock de Productos**

## Este proyecto tiene como objetivo la implementación de un sistema de gestión de stock de productos utilizando las estructuras de datos 
## creadas por el equipo docente de la UNED. La gestión del Stock la realizaremos mediante Secuencias o Árboles.

### **Instrucciones de ejecución**:

**1.** Creación del archivo .jar:
   - Compila el código fuente y crea el archivo .jar del proyecto.

**2.** Ejecución del archivo .jar:
   - Abre una terminal o línea de comandos.
   - Navega hasta la ubicación del archivo .jar creado en el paso anterior.
   - Ejecuta el siguiente comando:

       java -jar nombre-del-archivo.jar [parámetros]

   Reemplaza "nombre-del-archivo.jar" por el nombre real del archivo .jar generado.

   Parámetros de entrada:
   - Elige una de las siguientes opciones para indicar la estructura a utilizar: **SEQUENCE** o **TREE**.
   - Proporciona el nombre del archivo de entrada de datos con las instrucciones a realizar.
   - Proporciona el nombre del archivo de salida donde se registrarán las operaciones realizadas.

   Ejemplo de ejecución:

       java -jar gestion-stock.jar SEQUENCE datos.txt salida.txt

   Asegúrate de tener los archivos de entrada y salida en el directorio correcto.

Instrucciones del archivo de entrada:

- Cada línea del archivo de entrada debe contener una instrucción en el siguiente formato:

  [operación] [parámetro1] [parámetro2]

  Operaciones disponibles:
  - "compra": busca el producto. Si existe, actualiza las unidades; si no existe, crea un nuevo producto.
  - "venta": busca el producto. Si existe, actualiza las unidades; si no existe o se intenta comprar más unidades de las existentes, muestra un mensaje de error.
  - "listado": muestra una lista de productos. Si se proporciona un string como parámetro, lista los productos que comienzan con ese prefijo.

  Ejemplo de archivo de entrada:

    compra producto1 136
    venta producto1 43
    listado ig

- Guarda las instrucciones en un archivo de texto y asegúrate de proporcionar su nombre como parámetro en la ejecución.

Formato del archivo de salida:

- Después de ejecutar el programa, se generará un archivo de salida con las operaciones realizadas y los resultados obtenidos.

## **Autores**:
  Este proyecto ha sido desarrollado por Gustavo Acevedo Alfonso

## **Versión y fecha**
  Versión 1.0 - 17.05.2023
