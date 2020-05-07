package branching;

import main.Ui;

import java.io.File;
import java.util.Scanner;

/**
 * Controls branching which is short (a few lines instead of entire chapters).
 */
public class ShortBranch {
    private Scanner scanner;
    private File txt;

    public ShortBranch(Scanner scanner, File txt) {
        this.scanner = scanner;
        this.txt = txt;
        processBranch();
    }

    private void processBranch() {
        // Prompt user for their choice
        String userResponse = Ui.getResponse();
    }

    public Scanner updateScanner() {
        return scanner;
    }
}
