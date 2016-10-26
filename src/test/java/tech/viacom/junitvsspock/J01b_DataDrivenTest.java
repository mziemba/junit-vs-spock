package tech.viacom.junitvsspock;

import com.google.common.base.CaseFormat;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class J01b_DataDrivenTest {

    @Test
    @Parameters(method = "convertParams")
    @TestCaseName("converts '{0}' to '{2}' using {1}")
    public void shouldConvertToGivenCase(String input, CaseFormat style, String expected) {
        // given
        final CaseConverter converter = new CaseConverter();

        // when
        final String actual = converter.convert(input, style);

        // then
        assertEquals(expected, actual);
    }

    private Object[] convertParams() {
        // $() is deprecated in JUnitParams
        return new Object[] {
                $("foo bar baz", CaseFormat.UPPER_CAMEL, "FooBarBaz"),
                new Object[] {"foo bar baz", CaseFormat.LOWER_HYPHEN, "foo-bar-baz"},
                new Object[] {"foo bar baz", CaseFormat.LOWER_UNDERSCORE, "foo_bar_baz"}
        };
    }
}
