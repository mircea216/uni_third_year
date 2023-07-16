package com.company;

public class Main {

    public static void main(String[] args) {
        /// GENERAL APPROACH EGs
        System.out.println("General approach of GCD: ");
        GcdGeneralAlgorithm.gcd(0L, 0L);
        GcdGeneralAlgorithm.gcd(30000L, 500000L);
        GcdGeneralAlgorithm.gcd(170000000000000L, 169000001L);

        /// REPEATED - EGs

        System.out.println("Euclid Algorithm for GCD of two numbers using repeated subtractions");
        GcdGeneralAlgorithm.gcd(30000L, 500000L);
        GcdRepeatedSubtractions.gcd(3000000000L, 50000000000050L); /// the time for these with general approach would
        // be 6s
        GcdRepeatedSubtractions.gcd(600L, 6000L);
        GcdRepeatedSubtractions.gcd(600L, 1L);

        /// REPEATED % Egs

        System.out.println("Euclid Algorithm for GCD of two numbers using repeated divisions");
        GcdRepeatedDivisions.gcd(20L, 0L);
        GcdRepeatedDivisions.gcd(0L, 30L);
        GcdRepeatedDivisions.gcd(9000000090900000000L, 9000010970707070700L);
        GcdRepeatedDivisions.gcd(170L, 17L);
        GcdRepeatedDivisions.gcd(300L, 360L);
    }
}
