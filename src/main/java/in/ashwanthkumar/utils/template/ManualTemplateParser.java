package in.ashwanthkumar.utils.template;

import java.util.Map;

public class ManualTemplateParser {
    enum ParserState {
        NOTHING,
        VAR_POUND,
        VAR_START,
        VAR_NAME,
        DEFAULT_START,
        DEFAULT_VALUE,
        VAR_END
    }

    private Map<String, Object> variables;

    private ParserState state;
    private StringBuilder variableName;
    private StringBuilder defaultValue;

    public ManualTemplateParser(Map<String, Object> variables) {
        this.variables = variables;
        this.state = ParserState.NOTHING;
        variableName = new StringBuilder();
        defaultValue = new StringBuilder();
    }

    public String render(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            state = parse(c, state);
            if (state == ParserState.VAR_NAME) {
                variableName.append(c);
            } else if (state == ParserState.DEFAULT_VALUE) {
                defaultValue.append(c);
            } else if (state == ParserState.VAR_END) {
                String variable = variableName.toString();
                Object value = variables.get(variable);
                if (value == null) {
                    value = defaultValue.toString();
                }
                output.append(value);
                variableName = new StringBuilder();
                defaultValue = new StringBuilder();
            } else if (state == ParserState.NOTHING) {
                output.append(c);
            }
        }
        return output.toString();
    }

    ParserState parse(char input, ParserState previous) {
        switch (input) {
            case '#':
                return ParserState.VAR_POUND;
            case '{':
                if (previous == ParserState.VAR_POUND) {
                    return ParserState.VAR_START;
                }
            case '?':
                if (previous == ParserState.VAR_NAME) {
                    return ParserState.DEFAULT_START;
                }
            case '}':
                if (previous == ParserState.VAR_NAME || previous == ParserState.DEFAULT_VALUE) {
                    return ParserState.VAR_END;
                }
            default:
                if (previous == ParserState.VAR_START || previous == ParserState.VAR_NAME)
                    return ParserState.VAR_NAME;
                else if (previous == ParserState.DEFAULT_START || previous == ParserState.DEFAULT_VALUE)
                    return ParserState.DEFAULT_VALUE;
                else
                    return ParserState.NOTHING;
        }
    }
}
