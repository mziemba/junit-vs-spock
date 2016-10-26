package tech.viacom.junitvsspock

import spock.lang.Specification
import spock.lang.Unroll

import static com.google.common.base.CaseFormat.LOWER_HYPHEN as KEBAB
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE as SNAKE
import static com.google.common.base.CaseFormat.UPPER_CAMEL as CAMEL

class S01a_DataDrivenSpec extends Specification {
    // converts '#string' to '#expected' using #style

    @Unroll
    def "converts '#string' to '#expected' using #style"() {
        given:
            def converter = new CaseConverter()

        expect:
            converter.convert(string, style) == expected

        where:
            string        | style || expected
            "foo bar baz" | CAMEL || "FooBarBaz"
            "foo bar baz" | KEBAB || "foo-bar-baz"
            "foo bar baz" | SNAKE || "foo_bar_baz"
    }
}
