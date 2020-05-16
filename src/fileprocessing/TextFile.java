package fileprocessing;

import main.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Each instance of this class interprets a single text file containing dialogues + branch instructions.
 */
public class TextFile {
    private Scanner scanner;
    HashMap<String, String> speakerAcronyms;
    private File txt;
    private String currLine;

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
    private boolean processLines(int displayBuffer) {
        while (displayBuffer > 0 && scanner.hasNext()) {
            currLine = scanner.nextLine();
            if (currLine.equals("endbranch")) return true;
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
        // should not come here
        return false;
    }

    /**
     * Parse the keyword surrounded by ().
     * @param keyword to execute corresponding instructions
     */
    private void parseKeyword(String keyword) {
        int endIdx = currLine.indexOf(')');
        switch (keyword) {
        case "branch": // short branching
            String[] branches = currLine.substring(endIdx+1).trim().split(" ");
            String response = Ui.getResponse(); // get player branch response
            int responseIdx = Ui.checkPlayerResponse(response, branches);
            while (responseIdx == -1) { // while response is invalid
                response = Ui.promptTryAgain();
                responseIdx = Ui.checkPlayerResponse(response, branches);
            }

            // go to correct branch depending on response
            String branchName = "";
            while (!branchName.equals(response)) {
                currLine = scanner.nextLine();
                branchName = currLine.trim();
            }

            // process lines as usual until endbranch is read
            boolean endBranch;
            while (true) {
                endBranch = processLines(1);
                if (endBranch) break;
                Ui.getResponse(); // enter to print next line(s)
            }

            // skip over all irrelevant lines
            int branchesRemaining = branches.length - responseIdx - 1;
            while (branchesRemaining > 0) {
                currLine = scanner.nextLine();
                if (currLine.equals("endbranch")) branchesRemaining--;
            }
            break;
        case "goto": // long branching i.e. jump to another file
            String branchFileName = currLine.substring(endIdx+1).trim();
            File branchFile = FileReader.loadFile("./dialogues/" + branchFileName + ".txt");
            TextFile longBranch = new TextFile(branchFile, speakerAcronyms);
            longBranch.processFile();
            break;
        default:
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
