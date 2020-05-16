package fileprocessing;

/**
 * Allows displaying multiple lines at once
 */
public class MultiLine {
    private String currLine;
    private int numToDisplay;

    public MultiLine(String currLine) {
        this.currLine = currLine;
        findNumToDisplay();
    }

    private void findNumToDisplay() {
        int endIdx = currLine.indexOf('>');
        String numToDisplayString = currLine.substring(1, endIdx);
        numToDisplay = Integer.parseInt(numToDisplayString);
    }

    public int getNumToDisplay() {
        return numToDisplay;
    }
}
