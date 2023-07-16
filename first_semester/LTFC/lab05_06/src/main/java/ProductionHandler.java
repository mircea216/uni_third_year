import java.util.List;

public class ProductionHandler {
    public static final String ARROW = " -> ";
    public static final String SPACE = " ";
    public static final String PIPE_SIGN = "| ";
    public static final String EMPTY = "";
    private final String startChore;
    private final List<List<String>> grammarRules;

    public ProductionHandler(String start, List<List<String>> grammarRules) {
        this.startChore = start;
        this.grammarRules = grammarRules;
    }

    public List<List<String>> getGrammarRules() {
        return grammarRules;
    }

    public String getStartChore() {
        return startChore;
    }

    public String toString() {
        StringBuilder productions = new StringBuilder(startChore + ARROW);
        for (List<String> rule : grammarRules) {
            for (String element : rule)
                productions.append(element).append(SPACE);
            productions.append(PIPE_SIGN);
        }
        productions.replace(productions.length() - 3, productions.length() - 1, EMPTY);
        return productions.toString().replace(SPACE, EMPTY);
    }
}
