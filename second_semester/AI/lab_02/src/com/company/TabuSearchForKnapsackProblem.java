package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TabuSearchForKnapsackProblem {
    private final List<Integer> values;
    private final List<Integer> weights;
    private final int capacity;
    private final int tabuSize;
    private final int maxIterations;
    private final int numberOfObjects;

    public TabuSearchForKnapsackProblem(List<Integer> values, List<Integer> weights, int capacity, int tabuSize,
                                        int maxIterations, int numberOfObjects) {
        this.values = values;
        this.weights = weights;
        this.capacity = capacity;
        this.tabuSize = tabuSize;
        this.maxIterations = maxIterations;
        this.numberOfObjects = numberOfObjects;
    }

    public void tabuSearch() {
        int[] currentSolution = generateRandomValidSolution();
        int[] best = Arrays.copyOf(currentSolution, currentSolution.length);
        int[] memoryList = initMemory(numberOfObjects);
        for (int i = 0; i < maxIterations; i++) {
            int[] xSolution = getBestNeighbourNonTabu(currentSolution, memoryList);
            updateMemory(memoryList, xSolution, currentSolution, tabuSize);
            currentSolution = xSolution;
            if (computeFitnessValue(currentSolution) > computeFitnessValue(best) && isValid(currentSolution)) {
                best = Arrays.copyOf(currentSolution, currentSolution.length);
            }
        }
        System.out.println("The best value is: " + computeFitnessValue(best));
    }

    private int[] initMemory(int numberOfObjects) {
        return new int[numberOfObjects];
    }

    private void updateMemory(int[] memoryList, int[] xSolution, int[] currentSolution, int tabuSize) {
        for (int i = 0; i < memoryList.length; i++) {
            if (xSolution[i] != 0) {
                memoryList[i] = xSolution[i] - 1;
            } else {
                memoryList[i] = xSolution[i];
            }
        }
        int retrievedTerm = retrieveTabuTermChange(currentSolution, xSolution);
        memoryList[retrievedTerm] = tabuSize;
    }

    private int[] getBestNeighbourNonTabu(int[] solution, int[] memoryList) {
        List<int[]> neighborhood = getAllNeighborhoods(solution);
        List<int[]> nonTabuNeighborhood = new ArrayList<>();
        for (int i = 0; i < neighborhood.size(); i++) {
            if (memoryList[i] == 0) {
                nonTabuNeighborhood.add(neighborhood.get(i));
            }
        }
        return computeTheBestSolutionOfList(nonTabuNeighborhood);
    }

    private int retrieveTabuTermChange(int[] currentSolution, int[] xSolution) {
        for (int i = 0; i < currentSolution.length; i++) {
            if (currentSolution[i] != xSolution[i]) {
                return i;
            }
        }
        return -1;
    }

    private int[] generateRandomValidSolution() {
        Random random = new Random();
        int[] solution = new int[values.size()];
        boolean isValid = false;
        while (!isValid) {
            for (int i = 0; i < values.size(); i++) {
                solution[i] = random.nextInt(2);
            }
            isValid = isValid(solution);
        }
        return solution;
    }

    private boolean isValid(int[] solution) {
        int totalWeight = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == 1) {
                totalWeight += weights.get(i);
            }
        }
        return totalWeight <= capacity;
    }

    private int computeFitnessValue(int[] solution) {
        int totalValue = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == 1) {
                totalValue += values.get(i);
            }
        }
        return totalValue;
    }

    private List<int[]> getAllNeighborhoods(int[] solution) {
        List<int[]> neighborhood = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            generateNeighborhoodBy1Flip(solution, neighborhood, i);
        }
        return neighborhood;
    }

    private void generateNeighborhoodBy1Flip(int[] solution, List<int[]> neighborhood, int i) {
        int[] neighbor = Arrays.copyOf(solution, solution.length);
        neighbor[i] = 1 - neighbor[i];
        neighborhood.add(neighbor);
    }

    private int[] computeTheBestSolutionOfList(List<int[]> solutions) {
        int[] best = solutions.get(0);
        int bestFitness = computeFitnessValue(best);
        for (int i = 1; i < solutions.size(); i++) {
            int[] solution = solutions.get(i);
            int fitness = computeFitnessValue(solution);
            if (fitness > bestFitness) {
                best = solution;
                bestFitness = fitness;
            }
        }
        return best;
    }
}

