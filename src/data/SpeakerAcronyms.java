package data;

import main.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Processes txt file containing speaker acronyms.
 * The format should follow ACRONYM:FULLNAME without unnecessary spaces.
 */
public class SpeakerAcronyms {
    HashMap<String, String> speakerAcronyms = new HashMap<String, String>();
    private Scanner scanner;
    File txt;

    public SpeakerAcronyms (File txt) {
        this.txt = txt;
        try {
            this.scanner = new Scanner(txt);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        }
    }

    public HashMap<String, String> getAcronyms() {
        processAcronyms();
        return speakerAcronyms;
    }

    /**
     * Loads acronyms into hash map.
     */
    private void processAcronyms() {
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            int sepIdx = currLine.indexOf(':');
            String acronym = currLine.substring(0, sepIdx);
            String fullName = currLine.substring(sepIdx+1);
            speakerAcronyms.put(acronym, fullName);
        }
    }
}
