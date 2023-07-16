package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class KnapsackRandomHillClimbing {

    // Generate a random solution
    private static List<Integer> generateRandomSolution(int n) {
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

    // Generate a random neighbor solution by flipping a single bit
    private static List<Integer> generateNeighbor(List<Integer> solution) {
        List<Integer> neighbor = new ArrayList<>(solution);
        int index = (int) (Math.random() * solution.size());
        neighbor.set(index, 1 - neighbor.get(index));
        return neighbor;
    }

    public static void randomHillClimbing(int iterations, List<Integer> values, List<Integer> weights, int capacity) {
        List<Integer> bestSolution = new ArrayList<>();
        int bestFitness = 0;
        for (int i = 0; i < iterations; i++) {
            List<Integer> currentSolution = generateRandomSolution(values.size());
            int currentFitness = 0;
            if (evaluateValidity(currentSolution, weights, capacity)) {
                currentFitness = computeTotalValue(currentSolution, values);
            }
            List<Integer> neighborSolution = generateNeighbor(currentSolution);
            int neighborFitness = 0;
            if (evaluateValidity(neighborSolution, weights, capacity)) {
                neighborFitness = computeTotalValue(neighborSolution, values);
            }
            if (neighborFitness > currentFitness) {
                currentSolution = neighborSolution;
                currentFitness = neighborFitness;
            }

            if (currentFitness > bestFitness) {
                bestSolution = currentSolution;
                bestFitness = currentFitness;
            }
        }
        System.out.println("The best solution has the fitness (weight & fitnessValue): " +
                computeWeight(bestSolution, weights) + " " + bestFitness);
    }

    private static boolean evaluateValidity(List<Integer> solution, List<Integer> weights, int capacity) {
        int totalWeight = 0;
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == 1) {
                totalWeight += weights.get(i);
            }
        }
        return totalWeight <= capacity;
    }

    // computes the total value of a solution
    private static int computeTotalValue(List<Integer> solution, List<Integer> values) {
        return IntStream.range(0, solution.size())
                .filter(i -> solution.get(i) == 1)
                .map(values::get)
                .sum();
    }

    // computes weight
    public static int computeWeight(List<Integer> solution, List<Integer> weights) {
        return IntStream.range(0, weights.size())
                .map(i -> solution.get(i) * weights.get(i))
                .sum();
    }
}
