package com.yogusoft.consoleUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

// Clase para obtener valores de entrada
public class ConsoleInput {
    // Metodo para obtener cadenas de texto
    public static String getString() {
        Scanner leer = new Scanner(System.in);
        boolean err = true;
        String x = "";

        do {
            try {
                x = leer.nextLine();

                if (x.equals("")) {
                    System.out.println("Error, has introducido una cadena vacia");
                    System.out.println("Vuelva a intentarlo: ");
                } else {
                    err = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, no has introducido una cadena " + e);
                System.out.println("Vuelva a intentarlo: ");
                leer.nextLine();
            }
        } while (err);

        return x;
    }
}
