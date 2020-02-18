package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
public interface Saveable {
    /* persistence implementation adapted from Teller Sample*/

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
