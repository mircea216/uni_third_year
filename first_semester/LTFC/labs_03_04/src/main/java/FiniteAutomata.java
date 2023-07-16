import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FiniteAutomata {
    private static final String STATES = "states";
    private static final String ALPHABET = "alphabet";
    private static final String INITIAL_STATE = "initialState";
    private static final String TRANSITIONS = "transitions";
    private static final String FINAL_STATES = "finalStates";
    private static final String SEPARATOR = "|";
    private static final String FILENAME_CUSTOM = "data/custom_automata.json";
    private List<String> states;
    private List<String> transitions;
    private List<String> alphabet;
    private String initialState;
    private List<String> finalStates;
    public Map<String, String> transitionsHandler = new TreeMap<>();

    public List<String> getStates() {
        return states;
    }

    public List<String> getTransitions() {
        return transitions;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public String getInitialState() {
        return initialState;
    }

    public FiniteAutomata setStates(List<String> states) {
        this.states = states;
        return this;
    }

    public FiniteAutomata setTransitions(List<String> transitions) {
        this.transitions = transitions;
        return this;
    }

    public FiniteAutomata setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
        return this;
    }

    public FiniteAutomata setInitialState(String initialState) {
        this.initialState = initialState;
        return this;
    }

    public FiniteAutomata setFinalStates(List<String> finalStates) {
        this.finalStates = finalStates;
        return this;
    }

    public FiniteAutomata() {
        parseAutomataFile();
    }

    public boolean acceptsSequence(String sequence) {
        String state = getInitialState();
        for (int i = 0; i < sequence.length(); i++) {
            if (!transitionsHandler.containsKey(state + sequence.charAt(i))) {
                return false;
            } else {
                state = transitionsHandler.get(state + sequence.charAt(i));
            }
            if (state.isEmpty()) {
                return false;
            }
        }
        return getFinalStates().contains(state);
    }

    public String whereItGoes(String state, String symbol) {
        if (transitionsHandler.get(state + symbol) == null)
            return "There is no link between " + state + " and " + symbol;
        return "The state " + state + " goes to " + transitionsHandler.get(state + symbol) + " through " + symbol;
    }

    public void convertFiniteAutomataToGrammar() {
        Grammar grammar = new Grammar();
        grammar.setStartSymbol(initialState)
                .setNonterminals(states)
                .setTerminals(alphabet)
                .setProductions(generateProductionsFromTransitions());
        System.out.println(grammar);
    }

    @Override
    public String toString() {
        return "The finite automata is: " +
                "\nstates: " + states +
                "\ntransitions: " + transitions +
                "\nalphabet: " + alphabet +
                "\ninitialState: '" + initialState + '\'' +
                "\nfinalStates: " + finalStates;
    }

    private void parseAutomataFile() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(FiniteAutomata.FILENAME_CUSTOM)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            extractAutomataFields(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void extractAutomataFields(JSONObject jsonObject) {
        states = (List<String>) jsonObject.get(STATES);
        alphabet = (List<String>) jsonObject.get(ALPHABET);
        initialState = (String) jsonObject.get(INITIAL_STATE);
        transitions = (List<String>) jsonObject.get(TRANSITIONS);
        finalStates = (List<String>) jsonObject.get(FINAL_STATES);
        transitions.forEach(transition -> {
            transitionsHandler.put(String.valueOf(transition.charAt(0)) + transition.charAt(2),
                    String.valueOf(transition.charAt(4)));
        });
    }

    private List<String> generateProductionsFromTransitions() {
        List<String> productions = new ArrayList<>();
        for (String transition : transitions) {
            String production;
            if (finalStates.contains(String.valueOf(transition.charAt(4)))) {
                production = transition.charAt(0) + Grammar.ARROW + transition.charAt(2) + transition.charAt(4) +
                        SEPARATOR + transition.charAt(2);
                productions.add(production);
            } else {
                production = transition.charAt(0) + Grammar.ARROW + transition.charAt(2) + transition.charAt(4);
                productions.add(production);
            }
        }
        return productions;
    }
}
