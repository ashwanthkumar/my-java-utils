package in.ashwanthkumar.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
     * Read the file as a List&lt;String&gt; with each line as an item.
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static List<String> linesFromFile(String source) throws IOException {
        return linesFromFile(new File(source));
    }

    public static List<String> linesFromFile(File source) throws IOException {
        ArrayList<String> contents = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(source));
        String line;
        while ((line = reader.readLine()) != null) {
            contents.add(line);
        }

        return contents;
    }
}
