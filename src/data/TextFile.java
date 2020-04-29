package data;

import main.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Class which interprets text files containing dialogues + branch instructions.
 */
public class TextFile {
    private Scanner scanner;
    HashMap<String, String> speakerAcronyms;
    File txt;

    public TextFile (File txt, HashMap<String, String> speakerAcronyms) {
        this.txt = txt;
        this.speakerAcronyms = speakerAcronyms;
        try {
            this.scanner = new Scanner(txt);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        }
    }

    /**
     * Go through the file line by line.
     */
    public void processFile() {
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            // Print in format NAME: message if [NAME] message is used in txt
            if (currLine.startsWith("[")) {
                int endIdx = currLine.indexOf(']');
                String speaker = currLine.substring(1, endIdx);
                speaker = isAcronym(speaker);
                String message = currLine.substring(endIdx+1).trim();
                Ui.printReply(speaker + ": " + message);
            } else {
                Ui.printReply(currLine);
            }
            String response = Ui.getResponse();
        }
    }

    /**
     * Checks if the speaker name is an acronym.
     * @param speaker name
     * @return the actual speaker name if input was an acronym
     */
    private String isAcronym(String speaker) {
        // search for the key in the hash map
        String speakerName = speakerAcronyms.get(speaker);
        return speakerName == null ? speaker : speakerName;
    }
}
