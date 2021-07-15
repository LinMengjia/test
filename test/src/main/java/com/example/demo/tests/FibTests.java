package com.example.demo.tests;

public class FibTests {
    public static long fibLoop(int num) {
        if (num < 1 || num > 92) {
            return 0;
        }
        long a = 1;
        long b = 1;
        long temp;
        for (int i = 2; i <= num; i++) {
            temp = a;
            a = b;
            b += temp;
        }
        return b;
    }

    public static long fibRec(int num) {
        if (num < 1) {
            return 0;
        }
        if (num < 3) {
            return 1;
        }
        return fibRec(num - 1) + fibRec(num - 2);
    }


    public static void main(String[] args) {
        System.out.println(fibLoop(5));
        System.out.println(fibRec(5));


    }
}
