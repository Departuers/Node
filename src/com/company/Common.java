package com.company;

public class Common {
    public static void out(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "->");
        }
        System.out.println("the end");
    }
    public static int[] random(int size,int range) {
        int[] result = new int[size];
        for (int i = 0; i <size; i++) {
            result[i]= (int) (Math.random() * range);
        }
        return result;
    }
}