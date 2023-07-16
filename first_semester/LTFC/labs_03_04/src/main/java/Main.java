public class Main {

    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        grammar.showGrammarElements("A");
        if (grammar.isRegular()) {
            System.out.println("The grammar is regular");
        } else {
            System.out.println("The grammar is not regular");
        }
        System.out.println("The conversion from grammar to finite automata");
        grammar.convertGrammarToFiniteAutomata();
        System.out.println();
        System.out.println("The conversion from finite automata to grammar");
        FiniteAutomata finiteAutomata = new FiniteAutomata();
        finiteAutomata.convertFiniteAutomataToGrammar();
    }
}
