package com.company;
/// Euclid Algorithm for GCD of two numbers using repeated subtractions
/// Working for numbers of arbitrary size

import java.time.Duration;
import java.time.Instant;

public class GcdRepeatedSubtractions {

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


        while (second != 0) {  /// whereas the number is different from 0
            if (first > second) {
                first = first - second;  /// if the first one is greater than the second one we subtract from the first
                /// one the second one
            } else {
                second = second - first; /// otherwise, I subtract from the second one the first one
            }

            /// the loop ends when second becomes 0 and first the GCD => I return it
            /// eg. 15 and 10 => i) first = 15-10=5 second = 5-5=0 => gcd = 5
        }
        Instant finish = Instant.now(); /// finish time
        long timeElapsed = Duration.between(start, finish).toNanosPart(); /// the running time is computed here
        System.out.println("The running time in nanos is: " + timeElapsed);
        System.out.println("The gcd between the 2 numbers is: " + first); /// the gcd stays in the first one
    }
}

