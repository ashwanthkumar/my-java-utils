package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.lang.StringUtils;

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
                    return Failure.of("expected " + literal + " but found " + input.substring(0, size(literal)), input);
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
}
