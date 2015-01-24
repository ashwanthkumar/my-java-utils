package in.ashwanthkumar.utils.lang.option;

import org.junit.Test;

import static in.ashwanthkumar.utils.lang.option.Option.option;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OptionTest {

    @Test
    public void shouldSafelyWrapNullValues() {
        Option<Object> option = option(null);
        assertTrue(option.isEmpty());
        assertFalse(option.isDefined());
    }

    @Test
    public void shouldReturnDefaultValueOnlyWhenValueIsNotPresent() {
        assertThat(Option.<Integer>option(null).getOrElse(5), is(5));
        assertThat(option(10).getOrElse(5), is(10));
    }

    @Test
    public void shouldReturnWrappedValueOnGet() {
        Option<Integer> option = option(1);
        assertThat(option.get(), is(1));
    }

}