package com.company;

public class Main {

    public static void sieveOfEratosthenes(int bound) {
        // Create a boolean vector isPrime[0 ... bound] and initialize all entries it as true. A value in isPrime[i] will
        // be false if it isn't a prime value, else true.

        boolean[] isPrime = new boolean[bound + 1];
        for (int i = 0; i <= bound; i++)
            isPrime[i] = true;

        /// loop that goes from 2 to the square root of the bound

        for (int index = 2; index * index <= bound; index++) {
            // If isPrime[index] is not changed, then it is a isPrime
            if (isPrime[index]) {
                /// The loop goes from the first multiple of the index and marks all its multiples as false (2,3,5...)
                for (int i = index * index; i <= bound; i += index)
                    isPrime[i] = false;
            }
        }
        System.out.println("The prime numbers lower than " + bound + " are:");
        // Print all prime numbers
        for (int i = 2; i <= bound; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        sieveOfEratosthenes(300);
    }
}
