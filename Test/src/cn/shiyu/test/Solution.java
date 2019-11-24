package cn.shiyu.test;

import java.util.stream.IntStream;

public class Solution {
    public static boolean isPalindrome(int x) {
        return new StringBuilder().append(x).reverse().toString().equals(Integer.toString(x));
    }

    public static void main(String[] args) {
        int[] num = {3, 4, 1141, 13, 14, 12, 231, 23, 12, 4, 12, 3, 1244};
        int asInt = IntStream.of(num).parallel().min().getAsInt();
        System.out.println(asInt);
    }
}