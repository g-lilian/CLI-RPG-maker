package main;

import fileprocessing.ChapterSelectMenu;
import fileprocessing.FileReader;
import fileprocessing.SpeakerAcronyms;
import fileprocessing.TextFile;

import java.io.File;

public class Main {
    private static final String MAINTXT = "./dialogues/arc1.1.txt";
    private static final String ACRONYMS = "./otherdata/acronyms.txt";
    private static final String CHAPTERS = "./otherdata/chapters.txt";

    public static void main(String[] args) {
        Ui.printStartMessage();

        File dialogueFile = FileReader.loadFile(MAINTXT);
        File acronymsFile = FileReader.loadFile(ACRONYMS);
        File chaptersFile = FileReader.loadFile(CHAPTERS);

        ChapterSelectMenu chapterSelection = new ChapterSelectMenu(chaptersFile);
        String chapterToLoad = chapterSelection.runChapterSelect();
        SpeakerAcronyms acronyms = new SpeakerAcronyms(acronymsFile);

        File startingChapterFile = FileReader.loadFile("./dialogues/" + chapterToLoad + ".txt");
        TextFile startingChapter = new TextFile(startingChapterFile, acronyms.getAcronyms());
        startingChapter.processFile();

        //TextFile currTxt = new TextFile(dialogueFile, acronyms.getAcronyms());
        //currTxt.processFile();
    }
}
