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
        String response = scanner.nextLine();
        return response;
    }

    /**
     * Checks if the player response corresponds to the valid options.
     * @param response player response
     * @param options list of valid options
     * @return -1 if response is invalid, otherwise returns index of response
     */
    public static int checkPlayerResponse(String response, String[] options) {
        for (int i=0; i<options.length; i++) {
            if (response.equals(options[i])) return i;
        }
        return -1;
    }

    public static String promptTryAgain() {
        System.out.println("Invalid response. Try again.");
        String response = Ui.getResponse();
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
