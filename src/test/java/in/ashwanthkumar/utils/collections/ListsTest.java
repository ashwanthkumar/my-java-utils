package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Functions;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.option.None;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

import static in.ashwanthkumar.utils.collections.Lists.*;
import static in.ashwanthkumar.utils.lang.option.Option.option;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class ListsTest {

    @Test
    public void shouldFoldLeft() {
        List<Integer> numbers = Lists.of(1, 2, 3, 4, 5);
        Integer sum = foldL(numbers, 0, doSum());
        assertThat(sum, is(15));
    }

    @Test
    public void shouldFilter() {
        List<Integer> numbers = Lists.of(1, 2, 3, 4, 5);
        List<Integer> oddNumbers = filter(numbers, pickOddNumbers());

        assertThat(oddNumbers.size(), is(3));
        assertThat(oddNumbers, hasItem(1));
        assertThat(oddNumbers, hasItem(3));
        assertThat(oddNumbers, hasItem(5));
    }

    @Test
    public void shouldDoFilterAndFold() {
        List<Integer> numbers = Lists.of(1, 2, 3, 4, 5);
        Integer sum = foldL(filter(numbers, pickOddNumbers()), 0, doSum());
        assertThat(sum, is(9));
    }

    @Test
    public void shouldCreateStringFromList() {
        assertThat(mkString(Lists.of(1, 2, 3, 4, 5)), is("1, 2, 3, 4, 5"));
        assertThat(mkString(Lists.of(1, 2, 3, 4, 5), "(", ")", ","), is("(1, 2, 3, 4, 5)"));
    }
    @Test
    public void shouldFindAnElementFromList() {
        Option<Integer> value = find(Lists.of(1, 2, 3, 4, 5), new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input == 5;
            }
        });

        assertTrue(value.isDefined());
        assertThat(value.get(), is(5));

        Option<Integer> notFound = find(Lists.of(1, 2, 3, 4, 5), new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input == 10;
            }
        });
        assertTrue(notFound.isEmpty());
        assertThat(notFound.getOrElse(5), is(5));
    }

    @Test
    public void shouldTransformAnArray() {
        String input = "a b c d e f g h i j";
        List<String> output = map(input.split(" "), Functions.<String>identity());
        assertThat(output.size(), is(10));
    }

    @Test
    public void shouldFlattenAListOfList() {
        List<List<Integer>> listOfList = Lists.of(Lists.of(1, 2, 3), Lists.of(4, 5, 6));
        List<Integer> flatten = Lists.flatten(listOfList);
        for (int i = 1; i <= 6; i++) {
            assertThat(flatten, hasItem(i));
        }
    }

    @Test
    public void shouldConcatMultipleList() {
        List<Integer> flatten = concat(Lists.of(1, 2, 3), Lists.of(4, 5, 6));
        for (int i = 1; i <= 6; i++) {
            assertThat(flatten, hasItem(i));
        }
    }

    private Predicate<Integer> pickOddNumbers() {
        return new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input % 2 != 0;
            }
        };
    }


    private Function<Tuple2<Integer, Integer>, Integer> doSum() {
        return new Function<Tuple2<Integer, Integer>, Integer>() {
            @Override
            public Integer apply(Tuple2<Integer, Integer> input) {
                Integer sumSoFar = input._1();
                Integer element = input._2();
                return sumSoFar + element;
            }
        };
    }


}