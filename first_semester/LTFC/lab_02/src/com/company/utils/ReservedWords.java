package com.company.utils;

import java.util.List;

import static com.company.utils.TableOfCodes.*;

public class ReservedWords {

    public static boolean isReservedWord(String atom) {
        return List.of(VAR, ARRAY, INTEGER, CHAR, BEGIN, END, READ, WRITE, DO, IF, THEN, ELSE, WHILE).contains(atom);
    }
}
