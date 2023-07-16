package com.company.utils;

import static com.company.utils.IdentifiersRestrictions.NUMERIC_PATTERN;

public class Constants {

    public static boolean isNumericConstant(String atom) {
        return atom.matches(NUMERIC_PATTERN);
    }
}
