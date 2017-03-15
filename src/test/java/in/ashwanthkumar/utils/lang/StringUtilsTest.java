package in.ashwanthkumar.utils.lang;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void shouldCheckIfEmpty() {
        assertThat(StringUtils.isEmpty(""), is(true));
        assertThat(StringUtils.isEmpty(null), is(true));
        assertThat(StringUtils.isEmpty("foo-bar-baz"), is(false));
    }

    @Test
    public void shouldCheckIfNotEmpty() {
        assertThat(StringUtils.isNotEmpty(""), is(false));
        assertThat(StringUtils.isNotEmpty(null), is(false));
        assertThat(StringUtils.isNotEmpty("foo-bar-baz"), is(true));
    }

    @Test
    public void shouldCheckIfBlank() {
        assertThat(StringUtils.isBlank(null), is(true));
        assertThat(StringUtils.isBlank("                  "), is(true));
        assertThat(StringUtils.isBlank("                  f"), is(false));
        assertThat(StringUtils.isBlank("f                  "), is(false));
    }

    @Test
    public void shouldCheckIfNotBlank() {
        assertThat(StringUtils.isNotBlank(null), is(false));
        assertThat(StringUtils.isNotBlank("                  "), is(false));
        assertThat(StringUtils.isNotBlank("                  f"), is(true));
        assertThat(StringUtils.isNotBlank("f                  "), is(true));
    }

    @Test
    public void shouldCheckIfStartsWith() {
        assertThat(StringUtils.startsWith("foobarbaz", "foo"), is(true));
        assertThat(StringUtils.startsWith("foobarbaz", "bar"), is(false));
        assertThat(StringUtils.startsWith(null, "bar"), is(false));
    }

    public void shouldCheckIfEndsWith() {
        assertThat(StringUtils.endsWith("foobarbaz", "baz"), is(true));
        assertThat(StringUtils.endsWith("foobarbaz", "foo"), is(false));
        assertThat(StringUtils.endsWith(null, "bar"), is(false));
    }

    @Test
    public void shouldReturnSizeOfString() {
        assertThat(StringUtils.size("input"), is(5));
        assertThat(StringUtils.size(null), is(0));
    }

    @Test
    public void shouldTrimTheString() {
        assertThat(StringUtils.trim("input   "), is("input"));
        assertThat(StringUtils.trim("    input"), is("input"));
        assertThat(StringUtils.trim("    input           "), is("input"));
        assertThat(StringUtils.trim("input"), is("input"));
        assertThat(StringUtils.trim(null), is(""));
    }
}
