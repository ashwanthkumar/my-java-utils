package in.ashwanthkumar.utils.parser;

import org.junit.Test;

import java.util.regex.Pattern;

import static in.ashwanthkumar.utils.parser.Parsers.Literal;
import static in.ashwanthkumar.utils.parser.Parsers.Regex;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParsersTest {
    @Test
    public void shouldCheckForLiteralString() {
        Parser<String> parser = Literal("input");
        ParserResult<String> result = parser.parse("input is awesome");
        assertThat(result.successful(), is(true));
        assertThat(result.empty(), is(false));
        assertThat(result.get(), is("input"));
        assertThat(result.remainingInput, is(" is awesome"));
    }

    @Test
    public void shouldCheckForCharacterInInput() {
        Parser<String> parser = Parsers.Character('i');
        ParserResult<String> result = parser.parse("input is awesome");
        assertThat(result.successful(), is(true));
        assertThat(result.empty(), is(false));
        assertThat(result.get(), is("i"));
        assertThat(result.remainingInput, is("nput is awesome"));
    }

    @Test
    public void shouldCheckForInteger() {
        Parser<Integer> parser = Parsers.Integer(123);
        ParserResult<Integer> result = parser.parse("123 and you are free");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is(123));
        assertThat(result.remainingInput, is(" and you are free"));
    }

    @Test
    public void shouldCheckForDouble() {
        Parser<Double> parser = Parsers.Double(3.14);
        ParserResult<Double> result = parser.parse("3.14 is the value of PI");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is(3.14));
        assertThat(result.remainingInput, is(" is the value of PI"));
    }

    @Test
    public void shouldComposeParsersTogether() {
        Parser<String> variableParser = Literal("#{")
                .thenR(Regex(Pattern.compile("[a-zA-Z0-9]+")))
                .thenL(Literal("}"));

        ParserResult<String> result = variableParser.parse("#{variable}");
        assertThat(result.successful(), is(true));
        assertThat(result.get(), is("variable"));
    }
}

