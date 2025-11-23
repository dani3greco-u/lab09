package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic implementation of Controller.
 */
public final class SimpleController implements Controller {

    private final List<String> history;
    private String nextString;

    /**
     * Constructor for SimpleController.
     */
    public SimpleController() {
        this.history = new LinkedList<>();
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalArgumentException("string is unset");
        } else {
            System.out.println(this.nextString); //NOPMD
            history.add(this.nextString);
        }
    }

    @Override
    public void setNextString(final String nextString) {
        if (nextString == null) {
            throw new IllegalArgumentException("nextString is null");
        } else {
            this.nextString = nextString;
        }
    }

}
