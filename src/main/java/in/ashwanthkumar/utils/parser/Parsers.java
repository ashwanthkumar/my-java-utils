package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.lang.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static in.ashwanthkumar.utils.lang.StringUtils.size;

public class Parsers {

    public static Parser<String> Literal(final String literal) {
        return new Parser<String>() {
            @Override
            public ParserResult<String> parse(String input) {
                if (StringUtils.startsWith(input, literal)) {
                    String substring = input.substring(size(literal));
                    return Success.of(literal, substring);
                } else {
                    int size = Math.min(size(literal), size(input));
                    return Failure.of(String.format("expected '%s' but found '%s'", literal, input.substring(0, size)), input);
                }
            }
        };
    }

    public static Parser<String> Character(final char input) {
        return Literal(String.valueOf(input));
    }

    public static Parser<Integer> Integer(final Integer num) {
        return Literal(String.valueOf(num)).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(input);
            }
        });
    }

    public static Parser<Double> Double(final Double num) {
        return Literal(String.valueOf(num)).map(new Function<String, Double>() {
            @Override
            public Double apply(String input) {
                return Double.valueOf(input);
            }
        });
    }

    public static Parser<String> Regex(final Pattern regex) {
        return new Parser<String>() {
            @Override
            public ParserResult<String> parse(String input) {
                Matcher matcher = regex.matcher(input);
                if (matcher.find()) {
                    return Success.of(matcher.group(), input.substring(matcher.end()));
                } else {
                    return Failure.of(regex.pattern() + " did not match on the " + input, input);
                }
            }
        };
    }

    /**
     * Applies the parser repeatedly on the input
     */
    public static <T> Parser<List<T>> Sequence(final Parser<T> parser) {
        return new Parser<List<T>>() {
            @Override
            public ParserResult<List<T>> parse(String input) {
                List<T> results = Lists.<T>Nil();
                ParserResult<T> result = parser.parse(input);
                while (result.successful()) {
                    results.add(result.get());
                    result = parser.parse(result.getRemainingInput());
                }

                return Success.of(results, result.getRemainingInput());
            }
        };
    }
}
