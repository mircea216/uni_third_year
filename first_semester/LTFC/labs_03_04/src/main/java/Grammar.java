import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Grammar {
    private static final String COMMA = ",";
    private static final String EPSILON = "ε";
    private static final String START_SYMBOL = "startSymbol";
    private static final String TERMINALS = "terminals";
    private static final String PRODUCTIONS = "productions";
    private static final String NONTERMINALS = "nonterminals";
    private static final String K = "K";
    public static final String ARROW = "->";
    public static final String PIPE = "\\|";
    public static final String GRAMMAR_JSON = "data/grammar.json";


    private String startSymbol;
    private List<String> nonterminals;
    private List<String> terminals;
    private List<String> productions;
    private final Map<String, List<String>> productionsHandler = new HashMap<>();

    public Grammar() {
        parseAutomataFile();
    }

    public Grammar setStartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
        return this;
    }

    public Grammar setNonterminals(List<String> nonterminals) {
        this.nonterminals = nonterminals;
        return this;
    }

    public Grammar setTerminals(List<String> terminals) {
        this.terminals = terminals;
        return this;
    }

    public Grammar setProductions(List<String> productions) {
        this.productions = productions;
        return this;
    }

    public void showGrammarElements(String nonterminal) {
        System.out.println("The start symbol is: " + startSymbol);
        System.out.println("The nonterminals are: " + nonterminals);
        System.out.println("The terminals are: " + terminals);
        System.out.println("The productions are: " + productions);
        if (productionsHandler.get(nonterminal) != null) {
            System.out.println("The productions for the particular nonterminal " + nonterminal + " are: " +
                    productionsHandler.get(nonterminal));
        } else {
            System.out.println(nonterminal + " has no production");
        }
    }

    public boolean isRegular() {
        for (String key : productionsHandler.keySet()) {
            if (key.length() > 1 || (key.length() == 1 && !nonterminals.contains(key))) {
                return false;
            }
        }
        int counter = 1;
        boolean flag = false;
        for (String production : productions) {
            List<String> splitProductions = Arrays.stream(production.substring(production.indexOf(ARROW) + 2)
                    .split(PIPE)).toList();
            if (counter == 1 && splitProductions.contains(EPSILON)) {
                flag = true;
            } else if (counter > 1 && splitProductions.contains(EPSILON)) {
                return false;
            }
            for (String chore : splitProductions) {
                if (chore.length() > 2) {
                    return false;
                } else if (chore.length() == 2) {
                    if (nonterminals.contains(String.valueOf(chore.charAt(0))) ||
                            terminals.contains(String.valueOf(chore.charAt(1))) ||
                            (String.valueOf(chore.charAt(1)).equals(startSymbol) && flag)) {
                        return false;
                    }
                } else if (chore.length() == 1) {
                    if (nonterminals.contains(String.valueOf(chore.charAt(0)))
                            || ((chore.equals(EPSILON)) && counter != 1)
                            || (String.valueOf(chore.charAt(0)).equals(startSymbol) && flag)
                    )
                        return false;
                }
            }
            counter++;
        }
        return true;
    }

    public void convertGrammarToFiniteAutomata() {
        if (this.isRegular()) {
            List<String> states = nonterminals;
            states.add(K);
            FiniteAutomata finiteAutomata = new FiniteAutomata();
            finiteAutomata.setAlphabet(terminals)
                    .setStates(states)
                    .setInitialState(nonterminals.get(0))
                    .setFinalStates(finalStatesGenerator())
                    .setTransitions(generateTransitionsFromProductions());
            System.out.println(finiteAutomata);
        } else {
            System.out.println("The conversion cannot be made due to irregularity");
        }
    }

    @Override
    public String toString() {
        return "The grammar is: " +
                "\nstartSymbol='" + startSymbol + '\'' +
                "\nnonterminals=" + nonterminals +
                "\nterminals=" + terminals +
                "\nproductions=" + productions;
    }

    private void extractGrammarFields(JSONObject jsonObject) {
        startSymbol = (String) jsonObject.get(START_SYMBOL);
        nonterminals = (List<String>) jsonObject.get(NONTERMINALS);
        terminals = (List<String>) jsonObject.get(TERMINALS);
        productions = (List<String>) jsonObject.get(PRODUCTIONS);
        productions.forEach(production -> {
                    productionsHandler.put(Arrays.stream(getProductionsOfATerminal(production))
                                    .collect(Collectors.toList())
                                    .get(0),
                            Arrays.stream(Arrays.toString(getProductionsOfATerminal(production))
                                    .substring(4, Arrays.toString(getProductionsOfATerminal(production)).length() - 1).split(PIPE)).toList());
                }
        );
    }

    private String[] getProductionsOfATerminal(String production) {
        return production.split(ARROW);
    }

    private List<String> finalStatesGenerator() {
        if (productions.get(0).contains(EPSILON)) {
            return List.of(nonterminals.get(0), K);
        } else return List.of(K);
    }

    private List<String> generateTransitionsFromProductions() {
        List<String> transitions = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : productionsHandler.entrySet()) {
            String transition;
            for (String value : entry.getValue()) {
                if (value.length() == 2) {
                    transition = entry.getKey() + COMMA + value.charAt(0) + COMMA + value.charAt(1);
                    transitions.add(transition);
                } else if (value.length() == 1 && !value.equals(EPSILON)) {
                    transition = entry.getKey() + COMMA + value.charAt(0) + COMMA + K;
                    transitions.add(transition);
                }
            }
        }
        return transitions;
    }

    private void parseAutomataFile() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(Grammar.GRAMMAR_JSON)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            extractGrammarFields(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
