package branching;

import data.TextFile;
import main.Ui;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Controls branching which is short (a few lines instead of entire chapters).
 */
public class ShortBranch extends TextFile {
    private String currLine;

    public ShortBranch(File txt, HashMap<String, String> speakerAcronyms, String currLine) {
        super(txt, speakerAcronyms);
        this.currLine = currLine;
        processBranch();
    }

    private void processBranch() {
        int endIdx = currLine.indexOf(')');
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
    }

    public Scanner updateScanner() {
        return scanner;
    }
}
