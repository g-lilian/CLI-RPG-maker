package fileprocessing;

import javafx.util.Pair;
import main.Ui;

import java.io.File;
import java.util.List;

public class ChapterSelectMenu {
    private File chaptersFile;

    public ChapterSelectMenu(File chaptersFile) {
        this.chaptersFile = chaptersFile;
    }

    public String runChapterSelect() {
        // load and print the chapter selection
        Chapters chaptersObj = new Chapters(chaptersFile);
        List<Pair<String, String>> chapters = chaptersObj.getChapters();
        Ui.printChapterSelectMenu(chapters);

        boolean isValidChapter = false;
        int chapterSelection = -1;
        // check if the chapter number is valid
        while (!isValidChapter) {
            try {
                chapterSelection = Integer.parseInt(Ui.getChapterSelection());
            } catch (NumberFormatException e) {
                chapterSelection = -1;
            }
            if (chapterSelection > 0 && chapterSelection <= chapters.size()) {
                isValidChapter = true;
            } else {
                Ui.printInvalidChapterError();
            }
        }

        // if number is valid, get the chapter file name
        Pair<String, String> chapter = chapters.get(chapterSelection-1);
        String chapterName = chapter.getKey();
        String chapterFileName = chapter.getValue();
        Ui.printChapterLoadMessage(chapterName);

        return chapterFileName;
    }
}
