package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class KnapsackProblem {
    public static List<Integer> generateRandomSolution(int n) {
        // generates a list of n number either 0 or 1 depending on the remainder of the integer divided by 2
        List<Integer> randomSolution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Random rand = new Random();
            int randomValue = rand.nextInt() % 2;
            if (randomValue < 0) {
                randomSolution.add(randomValue * -1);
            } else {
                randomSolution.add(randomValue);
            }
        }
        return randomSolution;
    }

    public static boolean isValid(List<Integer> weights, List<Integer> solution, int capacity) {
        int weight = 0;
        for (int i = 0; i < weights.size(); i++) {
            weight += solution.get(i) * weights.get(i);
        }
        return (weight <= capacity);
    }

    public static int evaluateFitness(List<Integer> solution, List<Integer> values) {
        return IntStream.range(0, solution.size())
                .map(i -> solution.get(i) * values.get(i))
                .sum();
    }

    public static int computeWeight(List<Integer> solution, List<Integer> weights) {
        return IntStream.range(0, weights.size())
                .map(i -> solution.get(i) * weights.get(i))
                .sum();
    }

    public static void generateKValidSolutions(int k, List<Integer> values, List<Integer> weights, int capacity) {
        int index = 0;
        int maximValue = 0;
        List<Integer> bestSolution = new ArrayList<>();
        while (index < k) {
            List<Integer> solution = generateRandomSolution(weights.size());
            if (isValid(weights, solution, capacity)) {
                index++;
                int value = evaluateFitness(solution, values);
                if (value > maximValue) {
                    maximValue = value;
                    bestSolution = solution;
                }
            }
        }
        System.out.println("The best solution has the fitness (weight & fitnessValue): " +
                computeWeight(bestSolution, weights) + " " + maximValue);
    }
}