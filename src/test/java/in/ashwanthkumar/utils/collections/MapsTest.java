package in.ashwanthkumar.utils.collections;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MapsTest {
    @Test
    public void shouldCreateMapFromKeyValue() {
        Map<String, String> map = Maps.of("a", "b");
        assertThat(map, hasEntry("a", "b"));
        assertThat(map.size(), is(1));
    }

    @Test
    public void shouldCreateMapFrom2KVs() {
        Map<String, String> map = Maps.of("a", "b", "c", "d");
        assertThat(map, hasEntry("a", "b"));
        assertThat(map, hasEntry("c", "d"));
        assertThat(map.size(), is(2));
    }

    @Test
    public void shouldCreateMapFrom3KVs() {
        Map<String, String> map = Maps.of("a", "b", "c", "d", "e", "f");
        assertThat(map, hasEntry("a", "b"));
        assertThat(map, hasEntry("c", "d"));
        assertThat(map, hasEntry("e", "f"));
        assertThat(map.size(), is(3));
    }

    @Test
    public void shouldCreateMapFrom4KVs() {
        Map<String, String> map = Maps.of("a", "b", "c", "d", "e", "f", "g", "h");
        assertThat(map, hasEntry("a", "b"));
        assertThat(map, hasEntry("c", "d"));
        assertThat(map, hasEntry("e", "f"));
        assertThat(map, hasEntry("g", "h"));
        assertThat(map.size(), is(4));
    }

    @Test
    public void shouldCreateMapFrom5KVs() {
        Map<String, String> map = Maps.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        assertThat(map, hasEntry("a", "b"));
        assertThat(map, hasEntry("c", "d"));
        assertThat(map, hasEntry("e", "f"));
        assertThat(map, hasEntry("g", "h"));
        assertThat(map, hasEntry("i", "j"));
        assertThat(map.size(), is(5));
    }

    @Test
    public void shouldCreateMapFrom6KVs() {
        Map<String, String> map = Maps.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l");
        assertThat(map, hasEntry("a", "b"));
        assertThat(map, hasEntry("c", "d"));
        assertThat(map, hasEntry("e", "f"));
        assertThat(map, hasEntry("g", "h"));
        assertThat(map, hasEntry("i", "j"));
        assertThat(map, hasEntry("k", "l"));
        assertThat(map.size(), is(6));
    }

    @Test
    public void shouldGetOrElseDefaultValueFromMap() {
        Map<String, Integer> map = Maps.of("a", 1, "b", 2);
        assertThat(Maps.getOrElse(map, "c", 3), is(3));
        assertThat(Maps.getOrElse(map, "a", 3), is(1));
        assertThat(Maps.getOrElse(map, "b", 3), is(2));
    }


    @Test
    public void shouldCreateMapUsingBuilder() {
        Map<String, String> map = Maps.<String, String>builder()
                .put("a", "b")
                .put("c", "d")
                .value();

        assertThat(map, hasEntry("a", "b"));
        assertThat(map, hasEntry("c", "d"));
        assertThat(map.size(), is(2));
    }

}
