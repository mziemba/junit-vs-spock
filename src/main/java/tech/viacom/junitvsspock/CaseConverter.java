package tech.viacom.junitvsspock;

import com.google.common.base.CaseFormat;

/**
 * Converts given space-delimited string to a given {@link CaseFormat}.
 */
public class CaseConverter {

    public String convert(String input, CaseFormat format) {
        return CaseFormat.LOWER_UNDERSCORE.to(format, input.toLowerCase().replace(' ', '_'));
    }
}
