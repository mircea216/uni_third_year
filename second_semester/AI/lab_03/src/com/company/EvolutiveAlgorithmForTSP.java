package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EvolutiveAlgorithmForTSP {
    private static final String SPACE = " ";
    private final int populationSize;
    private final int maxGenerations;
    private int numberOfCities;
    private double[][] distances;
    private final List<int[]> population;

    public EvolutiveAlgorithmForTSP(int populationSize, int maxGenerations) throws IOException {
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        population = new ArrayList<>();
        readDataFromFile();
    }

    private void readDataFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("files/lin105.tsp"));
        ArrayList<double[]> citiesList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split(SPACE);
            double[] city = new double[2];
            city[0] = Double.parseDouble(tokens[1]);  // X-coordinate
            city[1] = Double.parseDouble(tokens[2]);  // Y-coordinate
            citiesList.add(city);
        }
        bufferedReader.close();
        numberOfCities = citiesList.size();
        distances = new double[numberOfCities][numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = i + 1; j < numberOfCities; j++) {
                // Euclidean distance
                double distance = Math.sqrt(Math.pow(citiesList.get(i)[0] - citiesList.get(j)[0], 2) +
                        Math.pow(citiesList.get(i)[1] - citiesList.get(j)[1], 2));
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
    }

    // Generate an initial population randomly
    public void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            int[] individual = new int[numberOfCities];
            for (int j = 0; j < numberOfCities; j++) {
                individual[j] = j;
            }
            Collections.shuffle(List.of(individual));
            population.add(individual);
        }
    }

    // Select parents using tournament selection
    public int[] selectParents() {
        int parent1Index = (int) (Math.random() * populationSize);
        int parent2Index = (int) (Math.random() * populationSize);
        while (parent2Index == parent1Index) {
            parent2Index = (int) (Math.random() * populationSize);
        }
        int[] parent1 = population.get(parent1Index);
        int[] parent2 = population.get(parent2Index);
        if (calculateFitness(parent1) < calculateFitness(parent2)) {
            return parent1;
        } else {
            return parent2;
        }
    }

    // Mutate an individual by swapping two cities
    public void mutate(int[] individual) {
        int cityIndex1 = (int) (Math.random() * numberOfCities);
        int cityIndex2 = (int) (Math.random() * numberOfCities);
        while (cityIndex2 == cityIndex1) {
            cityIndex2 = (int) (Math.random() * numberOfCities);
        }
        int temp = individual[cityIndex1];
        individual[cityIndex1] = individual[cityIndex2];
        individual[cityIndex2] = temp;
    }

    // Crossover two parents using order crossover
    public int[] orderCrossover(int[] parent1, int[] parent2) {
        // different numbers
        int crossoverPoint1 = (int) (Math.random() * numberOfCities);
        int crossoverPoint2 = (int) (Math.random() * numberOfCities);
        while (crossoverPoint2 == crossoverPoint1) {
            crossoverPoint2 = (int) (Math.random() * numberOfCities);
        }
        if (crossoverPoint1 > crossoverPoint2) {
            int temp = crossoverPoint1;
            crossoverPoint1 = crossoverPoint2;
            crossoverPoint2 = temp;
        }
        int[] child = new int[numberOfCities];
        Arrays.fill(child, -1);
        // fill the child from crossoverPoint1 to crossoverPoint2 with the parent1 values
        if (crossoverPoint2 + 1 - crossoverPoint1 >= 0)
            System.arraycopy(parent1, crossoverPoint1, child, crossoverPoint1, crossoverPoint2 + 1 - crossoverPoint1);
        int j = 0;
        for (int i = 0; i < numberOfCities; i++) {
            if (j == crossoverPoint1) {
                j = crossoverPoint2 + 1;
            }
            if (!containsCity(child, parent2[i])) {
                child[j] = parent2[i];
                j++;
            }
        }
        return child;
    }

    public int[] partiallyMappedCrossover(int[] parent1, int[] parent2) {
        int length = parent1.length;
        int[] child = new int[length];
        Arrays.fill(child, -1);

        int startPoint = (int) (Math.random() * length);
        int endPoint = (int) (Math.random() * (length - startPoint)) + startPoint;

        // copy the selected segment from parent1 to the child
        for (int i = startPoint; i <= endPoint; i++) {
            child[i] = parent1[i];
        }

        // fill the remaining positions of the child from parent2
        for (int i = 0; i < length; i++) {
            if (i < startPoint || i > endPoint) {
                int gene = parent2[i];

                // check if the gene is already present in the child
                while (containsCity(child, gene)) {
                    gene = parent2[getIndex(parent1, gene)];
                }
                child[i] = gene;
            }
        }

        return child;
    }


    // helper method to get the index of a given city in an array
    private int getIndex(int[] parent, int city) {
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == city) {
                return i;
            }
        }
        return -1;
    }

    // Select survivors
    public void selectSurvivors(List<int[]> offspring) {
        population.sort(Comparator.comparingDouble(this::calculateFitness));
        for (int i = 0; i < offspring.size(); i++) {
            population.set(populationSize - 1 - i, offspring.get(i));
        }
    }

    // Calculate the fitness of an individual
    public double calculateFitness(int[] tour) {
        double distance = 0;
        for (int i = 0; i < numberOfCities - 1; i++) {
            distance += distances[tour[i]][tour[i + 1]];
        }
        distance += distances[tour[numberOfCities - 1]][tour[0]];  // Return to the first city
        return distance;
    }

    // Check if an individual contains a city
    public boolean containsCity(int[] individual, int cityIndex) {
        for (int i = 0; i < numberOfCities; i++) {
            if (individual[i] == cityIndex) {
                return true;
            }
        }
        return false;
    }

    public void evolutiveAlgorithm(int option) {
        long startTime = System.nanoTime();
        System.out.println("Generation\tBest\tAverage");
        generateInitialPopulation();
        double bestFitness = Double.POSITIVE_INFINITY;
        for (int generation = 0; generation < maxGenerations; generation++) {
            List<int[]> offspring = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                int[] parent1 = selectParents();
                int[] parent2 = selectParents();
                int[] child = new int[0];
                if (option == 1) {
                    child = orderCrossover(parent1, parent2);
                } else if (option == 2) {
                    child = partiallyMappedCrossover(parent1, parent2);
                }
                if (Math.random() < 0.1) {
                    mutate(child);
                }
                offspring.add(child);
            }
            double bestGenerationFitness = Double.POSITIVE_INFINITY;
            double totalFitness = 0;
            for (int[] individual : offspring) {
                double fitness = calculateFitness(individual);
                totalFitness += fitness;
                if (fitness < bestGenerationFitness) {
                    bestGenerationFitness = fitness;
                }
            }
            double avgGenerationFitness = totalFitness / populationSize;
            selectSurvivors(offspring);
            if (bestGenerationFitness < bestFitness) {
                bestFitness = bestGenerationFitness;
            }
            System.out.println((generation + 1) + "\t\t" + (int) bestGenerationFitness + "\t" + (int) avgGenerationFitness);
        }
        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (double) (endTime - startTime) / 1_000_000_000.0;
        System.out.println("Time is: " + elapsedTimeInSeconds + " seconds");
    }
}