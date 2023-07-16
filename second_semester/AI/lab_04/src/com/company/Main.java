package com.company;

public class Main {

    public static void main(String[] args) {
        EvolutiveOptimizationAckley evolutiveOptimizationAckley = new EvolutiveOptimizationAckley(1000, 10000);
        evolutiveOptimizationAckley.evolutiveAlgorithm();
    }
}
