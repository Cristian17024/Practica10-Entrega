import java.io.*;
import java.util.*;

public class Persistencia {

    private final String rutaArchivo;

    public Persistencia(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Método para cargar los elementos desde el archivo y agregarlos al escenario
    public void cargarElementos(Escenario escenario) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar la línea por comas para obtener los diferentes datos
                String[] partes = linea.split(",");

                String tipo = partes[0];
                // El segundo y tercero son las coordenadas (renglón y columna)
                int renglon = Integer.parseInt(partes[1]);
                int columna = Integer.parseInt(partes[2]);
                // Crear un objeto Posicion con las coordenadas
                Posicion pos = new Posicion(renglon, columna);

                switch (tipo) {
                    case "Terricola":
                        escenario.addElemento(new Terricola("T", escenario, pos));
                        break;
                    case "Extraterrestre":
                        escenario.addElemento(new Extraterrestre("E", escenario, pos));
                        break;
                    case "Roca":
                        escenario.addElemento(new Roca(escenario, pos));
                        break;
                    case "Bomba":
                        int radio = Integer.parseInt(partes[3]);
                        escenario.addElemento(new Bomba(escenario, pos, radio));
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
    }

    // Método para guardar en el archivo el estado actual de todos los elementos del escenario
    public void guardarElementos(Escenario escenario) {
        // Abrir el archivo para escritura dentro de try-with-resources
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Recorrer cada elemento en el escenario
            for (Elemento e : escenario.getElementos()) {
                // Obtener la posición del elemento
                Posicion p = e.getPosicion();
                // Obtener el nombre simple de la clase (ejemplo: "Terricola", "Bomba")
                String tipo = e.getClass().getSimpleName();

                // Si el elemento es una bomba, escribir también el radio
                if (e instanceof Bomba) {
                    Bomba b = (Bomba) e;
                    pw.println("Bomba," + p.getRenglon() + "," + p.getColumna() + "," + b.getRadio());
                } else {
                    // Para los demás elementos solo escribir tipo y coordenadas
                    pw.println(tipo + "," + p.getRenglon() + "," + p.getColumna());
                }
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }
}
