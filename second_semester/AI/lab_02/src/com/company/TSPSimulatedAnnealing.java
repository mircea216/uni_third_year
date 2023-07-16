package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TSPSimulatedAnnealing {

    private final double INITIAL_TEMPERATURE;
    private final double FINAL_TEMPERATURE;
    private final double COOLING_FACTOR;
    private static final String SPACE = " ";

    private final int maxIterations;

    private int numberOfCities;
    private double[][] distances;   // Distances between cities matrix

    public TSPSimulatedAnnealing(int maxIterations, double INITIAL_TEMPERATURE, double FINAL_TEMPERATURE, double
            COOLING_FACTOR) throws IOException {
        this.maxIterations = maxIterations;
        this.FINAL_TEMPERATURE = FINAL_TEMPERATURE;
        this.COOLING_FACTOR = COOLING_FACTOR;
        this.INITIAL_TEMPERATURE = INITIAL_TEMPERATURE;
        readDataFromFile();
    }

    // Method to read data from file
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

    // Method to initialize a random tour
    private int[] generateRandomSolution() {
        List<Integer> tourList = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            tourList.add(i);
        }
        // Randomize the solution
        Collections.shuffle(tourList, new Random());
        int[] tour = new int[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            tour[i] = tourList.get(i);
        }
        return tour;
    }

    // Method to calculate the total distance of a tour
    private double calculateTourDistance(int[] tour) {
        double distance = 0;
        for (int i = 0; i < numberOfCities - 1; i++) {
            distance += distances[tour[i]][tour[i + 1]];
        }
        distance += distances[tour[numberOfCities - 1]][tour[0]];  // Return to the first city
        return distance;
    }

    // Method to simulate annealing to find the shortest TSP tour
    public void simulatedAnnealing() {
        // Initialize tour with a random permutation of cities
        // Current best tour
        int[] tour = generateRandomSolution();
        // Current best distance
        double bestDistance = calculateTourDistance(tour);
        // Simulated annealing loop
        double temperature = INITIAL_TEMPERATURE;
        for (int iteration = 0; iteration < maxIterations && temperature > FINAL_TEMPERATURE; iteration++) {
            // Generate a random neighbor
            int[] neighbor = generateNeighborWith2Swap(tour);
            double neighborDistance = calculateTourDistance(neighbor);
            // Decide whether to accept the neighbor
            double deltaDistance = neighborDistance - bestDistance;
            // Check that either delta is lower than 0 or e^(-delta/temperature) > random number
            if (deltaDistance < 0 || Math.exp(-deltaDistance / temperature) > Math.random()) {
                tour = neighbor;
                bestDistance = neighborDistance;
            }
            // Update the temperature
            temperature *= COOLING_FACTOR;
        }
        System.out.println((int) bestDistance);
    }


    // Method to generate a random neighbor
    private int[] generateNeighborWith2Swap(int[] tour) {
        int i = (int) (Math.random() * (numberOfCities - 1));
        int j = i + 1 + (int) (Math.random() * (numberOfCities - i - 1));
        int[] neighbor = Arrays.copyOf(tour, numberOfCities);
        // Perform 2-swap switch
        while (i < j) {
            int temp = neighbor[i];
            neighbor[i] = neighbor[j];
            neighbor[j] = temp;
            i++;
            j--;
        }
        return neighbor;
    }
}