package com.company;

import java.util.Arrays;
import java.util.Random;

public class EvolutiveOptimizationAckley {
    public EvolutiveOptimizationAckley(int populationSize, int maxGenerations) {
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
    }

    private final int populationSize; // Number of individuals in population
    private final int maxGenerations; // Maximum number of generations
    private static final double MUTATION_RATE = 0.01; // Probability of mutation

    private static final int DIMENSIONS = 2; // Number of dimensions of the Ackley function
    private static final double LOWER_BOUND = -32.768; // Lower bound of the search space
    private static final double UPPER_BOUND = 32.768; // Upper bound of the search space

    private static final double ACKLEY_A = 20;
    private static final double ACKLEY_B = 0.2;
    private static final double ACKLEY_C = 2 * Math.PI;

    private static final Random random = new Random();

    public void evolutiveAlgorithm() {
        double[][] population = initializePopulation();
        int generation = 0;

        while (generation < maxGenerations) {
            double[][] offspring = createOffspring(population);
            population = selectSurvivors(population, offspring);
            generation++;

            if (generation % 100 == 0) {
                double bestFitness = evaluateFitness(population[0]);
                System.out.println("Generation " + generation + ", Best Fitness: " + bestFitness);
            }
        }

        double[] bestIndividual = population[0];
        double bestFitness = evaluateFitness(bestIndividual);
        System.out.println("Best Individual: " + Arrays.toString(bestIndividual) + ", Best Fitness: " + bestFitness);
    }

    private double[][] initializePopulation() {
        double[][] population = new double[populationSize][DIMENSIONS];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < DIMENSIONS; j++) {
                population[i][j] = LOWER_BOUND + random.nextDouble() * (UPPER_BOUND - LOWER_BOUND);
            }
        }
        return population;
    }

    private double[][] createOffspring(double[][] population) {
        double[][] offspring = new double[populationSize][DIMENSIONS];

        for (int i = 0; i < populationSize; i++) {
            double[] parent1 = population[random.nextInt(populationSize)];
            double[] parent2 = population[random.nextInt(populationSize)];
            double[] child = crossover(parent1, parent2);
            mutate(child);
            offspring[i] = child;
        }

        return offspring;
    }

    private double[] crossover(double[] parent1, double[] parent2) {
        double[] child = new double[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            if (random.nextBoolean()) {
                child[i] = parent1[i];
            } else {
                child[i] = parent2[i];
            }
        }
        return child;
    }

    private void mutate(double[] individual) {
        for (int i = 0; i < DIMENSIONS; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                individual[i] += random.nextGaussian() * (UPPER_BOUND - LOWER_BOUND) / 10;
            }
        }
    }

    private double[][] selectSurvivors(double[][] population, double[][] offspring) {
        double[][] combinedPopulation = new double[populationSize * 2][DIMENSIONS];
        for (int i = 0; i < populationSize; i++) {
            combinedPopulation[i] = population[i];
            combinedPopulation[i + populationSize] = offspring[i];
        }

        Arrays.sort(combinedPopulation, (individual1, individual2) -> {
            double fitness1 = evaluateFitness(individual1);
            double fitness2 = evaluateFitness(individual2);
            return Double.compare(fitness1, fitness2);
        });

        return Arrays.copyOfRange(combinedPopulation, 0, populationSize);
    }

    private double evaluateFitness(double[] individual) {
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < DIMENSIONS; i++) {
            double x = individual[i];
            sum1 += x * x;
            sum2 += Math.cos(ACKLEY_C * x);
        }
        double term1 = -ACKLEY_A * Math.exp(-ACKLEY_B * Math.sqrt(sum1 / DIMENSIONS));
        double term2 = -Math.exp(sum2 / DIMENSIONS);
        return term1 + term2 + ACKLEY_A + Math.exp(1);
    }
}
