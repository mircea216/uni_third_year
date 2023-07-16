package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class EvolutiveAlgorithmForKnapsack {
    private final List<Integer> values;
    private final List<Integer> weights;
    private final int capacity;
    private final int populationSize;
    private final int maxGenerations;
    private final double crossoverRate;
    private final double mutationRate;
    private final Random rand = new Random();

    public EvolutiveAlgorithmForKnapsack(List<Integer> values, List<Integer> weights, int capacity, int populationSize,
                                         int maxGenerations, double crossoverRate, double mutationRate) {
        this.values = values;
        this.weights = weights;
        this.capacity = capacity;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    // Generate an initial population of valid solutions
    private List<List<Integer>> generateInitialPopulation() {
        List<List<Integer>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            List<Integer> solution = generateRandomValidSolution();
            population.add(solution);
        }
        return population;
    }

    // Evaluate the fitness of a solution

    // Select two parents for orderCrossover using tournament selection
    private List<List<Integer>> selectParents(List<List<Integer>> population, int tournamentSize) {
        List<List<Integer>> parents = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Integer> bestSolution = new ArrayList<>();
            int bestFitness = 0;
            for (int j = 0; j < tournamentSize; j++) {
                int index = rand.nextInt(population.size());
                List<Integer> solution = population.get(index);
                int fitness = evaluateFitness(solution);
                if (fitness > bestFitness) {
                    bestSolution = solution;
                    bestFitness = fitness;
                }
            }
            parents.add(bestSolution);
        }
        return parents;
    }

    // Perform single-point orderCrossover on the parents
    private List<List<Integer>> crossoverWithOneCutPoint(List<List<Integer>> parents) {
        List<List<Integer>> children = new ArrayList<>();
        children.add(new ArrayList<>());
        children.add(new ArrayList<>());
        if (rand.nextDouble() < crossoverRate) {
            int crossoverPoint = rand.nextInt(values.size() - 1) + 1;
            for (int i = 0; i < crossoverPoint; i++) {
                children.get(0).add(parents.get(0).get(i));
                children.get(1).add(parents.get(1).get(i));
            }
            for (int i = crossoverPoint; i < values.size(); i++) {
                children.get(0).add(parents.get(1).get(i));
                children.get(1).add(parents.get(0).get(i));
            }
        } else {
            children.set(0, parents.get(0));
            children.set(1, parents.get(1));
        }
        return children;
    }

    // Perform random mutation on a child
    private void mutate(List<Integer> child) {
        for (int i = 0; i < child.size(); i++) {
            if (rand.nextDouble() < mutationRate) {
                if (child.get(i) == 1) {
                    child.set(i, 0);
                } else {
                    child.set(i, 1);
                }
            }
        }
    }

    // Select survivors
    private List<List<Integer>> selectSurvivors(List<List<Integer>> population, List<List<Integer>> children) {
        List<List<Integer>> nextGeneration = new ArrayList<>(population);
        int worstIndex = 0;
        int worstFitness = Integer.MAX_VALUE;
        for (int i = 0; i < populationSize; i++) {
            int fitness = evaluateFitness(population.get(i));
            if (fitness < worstFitness) {
                worstIndex = i;
                worstFitness = fitness;
            }
        }
        for (int i = 0; i < 2; i++) {
            int index = rand.nextInt(populationSize);
            nextGeneration.set(index, children.get(i));
        }
        nextGeneration.set(worstIndex, children.get(0));
        return nextGeneration;
    }

    // Run the evolutive algorithm
    public void evolutiveAlgorithm(int option) {
        long startTime = System.nanoTime();
        List<List<Integer>> population = generateInitialPopulation();
        int bestFitness = 0;
        List<Integer> bestSolutions = new ArrayList<>();
        List<Double> averageFitness = new ArrayList<>();
        for (int i = 0; i < maxGenerations; i++) {
            List<List<Integer>> children = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                List<List<Integer>> parents = selectParents(population, 5);
                List<List<Integer>> offspring = new ArrayList<>();
                if (option == 1) offspring = crossoverWithTwoCutPoints(parents);
                else if (option == 2) offspring = crossoverWithOneCutPoint(parents);
                for (List<Integer> child : offspring) {
                    mutate(child);
                    children.add(child);
                }
            }
            population = selectSurvivors(population, children);
            int totalFitness = 0;
            for (List<Integer> solution : population) {
                int fitness = evaluateFitness(solution);
                if (fitness > bestFitness && isValid(solution)) {
                    bestFitness = fitness;
                }
                totalFitness += fitness;
            }
            double avgFitness = (double) totalFitness / population.size();
            bestSolutions.add(bestFitness);
            averageFitness.add(avgFitness);
        }
        System.out.println("Generation\tBest\tAverage");
        for (int i = 0; i < maxGenerations; i++) {
            System.out.println((i + 1) + "\t\t" + bestSolutions.get(i) + "\t" + averageFitness.get(i));
        }
        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (double) (endTime - startTime) / 1_000_000_000.0;
        System.out.println("Time is: " + elapsedTimeInSeconds + " seconds");
    }

    public boolean isValid(List<Integer> solution) {
        int weight = 0;
        for (int i = 0; i < weights.size(); i++) {
            weight += solution.get(i) * weights.get(i);
        }
        return (weight <= capacity);
    }

    public int evaluateFitness(List<Integer> solution) {
        return IntStream.range(0, solution.size())
                .map(i -> solution.get(i) * values.get(i))
                .sum();
    }

    private List<Integer> generateRandomValidSolution() {
        Random random = new Random();
        List<Integer> solution = new ArrayList<>();
        boolean isValidFlag = false;
        while (!isValidFlag) {
            solution.clear();
            for (int i = 0; i < values.size(); i++) {
                solution.add(random.nextInt(2));
            }
            isValidFlag = isValid(solution);
        }
        return solution;
    }

    private List<List<Integer>> crossoverWithTwoCutPoints(List<List<Integer>> parents) {
        List<List<Integer>> children = new ArrayList<>();
        children.add(new ArrayList<>());
        children.add(new ArrayList<>());
        if (rand.nextDouble() < crossoverRate) {
            int cutPoint1 = rand.nextInt(values.size() - 2) + 1;
            int cutPoint2 = rand.nextInt(values.size() - cutPoint1) + cutPoint1;
            for (int i = 0; i < cutPoint1; i++) {
                children.get(0).add(parents.get(0).get(i));
                children.get(1).add(parents.get(1).get(i));
            }
            for (int i = cutPoint1; i < cutPoint2; i++) {
                children.get(0).add(parents.get(1).get(i));
                children.get(1).add(parents.get(0).get(i));
            }
            for (int i = cutPoint2; i < values.size(); i++) {
                children.get(0).add(parents.get(0).get(i));
                children.get(1).add(parents.get(1).get(i));
            }
        } else {
            children.set(0, parents.get(0));
            children.set(1, parents.get(1));
        }
        return children;
    }
}

