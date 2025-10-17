/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegoinvertirfrases;

/**
 *
 * @author IFSul
 */
import java.util.*;

public class JuegoInvertirFrases {
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> frasesOriginales = new ArrayList<>();
    private static Deque<String> pilaFrasesInvertidas = new ArrayDeque<>();
    private static final int TAMANO_MAXIMO_PILA = 5;

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Invertir nueva frase (orden de palabras)");
            System.out.println("2 - Mostrar frases originales (orden alfabetico)");
            System.out.println("3 - Deshacer ultima inversion");
            System.out.println("4 - Invertir letras de cada palabra");
            System.out.println("5 - Salir");
            System.out.print("Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    invertirNuevaFrase();
                    break;
                case 2:
                    mostrarFrasesOriginales();
                    break;
                case 3:
                    deshacerUltimaInversion();
                    break;
                case 4:
                    invertirLetrasPorPalabra();
                    break;
                case 5:
                    System.out.println("¡Gracias por jugar! Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        } while (opcion != 5);
    }

    /**
     * Invierte el orden de las palabras de una frase ingresada.
     * Guarda la frase original y la invertida.
     */
    private static void invertirNuevaFrase() {
        System.out.print("Ingrese una frase: ");
        String frase = scanner.nextLine();
        frasesOriginales.add(frase);

        String[] palabras = frase.split(" ");
        Deque<String> pilaPalabras = new ArrayDeque<>();

        // Empujar palabras en la pila
        for (String palabra : palabras) {
            pilaPalabras.push(palabra);
        }

        // Construir frase con el orden invertido de palabras
        StringBuilder fraseInvertida = new StringBuilder();
        while (!pilaPalabras.isEmpty()) {
            fraseInvertida.append(pilaPalabras.pop()).append(" ");
        }

        String invertida = fraseInvertida.toString().trim();

        // Guardar en pila de frases invertidas con límite
        if (pilaFrasesInvertidas.size() == TAMANO_MAXIMO_PILA) {
            pilaFrasesInvertidas.removeLast(); // elimina la más antigua
        }
        pilaFrasesInvertidas.push(invertida);

        System.out.println("Frase original: " + frase);
        System.out.println("Frase invertida (orden de palabras): " + invertida);
    }

    /**
     * Muestra las frases originales almacenadas en orden alfabético.
     */
    private static void mostrarFrasesOriginales() {
        if (frasesOriginales.isEmpty()) {
            System.out.println("No hay frases almacenadas.");
            return;
        }

        List<String> frasesOrdenadas = new ArrayList<>(frasesOriginales);
        Collections.sort(frasesOrdenadas);

        System.out.println("Frases originales almacenadas (orden alfabetico):");
        for (String frase : frasesOrdenadas) {
            System.out.println("- " + frase);
        }
    }

    /**
     * Deshace la última inversión eliminando la última frase invertida de la pila.
     */
    private static void deshacerUltimaInversion() {
        if (pilaFrasesInvertidas.isEmpty()) {
            System.out.println("No hay inversiones para deshacer.");
            return;
        }

        String ultimaInvertida = pilaFrasesInvertidas.pop();
        System.out.println("Ultima inversiOn deshecha: " + ultimaInvertida);
    }

    /**
     * Invierte las letras de cada palabra de una frase sin cambiar el orden de las palabras.
     * Guarda la frase original y la invertida.
     */
    private static void invertirLetrasPorPalabra() {
        System.out.print("Ingrese una frase: ");
        String frase = scanner.nextLine();
        frasesOriginales.add(frase);

        String[] palabras = frase.split(" ");
        StringBuilder fraseInvertida = new StringBuilder();

        for (String palabra : palabras) {
            fraseInvertida.append(new StringBuilder(palabra).reverse().toString()).append(" ");
        }

        String invertida = fraseInvertida.toString().trim();

        // Guardar en pila de frases invertidas con límite
        if (pilaFrasesInvertidas.size() == TAMANO_MAXIMO_PILA) {
            pilaFrasesInvertidas.removeLast();
        }
        pilaFrasesInvertidas.push(invertida);

        System.out.println("Frase original: " + frase);
        System.out.println("Frase invertida (letras por palabra): " + invertida);
    }
}
