package branching;

import data.TextFile;
import main.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Controls branching which is short (a few lines instead of entire chapters).
 */
public class ShortBranch extends TextFile {
    private String currLine;
    private Scanner scanner;
    private int lineCount;
    private int branchLineCount = 0;

    public ShortBranch(File txt, HashMap<String, String> speakerAcronyms, String currLine, int lineCount) {
        super(txt, speakerAcronyms);
        this.currLine = currLine;
        this.lineCount = lineCount;
        try {
            this.scanner = new Scanner(txt);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        }
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
            // first get to the correct line of the file
            scanner = scanLines(lineCount, scanner);

            currLine = scanner.nextLine();
            branchLineCount++;
            branchName = currLine.trim();
        }

        // process lines as usual until endbranch is read
        boolean endBranch;
        while (true) {
            endBranch = processLines(1, scanner, lineCount + branchLineCount);
            if (endBranch) break;
            Ui.getResponse(); // enter to print next line(s)
        }

        // skip over all irrelevant lines
        int branchesRemaining = branches.length - responseIdx - 1;
        while (branchesRemaining > 0) {
            currLine = scanner.nextLine();
            branchLineCount++;
            if (currLine.equals("endbranch")) branchesRemaining--;
        }
    }

    public int getBranchLineCount() { return branchLineCount; }
}
