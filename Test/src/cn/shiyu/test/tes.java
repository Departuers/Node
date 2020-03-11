package cn.shiyu.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.in;

class tes {
    static int n, m;
    static int[] u;
    static int[] v;
    static int[] w;
    static int[] dis;
    static int[] first;
    static int[] next;
    static Queue<Integer> q = new LinkedList<Integer>();
    static boolean[] inq;

    public static void main(String[] args) {
        int i;
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        m = sc.nextInt();
        n++;
        m++;
        u = new int[m];
        v = new int[m];
        w = new int[m];
        first = new int[n];
        next = new int[m];
        dis = new int[n];
        inq = new boolean[n];
        for (i = 1; i < n; i++)
            first[i] = -1;
        for (i = 1; i < m; i++) {
            u[i] = sc.nextInt();
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            next[i] = first[u[i]];
            first[u[i]] = i;
        }
        spfa(1);
        for (i = 2; i < n; i++)
            System.out.println(dis[i]);
    }

    public static void spfa(int s) {
        int i, x;
        for (i = 2; i < n; i++)
            dis[i] = Integer.MAX_VALUE;
        q.offer(s);
        while (!q.isEmpty()) {
            x = q.poll();
            inq[x] = false;
            for (i = first[x]; i != -1; i = next[i])
                if (dis[v[i]] > dis[x] + w[i]) {
                    dis[v[i]] = dis[x] + w[i];
                    if (!inq[v[i]]) {
                        inq[v[i]] = true;
                        q.offer(v[i]);
                    }
                }
        }
    }
}
