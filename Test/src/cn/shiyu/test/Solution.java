package cn.shiyu.test;

import cn.shiyu.bobo.WeightedEdge;

import java.util.PriorityQueue;
import java.util.Scanner;

import static java.lang.System.in;

public class Solution {
    static int cc[];
    static int[] parent;

    static class node implements Comparable<node> {
        public int s = 0, t = 0, w = 0;

        public node(int s, int t, int w) {
            this.s = s;
            this.t = t;
            this.w = w;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("node{");
            sb.append("s=").append(s);
            sb.append(", t=").append(t);
            sb.append(", w=").append(w);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public int compareTo(node node) {
            return w - node.w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        int N = sc.nextInt();
        int P = sc.nextInt();
        cc = new int[N + 1];
        parent = new int[N + 1];
        int minc=Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            cc[i] = sc.nextInt();
            minc=Math.min(minc,cc[i]);
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        int a, b, c = 0;
        for (int i = 0; i < P; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            pq.offer(new WeightedEdge(a, b, c * 2 + cc[a] + cc[b]));
        }
        int ans = 0;
            while (!pq.isEmpty()){
                WeightedEdge next = pq.remove();
                if (!isCo(next.getV(), next.getW())) {
                    ans += next.getWeight();
                    union(next.getV(), next.getW());
                }
            }
        System.out.println(ans+minc);

    }

    public static boolean isCo(int p, int q) {
        return find(p) == find(q);
    }

    public static void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
    }

    public static int find(int index) {
        if (index < 0 || index >= parent.length)
            return -1;
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

}