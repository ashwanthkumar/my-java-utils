package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParserTest {
    @Test
    public void shouldMapTheResult() {
        Parser<String> parser = Parsers.Literal("foo");
        Parser<String> transformedParser = parser.map(new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input + "-bar";
            }
        });

        ParserResult<String> result = transformedParser.parse("foo");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is("foo-bar"));
    }

    @Test
    public void shouldKeepTheResultOfRightOnThenR() {
        Parser<String> left = Parsers.Literal("foo");
        Parser<String> right = Parsers.Literal("bar");
        Parser<String> parser = left.thenR(right);
        ParserResult<String> result = parser.parse("foobar");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is("bar"));
    }

    @Test
    public void shouldKeepTheResultOfLeftOnThenL() {
        Parser<String> left = Parsers.Literal("foo");
        Parser<String> right = Parsers.Literal("bar");
        Parser<String> parser = left.thenL(right);
        ParserResult<String> result = parser.parse("foobar");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is("foo"));
    }

    @Test
    public void shouldKeepBothResultsOnThen() {
        Parser<String> left = Parsers.Literal("foo");
        Parser<String> right = Parsers.Literal("bar");
        Parser<Tuple2<String, String>> parser = left.then(right);
        ParserResult<Tuple2<String, String>> result = parser.parse("foobar");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is(Tuple2.tuple2("foo", "bar")));
    }

    @Test
    public void shouldEvaluateRightParserOnlyIfCurrentParserFail() {
        Parser<String> left = Parsers.Literal("foo");
        Parser<String> right = Parsers.Literal("bar");
        Parser<String> combined = left.or(right);

        ParserResult<String> positive = combined.parse("foobar");
        assertThat(positive.successful(), is(true));
        assertThat(positive.get(), is("foo"));

        ParserResult<String> evaluateSecondParser = combined.parse("barfoo");
        assertThat(evaluateSecondParser.successful(), is(true));
        assertThat(evaluateSecondParser.get(), is("bar"));
    }

}
