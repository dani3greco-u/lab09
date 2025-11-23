package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * @param nextString next string to print
     */
    void setNextString(String nextString);

    /**
     * @return next string to print
     */
    String getNextString();

    /**
     * @return the history of the string printed
     */
    List<String> getHistory();

    /**
     * Print the current String.
     */
    void printCurrentString();
}
