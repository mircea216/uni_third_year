package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void showMenu() {
        System.out.println("1. Test solutions for the first instance - 20 for k=10 / it = 10");
        System.out.println("2. Test solutions for the first instance - 20 for k=100 / it = 100");
        System.out.println("3. Test solutions for the first instance - 20 for k=1000 / it = 1000");
        System.out.println("4. Test solutions for the second instance - 200 for k=10 / it = 10");
        System.out.println("5. Test solutions for the second instance - 200 for k=100 / it = 100");
        System.out.println("6. Test solutions for the second instance - 200 for k=1000 / it = 1000");
        System.out.println("7. Test for a small instance");
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
                    viewSolution("files/rucsac-20.txt", 10, 10);
                    break;
                case "2":
                    viewSolution("files/rucsac-20.txt", 100, 100);
                    break;
                case "3":
                    viewSolution("files/rucsac-20.txt", 1000, 100);
                    break;
                case "4":
                    viewSolution("files/rucsac-200.txt", 10, 10);
                    break;
                case "5":
                    viewSolution("files/rucsac-200.txt", 100, 100);
                    break;
                case "6":
                    viewSolution("files/rucsac-200.txt", 1000, 1000);
                    break;
                case "7":
                    List<Integer> values = new ArrayList<>(List.of(17, 8, 2, 3, 4, 6));
                    List<Integer> weights = new ArrayList<>(List.of(7, 2, 4, 6, 8, 1));
                    System.out.println("KNAPSACK BASIC");
                    KnapsackProblem.generateKValidSolutions(5, values, weights, 21);
                    System.out.println("KNAPSACK WITH RHC");
                    KnapsackRandomHillClimbing.randomHillClimbing(1, values, weights, 20);
                    break;
                case "x":
                    break label;
                default:
                    System.out.println("The selected option is invalid!");
                    break;
            }
        }
    }

    private void viewSolution(String fileName, int k, int iterations) {
        try {
            InstanceProblemHandler instanceProblemHandler = new InstanceProblemHandler();
            instanceProblemHandler.parseInstanceFile(fileName);
            System.out.println("KNAPSACK BASIC");
            KnapsackProblem.generateKValidSolutions(k, instanceProblemHandler.getValues(),
                    instanceProblemHandler.getWeights(),
                    instanceProblemHandler.getCapacity());
            System.out.println("KNAPSACK WITH RHC");
            KnapsackRandomHillClimbing.randomHillClimbing(iterations, instanceProblemHandler.getValues(),
                    instanceProblemHandler.getWeights(),
                    instanceProblemHandler.getCapacity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
