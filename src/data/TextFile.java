package data;

import main.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class which interprets text files containing dialogues + branch instructions
 */
public class TextFile {
    private Scanner scanner;
    File txt;

    public TextFile (File txt) {
        this.txt = txt;
        try {
            this.scanner = new Scanner(txt);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        }
    }

    /**
     * Go through the file line by line
     */
    public void processFile() {
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            Ui.printReply(currLine);
            String response = Ui.getResponse();
        }
    }
}
