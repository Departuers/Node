package cn.shiyu.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//合根植物
public class Test {
    public static int[] parent;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        parent = new int[m * n + 1];
        for (int j = 1; j < parent.length; j++) {
            parent[j] = j;
        }
        for (int i = 0; i < k; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            union(a, b);
        }
        scanner.close();
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < parent.length; i++) {
            set.add(find(i));
        }
        System.out.println(set.size());
        for (int i = 0; i < parent.length; i++) {
            System.out.println(i+"  "+parent[i]);
        }
    }

    public static void union(int p, int q) {
        int proot = find(p);
        int qroot = find(q);
        if (qroot == proot)
            return;
        parent[proot] = qroot;
    }

    public static int find(int index) {
        if (index < 0 || index > parent.length)
            return -1;
        while (index != parent[index]) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}
