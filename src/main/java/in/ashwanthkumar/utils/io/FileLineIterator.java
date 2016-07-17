package in.ashwanthkumar.utils.io;

import java.io.*;
import java.util.Iterator;

public class FileLineIterator implements Iterator<String>, Iterable<String> {
    private BufferedReader reader;
    private String line;

    public FileLineIterator(File input) throws IOException {
        reader = new BufferedReader(new FileReader(input));
        init();
    }

    public FileLineIterator(InputStream input) throws IOException {
        reader = new BufferedReader(new InputStreamReader(input));
        init();
    }

    private void init() throws IOException {
        line = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return line != null; // null if EOF is reached
    }

    @Override
    public String next() {
        String valueToReturn = line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return valueToReturn;
    }

    @Override
    public void remove() {
        throw new RuntimeException("FileLineIterator#remove is not implemented");
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
