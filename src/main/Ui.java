package main;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
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
        return response;
    }

    public static void printStartMessage() {
        String startMessage = "Welcome to CLI RPG Maker." + System.lineSeparator();
        System.out.println(startMessage);
    }


    /** CHAPTER SELECTION */
    public static void printChapterSelectMenu(List<Pair<String, String>> chapters) {
        int chapterNum = 1;
        System.out.println("Chapter selection: ");
        for (Pair<String, String> chapter : chapters) {
            String chapterName = chapter.getKey();
            System.out.println(chapterNum + " - " + chapterName);
            chapterNum++;
        }
        lb();
    }

    public static String getChapterSelection() {
        System.out.println("Select a chapter to start from: ");
        return getResponse();
    }

    public static void printInvalidChapterError() {
        System.out.println("Invalid chapter. Please try again.");
    }

    public static void printChapterLoadMessage(String chapterName) {
        System.out.println("Loading chapter: " + chapterName + "...");
    }


    /** OTHERS */
    public static void lb() {
        System.out.print(System.lineSeparator());
    }

    public static void printFileNotFoundError() {
        System.out.println("File not found.");
    }
}
