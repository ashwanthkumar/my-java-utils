package in.ashwanthkumar.utils.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class IO {
    /**
     * Read the entire file as a String
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static String fromFile(String source) throws IOException {
        return fromFile(new File(source), false);
    }

    /**
     * Read the entire file as a string.
     *
     * @param source
     * @param hasNewLine Should we maintain the \n in the final string?
     * @return
     * @throws IOException
     */
    public static String fromFile(String source, boolean hasNewLine) throws IOException {
        return fromFile(new File(source), hasNewLine);
    }

    /**
     * Read the entire file as a string
     *
     * @param input
     * @param hasNewLine
     * @return
     * @throws IOException
     */
    public static String fromFile(File input, boolean hasNewLine) throws IOException {
        String EOL = "";
        if (hasNewLine) {
            EOL = "\n";
        }

        StringBuilder contents = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        while ((line = reader.readLine()) != null) {
            contents.append(line);
            contents.append(EOL);
        }
        return contents.toString();
    }

    /**
     * Read the file as an Iterator&lt;String&gt; with each line as an item.
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static Iterator<String> linesFromFile(String source) throws IOException {
        return linesFromFile(new File(source));
    }

    /**
     * Read the file as an Iterator&lt;String&gt; with each line as an item.
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static Iterator<String> linesFromFile(File source) throws IOException {
        return new FileLineIterator(source);
    }

    /**
     * Read the file as an Iterator&lt;String&gt; with each line as an item.
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static Iterator<String> linesFromInputStream(InputStream source) throws IOException {
        return new FileLineIterator(source);
    }
}
