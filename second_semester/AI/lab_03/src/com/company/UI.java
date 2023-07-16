package com.company;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    public static void showMenu() {
        System.out.println("1. Execute EA for instance 20 with ONE CROSSOVER POINT");
        System.out.println("2. Execute EA for instance 20 with TWO CROSSOVER POINT");
        System.out.println("3. Execute EA for instance 200 with ONE CROSSOVER POINT");
        System.out.println("4. Execute EA for instance 200 with TWO CROSSOVER POINT");
        System.out.println("5. Execute EA for lin105.tsp with ORDER CROSSOVER");
        System.out.println("6. Execute EA for lin105.tsp with PARTIALLY MAPPED CROSSOVER");
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
                        viewSolutionForKnapsack1("files/rucsac-20.txt", 1000, 1000
                        );
                    }
                    break;
                case "2":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack2("files/rucsac-20.txt", 1000, 1000
                        );
                    }
                    break;
                case "3":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack1("files/rucsac-200.txt", 1000, 1000
                        );
                    }
                    break;
                case "4":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForKnapsack2("files/rucsac-200.txt", 1000, 1000
                        );
                    }
                    break;
                case "5":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP1(100, 100);
                    }
                    break;
                case "6":
                    for (int i = 0; i < 10; i++) {
                        viewSolutionForTSP2(100, 100);
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

    private void viewSolutionForKnapsack1(String fileName, int populationSize, int maxGenerations) {
        try {
            InstanceKnapsackProblemHandler instanceProblemHandler = new InstanceKnapsackProblemHandler();
            instanceProblemHandler.parseInstanceFile(fileName);
            EvolutiveAlgorithmForKnapsack evolutiveAlgorithmForKnapsack = new EvolutiveAlgorithmForKnapsack(
                    instanceProblemHandler.getValues(), instanceProblemHandler.getWeights(),
                    instanceProblemHandler.getCapacity(), populationSize, maxGenerations,
                    0.9, 0.1);
            evolutiveAlgorithmForKnapsack.evolutiveAlgorithm(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewSolutionForKnapsack2(String fileName, int populationSize, int maxGenerations) {
        try {
            InstanceKnapsackProblemHandler instanceProblemHandler = new InstanceKnapsackProblemHandler();
            instanceProblemHandler.parseInstanceFile(fileName);
            EvolutiveAlgorithmForKnapsack evolutiveAlgorithmForKnapsack = new EvolutiveAlgorithmForKnapsack(
                    instanceProblemHandler.getValues(), instanceProblemHandler.getWeights(),
                    instanceProblemHandler.getCapacity(), populationSize, maxGenerations,
                    0.9, 0.1);
            evolutiveAlgorithmForKnapsack.evolutiveAlgorithm(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewSolutionForTSP1(int populationSize, int maxGenerations) {
        try {
            EvolutiveAlgorithmForTSP evolutiveAlgorithmForTSP = new EvolutiveAlgorithmForTSP(populationSize,
                    maxGenerations);
            evolutiveAlgorithmForTSP.evolutiveAlgorithm(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void viewSolutionForTSP2(int populationSize, int maxGenerations) {
        try {
            EvolutiveAlgorithmForTSP evolutiveAlgorithmForTSP = new EvolutiveAlgorithmForTSP(populationSize,
                    maxGenerations);
            evolutiveAlgorithmForTSP.evolutiveAlgorithm(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
