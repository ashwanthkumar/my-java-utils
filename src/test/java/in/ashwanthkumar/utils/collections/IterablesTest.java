package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Predicate;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static in.ashwanthkumar.utils.collections.Iterables.*;
import static in.ashwanthkumar.utils.collections.Lists.Nil;
import static in.ashwanthkumar.utils.lang.option.Option.option;
import static org.hamcrest.CoreMatchers.is;
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


    @Test
    public void shouldTestIfListIsEmptyOrNot() {
        assertTrue(isEmpty(Lists.of()));

        assertFalse(isEmpty(Lists.of(1)));
        assertTrue(nonEmpty(Lists.of(1)));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowAnExceptionOnHeadFromEmptyList() {
        head(Nil());
        fail("Should not come here");
    }

    @Test
    public void shouldReturnNoneForHeadOptionOnEmptyList() {
        assertThat(headOption(Nil()), is(option(null)));
    }

    @Test
    public void shouldReturnValueForHeadOption() {
        List<Integer> list = Lists.of(1, 2, 3, 4, 5);
        assertThat(headOption(list), is(option(1)));
    }

    @Test
    public void shouldPickHeadElementFromList() {
        List<Integer> list = Lists.of(1, 2, 3);
        Integer head = head(list);
        assertThat(head, is(1));
    }

}