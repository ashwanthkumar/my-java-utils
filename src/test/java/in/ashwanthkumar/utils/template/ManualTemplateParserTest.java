package in.ashwanthkumar.utils.template;

import in.ashwanthkumar.utils.collections.Maps;
import org.junit.Test;

import static in.ashwanthkumar.utils.template.ManualTemplateParser.ParserState.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ManualTemplateParserTest {
    @Test
    public void shouldRenderTheGivenStringWithVariableSubstitution() {
        String rendered = new ManualTemplateParser(Maps.<String, Object>of("var", 1)).render("Variable is #{var}");
        assertThat(rendered, is("Variable is 1"));
    }

    @Test
    public void shouldRenderTheGivenStringWithDefaultvalue() {
        String rendered = new ManualTemplateParser(Maps.<String, Object>of("var1", 1)).render("Variable has default=#{var?default}");
        assertThat(rendered, is("Variable has default=default"));
    }

    @Test
    public void shouldReturnRightParserState() {
        ManualTemplateParser parser = new ManualTemplateParser(Maps.<String, Object>of("var", 1));
        assertThat(parser.parse('#', DEFAULT_START), is(VAR_POUND));
        assertThat(parser.parse('{', VAR_POUND), is(VAR_START));
        assertThat(parser.parse('v', VAR_NAME), is(VAR_NAME));
        assertThat(parser.parse('a', VAR_NAME), is(VAR_NAME));
        assertThat(parser.parse('r', VAR_NAME), is(VAR_NAME));
        assertThat(parser.parse('}', VAR_NAME), is(VAR_END));
    }

    @Test
    public void shouldReturnRightParserStateWhenDefaultsIsPresent() {
        ManualTemplateParser parser = new ManualTemplateParser(Maps.<String, Object>of("var", 1));
        assertThat(parser.parse('#', DEFAULT_START), is(VAR_POUND));
        assertThat(parser.parse('{', VAR_POUND), is(VAR_START));
        assertThat(parser.parse('v', VAR_NAME), is(VAR_NAME));
        assertThat(parser.parse('a', VAR_NAME), is(VAR_NAME));
        assertThat(parser.parse('r', VAR_NAME), is(VAR_NAME));
        assertThat(parser.parse('?', VAR_NAME), is(DEFAULT_START));
        assertThat(parser.parse('n', DEFAULT_START), is(DEFAULT_VALUE));
        assertThat(parser.parse('a', DEFAULT_VALUE), is(DEFAULT_VALUE));
        assertThat(parser.parse('m', DEFAULT_VALUE), is(DEFAULT_VALUE));
        assertThat(parser.parse('e', DEFAULT_VALUE), is(DEFAULT_VALUE));
        assertThat(parser.parse('}', DEFAULT_VALUE), is(VAR_END));
    }


}