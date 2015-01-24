package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Predicate;
import org.junit.Test;

import static in.ashwanthkumar.utils.collections.Iterables.exists;
import static in.ashwanthkumar.utils.collections.Iterables.forall;
import static org.junit.Assert.*;

public class IterablesTest {

    @Test
    public void shouldSayTrueEvenIfOneElementMatchesACondition() {
        boolean exists = exists(Lists.of(1, 2, 3, 4), new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return 4 == input;
            }
        });
        assertTrue(exists);

        boolean notExists = exists(Lists.of(1, 2, 3, 4), new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input > 10;
            }
        });
        assertFalse(notExists);
    }

    @Test
    public void shouldSayTrueOnlyIfAllElementsMatchesACondition() {
        boolean forallMatches = forall(Lists.of(10, 20, 30, 40, 50), new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input % 10 == 0;
            }
        });
        assertTrue(forallMatches);

        boolean notForAll = forall(Lists.of(1, 20, 30, 40, 50), new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input % 10 == 0;
            }
        });
        assertFalse(notForAll);
    }


}