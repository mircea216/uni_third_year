package com.company;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        BigInteger[] key = Rabin.generateKey(512);
        BigInteger n = key[0];
        BigInteger p = key[1];
        BigInteger q = key[2];
        String finalMessage = null;
        String s = "Hello";

        System.out.println("Message sent by sender : " + s);

        BigInteger m
                = new BigInteger(
                s.getBytes(
                        StandardCharsets.US_ASCII));
        BigInteger c = Rabin.encryptHandler(m, n);

        System.out.println("Encrypted Message : " + c);

        /// call decrypt with encryption, second part of secret key and third part (p - q)
        BigInteger[] m2 = Rabin.decryptHandler(c, p, q);
        for (BigInteger b : m2) {
            String dec = new String(
                    b.toByteArray(),
                    StandardCharsets.US_ASCII);
            if (dec.equals(s)) {
                finalMessage = dec;
            }
        }
        System.out.println(
                "Message received by Receiver : "
                        + finalMessage);
    }
}
