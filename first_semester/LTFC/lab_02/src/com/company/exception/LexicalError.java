package com.company.exception;

public class LexicalError extends Exception {
    public static final String ERROR_LINE = "Lexical error at line: ";
    public static final String WRONG_LENGTH_IDENTIFIER = "The identifier cannot have more than 8 characters.";
    public static final String WRONG_IDENTIFIER = "The identifier doesn't respect the naming conventions.";
    public static final String WRONG_CHAR = "The char is wrongly defined.";

}
