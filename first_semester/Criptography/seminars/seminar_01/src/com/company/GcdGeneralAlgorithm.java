package com.company;
/// General Approach for GCD of two numbers

import java.time.Duration;
import java.time.Instant;

public class GcdGeneralAlgorithm {

    public static void gcd(Long first, Long second) {
        /// running time
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

        // stores the minimum between the two numbers
        Long minimumValue = Math.min(first, second);

        Instant finish = Instant.now(); /// initialize the finish time

        // take first loop iterating through smaller number to 1
        for (Long index = minimumValue; index > 1; index--) {

            // checks if the current value of the index divides both numbers with remainder 0 if yes, then index is the
            // GCD of the two numbers
            if (first % index == 0 && second % index == 0) {
                finish = Instant.now();         /// the finish time
                long timeElapsed = Duration.between(start, finish).toNanosPart(); /// computes the effective time
                System.out.println("The running time in nanos is: " + timeElapsed);
                System.out.println("The gcd between the 2 numbers is: " + index);
                return;
            }
        }
        finish = Instant.now();         /// the finish time
        long timeElapsed = Duration.between(start, finish).toNanosPart();
        System.out.println("The running time in nanos is: " + timeElapsed);
        // if there are no common factors for the numbers, then GCD of them is 1
        System.out.println("The gcd between the 2 numbers is: 1");
    }
}
