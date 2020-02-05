package cn.shiyu.test;


import java.util.TreeSet;

public class Solution {
    public static boolean isPalindrome(int x) {
        return new StringBuilder().append(x).reverse().toString().equals(Integer.toString(x));
    }

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(15);
        set.add(15);
        set.add(18);
        set.add(19);
        set.add(19);
        set.add(20);

        set.add(13);
        set.add(21);

        for (int x:set){
            System.out.println(x);
        }
    }
}