package in.ashwanthkumar.utils.io;

import in.ashwanthkumar.utils.collections.Iterables;
import org.junit.Test;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IOTest {

    @Test
    public void testLinesFromFile() throws Exception {
        Iterator<String> lines = IO.linesFromFile(new File(getClass().getResource("/test.txt").toURI()));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line1"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line2"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line3"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is(""));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line4"));
        assertThat(lines.hasNext(), is(false));
    }

    @Test
    public void testLinesFromInputStream() throws Exception {
        Iterator<String> lines = IO.linesFromInputStream(getClass().getResourceAsStream("/test.txt"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line1"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line2"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line3"));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is(""));
        assertThat(lines.hasNext(), is(true));
        assertThat(lines.next(), is("line4"));
        assertThat(lines.hasNext(), is(false));
    }
}