package in.ashwanthkumar.utils.collections;

import org.junit.Test;

import java.util.Set;

import static in.ashwanthkumar.utils.collections.Sets.isEmpty;
import static in.ashwanthkumar.utils.collections.Sets.nonEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SetsTest {
    @Test
    public void shouldCloneSet() {
        Set<Integer> setOfIntegers = Sets.of(1, 2, 3, 4, 5);
        Set<Integer> copy = Sets.copy(setOfIntegers);
        assertThat(copy, is(setOfIntegers));
    }

    @Test
    public void shouldCheckForEmpty() {
        assertTrue(nonEmpty(Sets.of(1, 2, 3, 4, 5)));
        assertTrue(isEmpty(Sets.of()));
    }

}