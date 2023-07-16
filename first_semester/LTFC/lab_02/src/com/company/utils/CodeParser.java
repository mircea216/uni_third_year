package com.company.utils;

import com.company.bst.FIP;
import com.company.bst.OrderedTree;
import com.company.bst.TS;
import com.company.exception.LexicalError;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.company.utils.TableOfCodes.CONSTANTS;
import static com.company.utils.TableOfCodes.IDENTIFIERS;

public class CodeParser {
    private static final String FILE_NAME = "data/code.txt";
    public static final String SPACE = " ";
    public static final String WORD_REGEX = "[\\w]";
    public static final String NON_WORD_REGEX = "[^\\w']+";
    public static final String APOSTROPHE = "'";
    public static final String FIP_TXT = "data/fip.txt";
    public static final String HEADER_FIP = "CODE TS POS";
    public static final String HEADER_TS = "NrCrt" + " " + "C/I";

    public static void extractIdentifiersAndConstantsForTS() {
        List<String> constantsIdentifiers = new ArrayList<>();
        for (String line : readFromFile()) {
            List<String> constantsIdentifiersWords = getConstantsIdentifiersWords(line);
            for (String constantIdentifier : constantsIdentifiersWords) {
                if (!constantsIdentifiers.contains(constantIdentifier) &&
                        !ReservedWords.isReservedWord(constantIdentifier) &&
                        (Constants.isNumericConstant(constantIdentifier)
                                || (IdentifiersRestrictions.isIdentifier(constantIdentifier) &&
                                !IdentifiersRestrictions.hasMoreThan8Letters(constantIdentifier)))) {
                    constantsIdentifiers.add(constantIdentifier);
                }
                if (constantIdentifier.contains(APOSTROPHE) && constantIdentifier.length() == 3) {
                    constantsIdentifiers.add(String.valueOf(constantIdentifier));
                }
            }
        }
        OrderedTree orderedTreeForTs = new OrderedTree();
        constantsIdentifiers.forEach(orderedTreeForTs::add);
        List<String> tsCI = orderedTreeForTs.traverseInOrder();

        var ref = new Object() {
            Integer nrCrt = 1;
        };
        tsCI.forEach(element -> {
            TS.ts.put(element, ref.nrCrt);
            ref.nrCrt = ref.nrCrt + 1;
        });
        List<String> tsList = new ArrayList<>();
        tsList.add(HEADER_TS);
        for (Map.Entry<String, Integer> entry : TS.ts.entrySet()) {
            tsList.add(entry.getValue() + SPACE + SPACE + SPACE + SPACE + SPACE + SPACE + SPACE + entry.getKey());
        }
        writeToFile("data/ts.txt", tsList);
    }

    public static void extractAtomsIForFIP() {
        extractIdentifiersAndConstantsForTS();
        List<String> linesOfCode = readFromFile();
        List<FIP> fip = new ArrayList<>();
        addScenariosOfListOfAtoms(linesOfCode, fip);
        List<String> fipList = new ArrayList<>();
        fipList.add(HEADER_FIP);
        fip.stream()
                .filter(fipPair -> !(fipPair.getCode() == null || fipPair.getCode().equals(0)
                        && fipPair.getPositionInTs() == null))
                .collect(Collectors.toList())
                .forEach((f -> fipList.add(f.getCode() + SPACE + SPACE + SPACE + SPACE + SPACE + f.getPositionInTs())));
        writeToFile(FIP_TXT, fipList);
    }

    private static void addScenariosOfListOfAtoms(List<String> linesOfCode, List<FIP> fip) {
        int lineCounter = 0;
        for (String line : linesOfCode) {
            lineCounter++;
            List<String> constantsIdentifiersWords = getConstantsIdentifiersWords(line);
            List<String> separatorsOperators = getSeparatorsOperators(line);
            if (constantsIdentifiersWords.size() == separatorsOperators.size()) {
                forEqualLengths(fip, constantsIdentifiersWords, separatorsOperators, lineCounter);
            } else if (constantsIdentifiersWords.size() > separatorsOperators.size()) {
                if (separatorsOperators.size() > 0) {
                    forLongerIdentifiers(fip, constantsIdentifiersWords, separatorsOperators, lineCounter);
                } else {
                    for (String constantsIdentifiersWord : constantsIdentifiersWords) {
                        fip.add(new FIP(TableOfCodes.tableOfCodes.get(constantsIdentifiersWord), -1));
                    }
                }
            } else {
                if (constantsIdentifiersWords.size() > 0) {
                    forLongerSeparators(fip, constantsIdentifiersWords, separatorsOperators, lineCounter);
                } else {
                    for (String separatorsOperator : separatorsOperators) {
                        fip.add(new FIP(TableOfCodes.tableOfCodes.get(separatorsOperator), -1));
                    }
                }
            }
        }
    }

    private static void forLongerSeparators(List<FIP> fip, List<String> constantsIdentifiersWords,
                                            List<String> separatorsOperators, int lineCounter) {
        extractTokensForFIP(fip, constantsIdentifiersWords, separatorsOperators, lineCounter);
        for (int i = constantsIdentifiersWords.size(); i < separatorsOperators.size(); i++) {
            fip.add(new FIP(TableOfCodes.tableOfCodes.get(separatorsOperators.get(i)), -1));
        }
    }

    private static void extractTokensForFIP(List<FIP> fip, List<String> constantsIdentifiersWords,
                                            List<String> separatorsOperators, int lineCounter) {
        for (int i = 0; i < Math.min(constantsIdentifiersWords.size(), separatorsOperators.size()); i++) {
            String token = constantsIdentifiersWords.get(i);
            checkIfReservedWord(fip, separatorsOperators, i, token, lineCounter);
        }
    }

    private static void checkIfReservedWord(List<FIP> fip, List<String> separatorsOperators, int i, String token, int
            lineCounter) {
        if (ReservedWords.isReservedWord(token)) {
            fip.add(new FIP(TableOfCodes.tableOfCodes.get(token), -1));
        }
        if ((IdentifiersRestrictions.isIdentifier(token) && !IdentifiersRestrictions.hasMoreThan8Letters(token))) {
            fip.add(new FIP(TableOfCodes.tableOfCodes.get(IDENTIFIERS), TS.ts.get(token)));
        } else if (IdentifiersRestrictions.isIdentifier(token) && IdentifiersRestrictions.hasMoreThan8Letters(token)) {
            System.out.println(LexicalError.ERROR_LINE + lineCounter + SPACE + LexicalError.WRONG_LENGTH_IDENTIFIER);
        }
        if (token.contains(APOSTROPHE)) {
            if (token.length() != 3)
                System.out.println(LexicalError.ERROR_LINE + lineCounter + SPACE + LexicalError.WRONG_CHAR);
        } else {
            if (!IdentifiersRestrictions.isIdentifier(token) && !Constants.isNumericConstant(token) &&
                    !ReservedWords.isReservedWord(token)) {
                System.out.println(LexicalError.ERROR_LINE + lineCounter + SPACE + LexicalError.WRONG_IDENTIFIER);
            }
        }
        if (Constants.isNumericConstant(token)) {
            fip.add(new FIP(TableOfCodes.tableOfCodes.get(CONSTANTS), TS.ts.get(token)));
        }
        fip.add(new FIP(TableOfCodes.tableOfCodes.get(separatorsOperators.get(i)), -1));
    }

    private static void forLongerIdentifiers(List<FIP> fip, List<String> constantsIdentifiersWords,
                                             List<String> separatorsOperators, int lineCounter) {
        extractTokensForFIP(fip, constantsIdentifiersWords, separatorsOperators, lineCounter);
        for (int i = separatorsOperators.size(); i < constantsIdentifiersWords.size(); i++) {
            fip.add(new FIP(TableOfCodes.tableOfCodes.get(constantsIdentifiersWords.get(i)),
                    TS.ts.get(constantsIdentifiersWords.get(i))));
        }
    }

    private static void forEqualLengths(List<FIP> fip, List<String> constantsIdentifiersWords,
                                        List<String> separatorsOperators, int lineCounter) {
        for (int i = 0; i < constantsIdentifiersWords.size(); i++) {
            {
                String token = constantsIdentifiersWords.get(i);
                checkIfReservedWord(fip, separatorsOperators, i, token, lineCounter);
            }
        }
    }

    private static List<String> getSeparatorsOperators(String line) {
        return Arrays.stream(line.split(WORD_REGEX))
                .filter(s -> !(s.isEmpty() || s.equals(SPACE)))
                .collect(Collectors.toList());
    }

    private static List<String> getConstantsIdentifiersWords(String line) {
        return Arrays.stream(line.split(NON_WORD_REGEX)).toList();
    }

    private static List<String> readFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                lines.add(newLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static void writeToFile(String fileName, List<String> lines) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
