import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GIC {
    public static final String SPACE = " ";
    public static final String GRAMMAR_FILE = "grammar/grammar.txt";
    public static final String ARROW = " -> ";
    public static final String REGEX = " \\| ";
    private final List<String> nonTerminals;
    private final List<String> terminals;
    private final List<ProductionHandler> productions;
    private String startSymbol;

    public GIC() {
        this.nonTerminals = new ArrayList<>();
        this.terminals = new ArrayList<>();
        this.productions = new ArrayList<>();
        extractGrammarFields();
    }

    private void extractGrammarFields() {
        try {
            int indexCounter = 0;
            for (String line : Files.readAllLines(Paths.get(GRAMMAR_FILE))) {
                handleGrammarElemsExceptProductions(indexCounter, line);
                handleProductionsForFile(indexCounter, line);
                indexCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleProductionsForFile(int indexCounter, String line) {
        if (indexCounter > 2) {
            String[] chores = line.split(ARROW);
            List<List<String>> rules = new ArrayList<>();
            for (String rule : chores[1].split(REGEX))
                rules.add(Arrays.asList(rule.split(SPACE)));
            productions.add(new ProductionHandler(chores[0], rules));
        }
    }

    private void handleGrammarElemsExceptProductions(int indexCounter, String line) {
        if (indexCounter <= 2) {
            String[] chores = line.split(SPACE);
            for (String chore : chores) {
                if (indexCounter == 0) {
                    startSymbol = chore;
                }
                if (indexCounter == 1) {
                    nonTerminals.add(chore);
                }
                if (indexCounter == 2) {
                    if (!terminals.contains(chore))
                        terminals.add(chore);
                }
            }
        }
    }

    public Set<ProductionHandler> handleProductionsHavingNonterminal(String nonTerminal) {
        Set<ProductionHandler> productionsForNonterminal = new HashSet<>();
        for (ProductionHandler production : productions) {
            for (List<String> rule : production.getGrammarRules())
                if (rule.contains(nonTerminal))
                    productionsForNonterminal.add(production);
        }
        return productionsForNonterminal;
    }

    public List<ProductionHandler> handleProductionsForANonterminal(String nonTerminal) {
        List<ProductionHandler> productionsForNonterminal = new ArrayList<>();
        for (ProductionHandler production : productions) {
            if (production.getStartChore().equals(nonTerminal)) {
                productionsForNonterminal.add(production);
            }
        }
        return productionsForNonterminal;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public void displayGIC() {
        System.out.println("Parsed gic");
        System.out.println("The start symbol is: " + startSymbol);
        System.out.println("The nonTerminals are: " + nonTerminals);
        System.out.println("The terminals are: " + terminals);
        System.out.println("The productions are: " + productions);
        System.out.println();
    }
}
