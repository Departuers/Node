package cn.shiyu.utils;

import java.util.Random;
import java.util.TooManyListenersException;

public class Common {

    public static int[] random(int size, int capacity) {
        int[] temp = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            temp[i] = r.nextInt(capacity);
        }
        /**
         *测试生成的数据
         */
//        for (int i = 0; i < size; i++) {
//            System.out.print(temp[i]);
//            if (i!=temp.length-1)
//                System.out.print("=>");
//            else
//            System.out.println();
//        }

        return temp;
    }
}
