public class Main {
    public static void main(String[] args) {
        GIC gic = new GIC();
        gic.displayGIC();
        GicFunctions gicFunctions = new GicFunctions(gic);
        gicFunctions.displayFirstFollow();
    }
}
