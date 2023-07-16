package com.company;
/// Euclid Algorithm for GCD of two numbers using repeated subtractions


import java.time.Duration;
import java.time.Instant;

public class GcdRepeatedDivisions {
    public static void gcd(Long first, Long second) {
        Instant start = Instant.now();

        if (first == 0 && second == 0) { /// cannot compute gcd between 0 and 0
            System.out.println("The gcd between 0 and 0 is not defined!");
            return;
        }
        if (first == 0) { // if the first number is 0 I return the second one
            System.out.println("The gcd between the 2 numbers is: " + second);
            return;
        }
        if (second == 0) {// if the second number is 0 I return the first one
            System.out.println("The gcd between the 2 numbers is: " + first);
            return;
        }

        Long remainderValue;
        while ((first % second) > 0) {
            remainderValue = first % second; /// the remainderValue takes the remainder of the division
            first = second;     /// first takes the second
            second = remainderValue;    /// second takes the remainder as long as the remainder is greater than 0
        }

        /// ex first=20 second=30
        /// rem = 20 first = 30 second =20 | rem=10 first=20 second=20 20%10==0 => gcd = second =10

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanosPart();
        System.out.println("The running time in nanos is: " + timeElapsed); /// running time
        System.out.println("The gcd between the 2 numbers is: " + second);

    }
}
