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
