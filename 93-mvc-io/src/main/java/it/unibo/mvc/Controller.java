package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * 
     * @param nexString next string to print
     */
    void setNextString(String nextString);

    /**
     * 
     * @return next string to print
    */
    String getNextString();

    /**
     * 
     * @return the history of the string printed
     */
    List<String> getHistory();

    /**
     * 
     */
    void printCurrentString();
}
