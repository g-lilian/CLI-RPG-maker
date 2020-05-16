package fileprocessing;

import javafx.util.Pair;
import main.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chapters {
    List<Pair<String, String>> chapters = new ArrayList<>();
    private Scanner scanner;
    File txt;

    public Chapters (File txt) {
        this.txt = txt;
        try {
            this.scanner = new Scanner(txt);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        }
    }

    /**
     * @return list of pairs matching chapter names to chapter files
     */
    public List<Pair<String, String>> getChapters() {
        loadChapters();
        return chapters;
    }

    /**
     * Loads chapters into hash map.
     */
    private void loadChapters() {
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            int sepIdx = currLine.indexOf(':');
            String chapterName = currLine.substring(0, sepIdx);
            String chapterFileName = currLine.substring(sepIdx+1);
            Pair<String, String> chapter = new Pair<>(chapterName, chapterFileName);
            chapters.add(chapter);
        }
    }
}
