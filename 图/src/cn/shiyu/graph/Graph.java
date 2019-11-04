package cn.shiyu.graph;

public class Graph {
    public static void main(String[] args) {
        int a[][] = {{1, 3, 4},
                {1, 3, 6},
                {1, 3, 7},
                {1, 3, 2}};
        System.out.println(a[2][2]);
        int c = 0;
        int e = 0;
        int b[][] = new int[3][4];
        for (int j = 0; j < b[0].length; j++) {
            for (int i = 0; i < b.length; i++) {
                b[i][j] = c++;
            }
        }


        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + "->");
            }
            System.out.println();
        }
        for (int i = 0; i < b[0].length; i++) {
            System.out.println(b[0][i]);
        }
    }
}