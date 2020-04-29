package main;

import data.FileReader;
import data.SpeakerAcronyms;
import data.TextFile;

import java.io.File;

public class Main {
    private static final String MAINTXT = "./dialogues/arc1.txt";
    private static final String ACRONYMS = "./otherdata/acronyms.txt";

    public static void main(String[] args) {
        Ui.printStartMessage();

        File dialogueFile = FileReader.loadFile(MAINTXT);
        File acronymsFile = FileReader.loadFile(ACRONYMS);
        SpeakerAcronyms acronyms = new SpeakerAcronyms(acronymsFile);
        TextFile currTxt = new TextFile(dialogueFile, acronyms.getAcronyms());
        currTxt.processFile();
    }
}
