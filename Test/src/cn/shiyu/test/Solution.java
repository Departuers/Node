package cn.shiyu.test;


import java.util.ArrayDeque;

public class Solution {
    public static boolean isPalindrome(int x) {
        return new StringBuilder().append(x).reverse().toString().equals(Integer.toString(x));
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        a.push(123);
        a.push(1234);
        a.push(222);
        System.out.println(a.pop());
    }
}