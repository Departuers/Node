package cn.shiyu.bobo.directed.ShortestPath;

import java.util.Arrays;
import java.util.LinkedList;

//SPFA算法+链式前向星建图!
public class 链式前向星 {
    static class node {
        int w, to, next;//w是权值,e是v边

        //next[i]表示与第i条边同起点的上一条边在edge数组的储存索引位置
        public node(int w, int to, int next) {
            this.w = w;
            this.to = to;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("w=").append(w);
            sb.append(", e=").append(to);
            sb.append(", next=").append(next);
            sb.append('}');
            return sb.toString();
        }
    }

    static node[] edge = new node[20];

    public static void main(String[] args) {
        add(1, 6, 1);
        add(3, 1, 7);
        add(2, 4, 7);
        add(2, 6, 7);
        add(1, 4, 6);
        add(1, 2, 1);
        add(2, 3, 1);
        int start = 4;
        for (int i = head[start]; i != 0; i = edge[i].next) {
            System.out.println(start + "->" + edge[i].to + "权值" + edge[i].w);
        }
        System.out.println(Arrays.toString(head));
    }

    static int cnt = 1;
    static int[] head = new int[30];//边是按顺序添加的,head[i]记录,以i为起点,存储在edge[]数组的起始位置

    static void add(int u, int v, int w) {//非常巧妙...
        edge[cnt] = new node(w, v, head[u]);//有向图增加u-v这条边
        head[u] = cnt++;
        edge[cnt] = new node(w, u, head[v]); //无向图增加v-u这条边
        head[v] = cnt++;
    }

    static int[] dis = new int[30];
    static boolean[] visited = new boolean[30];

    static void Spfa(int s) {
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        int v = 0;
        while (!queue.isEmpty()) {
            Integer p = queue.pollFirst();
            visited[p] = false;
            for (int i = head[p]; i != 0; i = edge[i].next) {
                v = edge[i].to;
                visited[v] = true;
                if (dis[p] != Integer.MAX_VALUE && dis[v] > dis[p] + edge[i].w) {
                    dis[v] = dis[p] + edge[i].w;
                    if (!queue.isEmpty() && dis[v] < queue.peekFirst()) queue.addFirst(v);
                    else queue.add(v);
                }
            }
        }
        System.out.println(Arrays.toString(dis));
    }
}
