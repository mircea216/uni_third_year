package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InstanceKnapsackProblemHandler {

    private final List<Integer> values = new ArrayList<>();
    private final List<Integer> weights = new ArrayList<>();
    private int capacity;
    private int numberOfObjects;
    private static final String REGEX_SPACE = "\\s+";

    public void parseInstanceFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        int totalNumberOfObjects = Integer.parseInt(bufferedReader.readLine().trim());
        numberOfObjects = totalNumberOfObjects;
        String line;
        int counter = 0;
        while ((line = bufferedReader.readLine()) != null && counter < totalNumberOfObjects) {
            counter++;
            String[] tokens = line.trim().split(REGEX_SPACE);
            this.values.add(Integer.parseInt(tokens[1]));
            this.weights.add(Integer.parseInt(tokens[2]));
        }
        this.capacity = Objects.nonNull(line) ? Integer.parseInt(line) : 0;
        bufferedReader.close();
    }

    public List<Integer> getValues() {
        return values;
    }

    public List<Integer> getWeights() {
        return weights;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfObjects() {
        return numberOfObjects;
    }
}
