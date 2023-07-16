package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Rabin {
    /// declare used constants - one random number and 2,3,4
    private static final Random randomNumber = new SecureRandom();
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FOUR = BigInteger.valueOf(4);

    public static BigInteger[] generateKey(int bitLength)
    {
        // find the two close primes p and q so that pq=n
        BigInteger p = createPrime(bitLength / 2);
        BigInteger q = createPrime(bitLength / 2);
        BigInteger N = p.multiply(q); // N=p*q
        return new BigInteger[] { N, p, q };
    }

    public static BigInteger encryptHandler(BigInteger m,
                                            BigInteger N)
    {
        // encrypts the message this way
        // modPow Returns a BigInteger whose value is (N^2 % m).
        return m.modPow(TWO, N);
    }

    public static BigInteger[] decryptHandler(BigInteger c,
                                              BigInteger p,
                                              BigInteger q)
    {
        // n = p*q
        BigInteger N = p.multiply(q);

        /// compute p1, p2, q1, q2
        /// Chinese remainder theorem
        BigInteger p1 = c.modPow(p
                        .add(BigInteger.ONE)
                        .divide(FOUR),
                p);   // p1 = [((p+1)/4)^p] % c

        BigInteger p2 = p.subtract(p1); // p2 = p - p1
        BigInteger q1 = c.modPow(q
                        .add(BigInteger.ONE)
                        .divide(FOUR),
                q);     // q1 =  [((q+1))^q/4] % c
        BigInteger q2 = q.subtract(q1);  // q2 = q-q1

        /// compute gcd of p q
        BigInteger[] ext = Gcd(p, q);
        BigInteger y_p = ext[1];
        BigInteger y_q = ext[2];

        /// we compute m1...m4 by the following rule - [y_p * p * q i + (y_q * q * p i)] % N - i = 1, 2
        BigInteger m1 = y_p.multiply(p)
                .multiply(q1)
                .add(y_q.multiply(q)
                        .multiply(p1))
                .mod(N);
        BigInteger m2 = y_p.multiply(p)
                .multiply(q2)
                .add(y_q.multiply(q)
                        .multiply(p1))
                .mod(N);
        BigInteger m3 = y_p.multiply(p)
                .multiply(q1)
                .add(y_q.multiply(q)
                        .multiply(p2))
                .mod(N);
        BigInteger m4 = y_p.multiply(p)
                .multiply(q2)
                .add(y_q.multiply(q)
                        .multiply(p2))
                .mod(N);

        return new BigInteger[] {m1, m2, m3, m4 };
    }

    public static BigInteger[] Gcd(BigInteger a, BigInteger b)
    {
        /// compute the gcd of the two big integers
        BigInteger s = BigInteger.ZERO;
        BigInteger old_s = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;
        BigInteger old_t = BigInteger.ZERO;
        BigInteger r = b;
        BigInteger old_r = a;
        while (!r.equals(BigInteger.ZERO)) {
            BigInteger q = old_r.divide(r);
            BigInteger tr = r;
            r = old_r.subtract(q.multiply(r));
            old_r = tr;

            BigInteger ts = s;
            s = old_s.subtract(q.multiply(s));
            old_s = ts;

            BigInteger tt = t;
            t = old_t.subtract(q.multiply(t));
            old_t = tt;
        }
        return new BigInteger[] { old_r, old_s, old_t };
    }

    public static BigInteger createPrime(int bigLength)
    {
        // helper to find p and q
        BigInteger probablePrime;
        do {
            /// Returns a positive BigInteger that is probably prime, with the specified bigLength
            probablePrime = BigInteger.probablePrime(bigLength, randomNumber);
        } while (!probablePrime.mod(FOUR).equals(THREE));
        return probablePrime;
    }
}
