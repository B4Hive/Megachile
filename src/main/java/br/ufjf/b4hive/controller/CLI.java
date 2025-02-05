package br.ufjf.b4hive.controller;

import java.util.Scanner;

public class CLI {

    private static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("UseSpecificCatch")
    public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para Linux/Unix/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar limpar a tela: " + e.getMessage());
        }
    }

    public static char getChar() {
        char c;
        String temp = scanner.next();
        scanner.nextLine();
        if (temp.length() < 1) {
            c = ' ';
        } else {
            c = temp.toLowerCase().charAt(0);
        }
        return c;
    }

}
