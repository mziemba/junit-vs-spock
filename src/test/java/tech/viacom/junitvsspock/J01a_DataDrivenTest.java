package tech.viacom.junitvsspock;

import com.google.common.base.CaseFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * We want to write tests for {@link CaseConverter} class.
 *
 * {@code
 *  converter.convert("foo bar baz", CaseFormat.UPPER_CAMEL) == "FooBarBaz"
 *  converter.convert("foo bar baz", CaseFormat.LOWER_HYPHEN) == "foo-bar-baz"
 *  converter.convert("foo bar baz", CaseFormat.LOWER_UNDERSCORE) == "foo_bar_baz"
 * }
 */
public class J01a_DataDrivenTest {

    @Test
    public void shouldConvertStringToCaseFormat() {
        final CaseConverter converter = new CaseConverter();
        {
            assertEquals("FooBarBaz", converter.convert("foo bar baz", CaseFormat.UPPER_CAMEL));
        }
        {
            assertEquals("foo-bar-baz", converter.convert("foo bar baz", CaseFormat.LOWER_HYPHEN));
        }

        assertEquals("foo_bar_baz", converter.convert("foo bar baz", CaseFormat.LOWER_UNDERSCORE));
    }
}
