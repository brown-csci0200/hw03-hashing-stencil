package sol;

import src.IGrep;
import java.util.Set;
import java.util.HashSet;

/**
 * Class for finding the line numbers where a given word appears in a file.
 */
public class Grep implements IGrep {

    /**
     * Constructor for grep
     * @param filename the name of the file
     */
    public Grep(String filename) {

    }

    /**
     * The lookup function
     * @param word - the word to look up
     *
     * @return The set of all line numbers where the word appears
     */
    @Override
    public Set<Integer> lookup(String word) {
        return null;
    }

    // Feel free to write additional helper methods :)

    /**
     * This is the main method. It takes in arguments (i.e. a file name and a word(s))
     * and calls your implementation of Grep.
     * @param args - file name and word(s) you are looking up
     */
    public static void main(String[] args) {

    }

}
