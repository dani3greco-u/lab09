package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    
    private static final String PATH = System.getProperty("user.home");
    private static final String DAFUALT_FILE = "output.txt";

    private File currentFile = new File(PATH + File.separator + DAFUALT_FILE);

    /**
     * Set a new currentFile.
     * 
     * @param f
     * @throws IllegalArgumentException
     */
    void setCurrentFile(File f) throws IllegalArgumentException {
        File p = f.getParentFile();
        if(p.exists()) {
            this.currentFile = f;
        }
        else {
            throw new IllegalArgumentException("Not found " + f.getPath());
        }
    }

    /**
     * Return the currentFile.
     * 
     * @return the currentFile
     */
    File getCurrenFile() {
        return this.currentFile;
    }

    /**
     * Return the path of currentFile.
     * 
     * @return the path of currentFile
     */
    String getCurrentFilePath() {
        return this.currentFile.getPath();
    }

    /**
     * Write on currentFile.
     * 
     * @param input
     * @throws IOException
     */
    void writeOnCurrentFile(String input) throws IOException{
        try (PrintStream ps = new PrintStream(PATH, StandardCharsets.UTF_8)) {
            ps.print(input);
        }
    }
    
}
