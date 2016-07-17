package in.ashwanthkumar.utils.template;

import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.collections.Maps;
import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Functions;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;
import in.ashwanthkumar.utils.parser.Parser;
import in.ashwanthkumar.utils.parser.Parsers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * TemplateParser build using {@link in.ashwanthkumar.utils.parser.Parser}
 * <p></p>
 * It supports the following type of rendering
 * <ol>
 * <li>{@code #{variable}} type substitution</li>
 * <li>{@code #{variable?default}} type substitution. Here if the {@code variable} value is not passed, it would assign the {@code default} value</li>
 * </ol>
 */
public class TemplateParser {
    private Map<String, Object> variables;

    public TemplateParser(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String render(String input) {
        Parser<String> baseParser = templateParser();
        return Parsers.Sequence(baseParser)
                .parse(input)
                .map(new Function<List<String>, String>() {
                    @Override
                    public String apply(List<String> input) {
                        return Lists.mkString(input, "");
                    }
                })
                .get();
    }

    private Parser<String> templateParser() {
        Parser<String> variableName = Parsers.Regex(Pattern.compile("[a-zA-Z0-9]+")).named("variable name regex");
        Parser<String> defaultValue = Parsers.Regex(Pattern.compile("[a-zA-Z0-9_$.]+")).named("default value regex");

        Parser<State> withDefault = Parsers.Literal("#{")
                .thenR(variableName)
                .thenL(Parsers.Literal("?"))
                .then(defaultValue)
                .thenL(Parsers.Literal("}"))
                .map(new Function<Tuple2<String, String>, State>() {
                    @Override
                    public State apply(Tuple2<String, String> input) {
                        return new State(input._1(), input._2());
                    }
                });

        Parser<State> variableNameParser = Parsers.Literal("#{")
                .thenR(variableName)
                .thenL(Parsers.Literal("}"))
                .map(new Function<String, State>() {
                    @Override
                    public State apply(String input) {
                        return new State(input);
                    }
                });

        return withDefault
                .or(variableNameParser)
                .map(new Function<State, String>() {
                    @Override
                    public String apply(State input) {
                        return String.valueOf(Maps.getOrElse(variables, input.variableName, input.defaultValue));
                    }
                })
                .or(Parsers.Regex(Pattern.compile(".")).named("match all"))
                .map(Functions.<String>identity());
    }

    static class State {
        String variableName;
        String defaultValue;

        public State(String variableName) {
            this.variableName = variableName;
        }

        public State(String variableName, String defaultValue) {
            this.variableName = variableName;
            this.defaultValue = defaultValue;
        }

        @Override
        public String toString() {
            return "State{" +
                    "variableName='" + variableName + '\'' +
                    ", defaultValue='" + defaultValue + '\'' +
                    '}';
        }
    }
}
