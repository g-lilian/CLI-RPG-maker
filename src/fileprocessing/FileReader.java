package fileprocessing;

import java.io.File;

/**
 * Loads text files
 */
public class FileReader {

    public static File loadFile(String filepath) {
        File txt = new File (filepath);
        return txt;
    }
}
