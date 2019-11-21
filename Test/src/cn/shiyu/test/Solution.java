package cn.shiyu.test;

public class Solution {
    public static boolean isPalindrome(int x) {
        return new StringBuilder().append(x).reverse().toString().equals(Integer.toString(x));
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}