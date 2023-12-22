import java.io.*;
import java.net.*;
import java.util.*;

// CLASE PRINCIPAL PARA EL SERVIDOR TCP
public class examen5_8 {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(5000);
            while (true) {
                Socket s = servidor.accept();
                Hilo h = new Hilo(s);
                h.start();
            }
        } catch (UnknownHostException e) {
            System.out.println("Error al conectar con el host");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

// CLASE PARA EL CÓDIGO DE LOS HILOS (en el mismo archivo .java)
class Hilo extends Thread {
    Socket cliente;
    public static final String FICHERO = "refranero.txt";
    private static List<String> lines = new ArrayList<>();

    public Hilo(Socket s) {
        cliente = s;
    }

    public void run() {
        try {
            Scanner input = new Scanner(cliente.getInputStream());
            PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);

            // Preguntamos el número de línea al cliente
            output.println("Bienvenido! Indique un número de refrán (1-586):");
            int numero = input.nextInt();

            // Comprobamos que se encuentra dentro del rango indicado
            if (numero < 1 || numero > 586) {
                System.out.println("Número de línea inválido.");
                System.exit(0);
            }

            // Guardamos las líneas del fichero en un array
            Scanner fileScanner = new Scanner(new File(FICHERO));
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }

            // Enviamos la línea correspondiente al cliente
            output.println(lines.get(numero - 1));

            // Mensaje en consola para indicar que ha finalizado la conexión
            System.out.println("Servicio finalizado.");

            // Cerramos el Scanner y el PrintWriter
            input.close();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Error: has de introducir un numero.");
        } catch (UnknownHostException e) {
            System.out.println("Error al conectar con el host");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

/*
Enunciado:
Crea un servidor TCP multihilo en Java que escuche en el puerto 5000. Inicialmente deberá
guardar el contenido del fichero “refranero.txt” en memoria apoyándose en el fichero
“Ejemplo_lectura.java” ofrecido como ejemplo. Después, para cada nuevo cliente que se
conecte, las operaciones a realizar son:

    1. Enviar un mensaje de bienvenida al cliente "Bienvenido! Indique un número de refrán
    (1-586):”
    2. El cliente enviará una sola línea conteniendo un número. Recuerde que los objetos
    de la clase Scanner disponen del método “nextInt()” para leer enteros (pero existen otras
    vías igualmente válidas).
    3. El servidor buscará en memoria el refrán correspondiente a la línea indicada por el
    cliente, se la envía como respuesta, y cierra la conexión.
    4. En caso de que el número enviado no corresponda al rango indicado, el servidor
    enviará al cliente el mensaje "Número de línea inválido."
    5. En caso de que el cliente no envíe un número sino otro contenido, se generará una
    excepción que deberá ser capturada.
    6. Para depurar la aplicación usar el comando: “nc localhost 5000”. Se recomienda
    además usar la consola de salida del servidor para depuración, escribiendo mensajes de
    cliente conectado/desconectado que correspondan, entre otros (opcional).


Ejemplo de salida en consola:
----------------------------------------------------
Bienvenido! Indique un número de refrán (1-586):
6
- A caballo regalado no le mires el diente.
----------------------------------------------------
*/
