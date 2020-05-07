package data;

import branching.ShortBranch;
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
    private File txt;

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
        int displayBuffer = 1; // num of lines to display with each Enter

        while (scanner.hasNext()) {
            processLines(displayBuffer);

            displayBuffer = 1;
            String response = Ui.getResponse(); // enter to print next line(s)
        }
    }

    /**
     * Processes X lines of text, where X = displayBuffer
     */
    public void processLines(int displayBuffer) {
        while (displayBuffer > 0) {
            String currLine = scanner.nextLine();
            char firstChar = currLine.length() != 0 ? currLine.charAt(0) : '0';

            switch (firstChar) {
            case '[': // default speech with character name
                // Print in format NAME: message if [NAME] message is used in txt
                int endIdx = currLine.indexOf(']');
                String speaker = currLine.substring(1, endIdx);
                speaker = isAcronym(speaker);
                String message = currLine.substring(endIdx + 1).trim();
                Ui.printReply(speaker + ": " + message);
                break;
            case '(': // a command word/phrase
                endIdx = currLine.indexOf(')');
                String keyword = currLine.substring(1, endIdx);
                parseKeyword(keyword);
                break;
            case '<': // multi line
                MultiLine multiline = new MultiLine(currLine);
                displayBuffer = multiline.getNumToDisplay();
                continue;
            default: // no special format, just print the line
                Ui.printReply(currLine);
            }

            if (displayBuffer > 1) Ui.lb(); // add line break for multiline
            displayBuffer--;
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

    /**
     * Parse the keyword surrounded by ().
     * @param keyword to execute corresponding instructions
     */
    private void parseKeyword(String keyword) {
        switch (keyword) {
        case "branch":
            //ShortBranch shortbranch = new ShortBranch()
            break;
        default:
        }
    }
}
