package main;

import java.util.Scanner;

/**
 * Get user response and print the text
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printReply(String text) {
        System.out.print(text);
    }

    public static String getResponse() {
        String response = "";
        response = scanner.nextLine();
        return response;
    }

    public static void printStartMessage() {
        String startMessage = "The story begins now." + System.lineSeparator();
        System.out.println(startMessage);
    }

    public static void lb() {
        System.out.print(System.lineSeparator());
    }

    public static void printFileNotFoundError() {
        System.out.println("File not found.");
    }
}
