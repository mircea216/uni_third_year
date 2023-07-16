package com.company;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    public static void showMenu() {
        System.out.println("1. Execute Tabu Search for instance 20 with iterations=10 and  tabuSize=4 ");
        System.out.println("2. Execute Tabu Search for instance 20 with iterations=100 and  tabuSize=3");
        System.out.println("3. Execute Tabu Search for instance 20 with iterations=1000 and  tabuSize=2");
        System.out.println("4. Execute Tabu Search for instance 200 with iterations=10 and  tabuSize=4");
        System.out.println("5. Execute Tabu Search for instance 200 with iterations=100 and  tabuSize=3");
        System.out.println("6. Execute Tabu Search for instance 200 with iterations=1000 and  tabuSize=2");
        System.out.println("7. Simulated annealing for lin105.tsp with maxIterations=100");
        System.out.println("8. Simulated annealing for lin105.tsp with maxIterations=1000");
        System.out.println("9. Simulated annealing for lin105.tsp with maxIterations=10000");
        System.out.println("x. EXIT");
    }

    public void run() {
        String option;

        label:
        while (true) {
            showMenu();
            System.out.println("Select the option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack("files/rucsac-20.txt", 10, 4);
                    }
                    break;
                case "2":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack("files/rucsac-20.txt", 100, 3);
                    }
                    break;
                case "3":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack("files/rucsac-20.txt", 1000, 2);
                    }
                    break;
                case "4":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack("files/rucsac-200.txt", 10, 4);
                    }
                    break;
                case "5":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack("files/rucsac-200.txt", 100, 3);
                    }
                    break;
                case "6":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack("files/rucsac-200.txt", 1000, 2);
                    }
                    break;
                case "7":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP(100, 10000, 0.00001, 0.99);
                    }
                    break;
                case "8":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP(1000, 10000, 0.00001, 0.99);
                    }
                    break;
                case "9":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP(10000, 10000, 0.00001, 0.99);
                    }
                    break;
                case "10":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP(10000, 10000, 0.001, 0.99);
                    }
                    break;
                case "11":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP(10000, 10000, 0.00001, 0.05);
                    }
                    break;
                case "12":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP(10000, 3000, 0.00001, 0.99);
                    }
                    break;
                case "x":
                    break label;
                default:
                    System.out.println("The selected option is invalid!");
                    break;
            }
        }
    }

    private void viewSolutionForKnapsack(String fileName, int iterations, int tabuSize) {
        try {
            InstanceKnapsackProblemHandler instanceProblemHandler = new InstanceKnapsackProblemHandler();
            instanceProblemHandler.parseInstanceFile(fileName);
            TabuSearchForKnapsackProblem tabuSearch = new TabuSearchForKnapsackProblem(instanceProblemHandler.getValues(),
                    instanceProblemHandler.getWeights(), instanceProblemHandler.getCapacity(),
                    tabuSize, iterations, instanceProblemHandler.getNumberOfObjects());
            tabuSearch.tabuSearch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewSolutionForTSP(int maxIterations, double INITIAL_TEMPERATURE, double FINAL_TEMPERATURE, double COOLING_FACTOR) {
        try {
            TSPSimulatedAnnealing tspSimulatedAnnealing = new TSPSimulatedAnnealing(maxIterations, INITIAL_TEMPERATURE,
                    FINAL_TEMPERATURE, COOLING_FACTOR);
            tspSimulatedAnnealing.simulatedAnnealing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
