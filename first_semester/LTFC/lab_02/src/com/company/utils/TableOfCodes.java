package com.company.utils;

import java.util.HashMap;
import java.util.Map;

public class TableOfCodes {

    public static final String IDENTIFIERS = "IDENTIFIERS";
    public static final String CONSTANTS = "CONSTANTS";
    public static final String ARRAY = "ARRAY";
    public static final String VAR = "VAR";
    public static final String INTEGER = "INTEGER";
    public static final String CHAR = "CHAR";
    public static final String BEGIN = "BEGIN";
    public static final String END = "END";
    public static final String READ = "READ";
    public static final String WRITE = "WRITE";
    public static final String DO = "DO";
    public static final String IF = "IF";
    public static final String THEN = "THEN";
    public static final String ELSE = "ELSE";
    public static final String WHILE = "WHILE";
    public static final String COLON = ":";
    public static final String SEMI_COLON = ";";
    public static final String DOT = ".";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLIED = "*";
    public static final String DIVIDED = "/";
    public static final String RIGHT_BRACKET = "(";
    public static final String LEFT_BRACKET = ")";
    public static final String R_A = "[";
    public static final String L_A = "]";
    public static final String LOWER = "<";
    public static final String GREATER = ">";
    public static final String EQUAL = "=";
    public static final String LE = "<=";
    public static final String GE = ">=";

    public static final Map<String, Integer> tableOfCodes = new HashMap<>() {{
        put(IDENTIFIERS, 0);
        put(CONSTANTS, 1);
        put(ARRAY, 2);
        put(VAR, 3);
        put(INTEGER, 4);
        put(CHAR, 5);
        put(BEGIN, 6);
        put(END, 7);
        put(READ, 8);
        put(WRITE, 9);
        put(DO, 10);
        put(IF, 11);
        put(THEN, 12);
        put(ELSE, 13);
        put(WHILE, 14);
        put(COLON, 15);
        put(SEMI_COLON, 16);
        put(DOT, 17);
        put(PLUS, 18);
        put(MINUS, 19);
        put(MULTIPLIED, 20);
        put(DIVIDED, 21);
        put(RIGHT_BRACKET, 22);
        put(LEFT_BRACKET, 23);
        put(R_A, 24);
        put(L_A, 25);
        put(LOWER, 26);
        put(GREATER, 27);
        put(EQUAL, 28);
        put(LE, 29);
        put(GE, 30);
    }};
}
