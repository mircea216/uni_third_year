import java.util.*;

public class GicFunctions {
    public static final String EPSILON = "ε";
    private static final Stack<List<String>> gicRules = new Stack<>();
    private final Map<String, Set<String>> firstHandlerMap;
    private final Map<String, Set<String>> followHandlerMap;
    private final GIC gic;

    public GicFunctions(GIC gic) {
        this.gic = gic;
        this.firstHandlerMap = new HashMap<>();
        this.followHandlerMap = new HashMap<>();
        for (String nonTerminal : gic.getNonTerminals()) {
            this.firstHandlerMap.put(nonTerminal, computeFirstOfANonTerminal(nonTerminal));
        }
        for (String nonTerminal : gic.getNonTerminals()) {
            followHandlerMap.put(nonTerminal, computeFollowOfANonTerminal(nonTerminal, nonTerminal));
        }
    }

    public void displayFirstFollow() {
        for (Map.Entry<String, Set<String>> entry : this.firstHandlerMap.entrySet()) {
            System.out.println("First of " + entry.getKey() + " is: " + entry.getValue());
        }
        System.out.println();
        for (Map.Entry<String, Set<String>> entry : this.followHandlerMap.entrySet()) {
            System.out.println("Follow of " + entry.getKey() + " is: " + entry.getValue());
        }
    }

    private Set<String> computeFirstOfANonTerminal(String nonTerminal) {
        if (firstHandlerMap.containsKey(nonTerminal))
            return firstHandlerMap.get(nonTerminal);
        Set<String> setOfChoresForNonTerminal = new HashSet<>();
        return handleChoreForNonTerminal(nonTerminal, setOfChoresForNonTerminal);
    }

    private Set<String> handleChoreForNonTerminal(String nonTerminal, Set<String> setOfChoresForNonTerminal) {
        List<String> terminals = gic.getTerminals();
        for (ProductionHandler productionHandler : gic.handleProductionsForANonterminal(nonTerminal)) {
            for (List<String> rule : productionHandler.getGrammarRules()) {
                String start = rule.get(0);
                if (start.equals(EPSILON))
                    setOfChoresForNonTerminal.add(EPSILON);
                else if (terminals.contains(start))
                    setOfChoresForNonTerminal.add(start);
                else
                    setOfChoresForNonTerminal.addAll(computeFirstOfANonTerminal(start));
            }
        }
        return setOfChoresForNonTerminal;
    }

    private Set<String> computeFollowOfANonTerminal(String nonTerminal, String initialNonTerminal) {
        if (followHandlerMap.containsKey(nonTerminal))
            return followHandlerMap.get(nonTerminal);
        Set<String> setOfChoresForNonTerminal = new HashSet<>();
        List<String> terminals = gic.getTerminals();

        if (nonTerminal.equals(gic.getStartSymbol()))
            setOfChoresForNonTerminal.add(EPSILON);

        for (ProductionHandler productionHandler : gic.
                handleProductionsHavingNonterminal(nonTerminal)) {
            String productionStart = productionHandler.getStartChore();
            for (List<String> gicRule : productionHandler.getGrammarRules()) {
                List<String> conflictsForRules = new ArrayList<>();
                conflictsForRules.add(nonTerminal);
                conflictsForRules.addAll(gicRule);
                if (gicRule.contains(nonTerminal) && !gicRules.contains(conflictsForRules)) {
                    gicRules.push(conflictsForRules);
                    int indexNonTerminal = gicRule.indexOf(nonTerminal);
                    setOfChoresForNonTerminal.addAll(
                            followHandlerFunction(nonTerminal, setOfChoresForNonTerminal,
                                    terminals,
                                    productionStart, gicRule,
                                    indexNonTerminal, initialNonTerminal));
                    List<String> sublist = gicRule.subList(indexNonTerminal + 1, gicRule.size());
                    if (sublist.contains(nonTerminal))
                        setOfChoresForNonTerminal.addAll(followHandlerFunction(nonTerminal,
                                setOfChoresForNonTerminal,
                                terminals, productionStart,
                                gicRule,
                                indexNonTerminal + 1 + sublist.indexOf(nonTerminal),
                                initialNonTerminal));
                    gicRules.pop();
                }
            }
        }

        return setOfChoresForNonTerminal;
    }

    private Set<String> followHandlerFunction(String nonTerminal, Set<String> setOfChoresForNonTerminal,
                                              List<String> terminals, String productionChoreStart, List<String> rule,
                                              int counterTerminal, String firstNonTerminal) {
        if (counterTerminal == rule.size() - 1) {
            if (productionChoreStart.equals(nonTerminal))
                return setOfChoresForNonTerminal;
            if (!firstNonTerminal.equals(productionChoreStart)) {
                setOfChoresForNonTerminal.addAll(computeFollowOfANonTerminal(productionChoreStart, firstNonTerminal));
            }
        } else {
            String nextSymbol = rule.get(counterTerminal + 1);
            if (terminals.contains(nextSymbol))
                setOfChoresForNonTerminal.add(nextSymbol);
            else {
                if (!firstNonTerminal.equals(nextSymbol)) {
                    Set<String> parts = new HashSet<>(firstHandlerMap.get(nextSymbol));
                    if (parts.contains(EPSILON)) {
                        setOfChoresForNonTerminal.addAll(computeFollowOfANonTerminal(nextSymbol, firstNonTerminal));
                        parts.remove(EPSILON);
                    }
                    setOfChoresForNonTerminal.addAll(parts);
                }
            }
        }
        return setOfChoresForNonTerminal;
    }
}
