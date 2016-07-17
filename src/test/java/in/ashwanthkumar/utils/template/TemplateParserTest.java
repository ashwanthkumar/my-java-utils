package in.ashwanthkumar.utils.template;

import in.ashwanthkumar.utils.collections.Maps;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TemplateParserTest {
    @Test
    public void shouldRenderTemplateForVariable() {
        TemplateParser templateParser = new TemplateParser(Maps.<String, Object>of("var1", "foo", "var2", "bar"));
        String output = templateParser.render("#{var1} is actually foo, while #{var2} is bar.");
        assertThat(output, is("foo is actually foo, while bar is bar."));
    }

    @Test
    public void shouldRenderTemplateForVariableWithDefaults() {
        TemplateParser templateParser = new TemplateParser(Maps.<String, Object>of("var1", "foo", "var2", "bar"));
        String output = templateParser.render("#{var1} is actually foo, while #{var3?baz} is bar.");
        assertThat(output, is("foo is actually foo, while baz is bar."));
    }
}
