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
                    System.out.println("Error, you have entered an empty string");
                    System.out.println("Please try again: ");
                } else {
                    err = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, you haven't entered a string " + e);
                System.out.println("Please try again: ");
                leer.nextLine();
            }
        } while (err);

        return x;
    }
}
