package com.company.utils;

public class IdentifiersRestrictions {

    public static final String ALPHA_PATTERN = "[a-zA-Z]";
    public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    public static final String NUMERIC_PATTERN = "[0-9]+";

    public static boolean hasMoreThan8Letters(String identifier) {
        return identifier.length() > 8;
    }

    public static boolean isIdentifier(String identifier) {
        return isAlphaNumeric(identifier) && startsWithLetter(identifier);
    }

    private static boolean isAlphaNumeric(String trimmedIdentifier) {
        return trimmedIdentifier.matches(ALPHANUMERIC_PATTERN);
    }

    private static boolean startsWithLetter(String trimmedIdentifier) {
        if (trimmedIdentifier.isEmpty())
            return false;
        return String.valueOf(trimmedIdentifier.charAt(0)).matches(ALPHA_PATTERN);
    }
}
