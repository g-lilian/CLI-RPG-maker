package main;

import data.FileReader;
import data.TextFile;

import java.io.File;

public class Main {
    private static final String FILEPATH = "./dialogues/arc1.txt";
    private static TextFile currTxt;

    public static void main(String[] args) {
        Ui.printStartMessage();

        File f = FileReader.loadFile(FILEPATH);
        currTxt = new TextFile(f);
        currTxt.processFile();
    }
}
