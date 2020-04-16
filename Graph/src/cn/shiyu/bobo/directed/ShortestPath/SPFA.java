package cn.shiyu.bobo.directed.ShortestPath;

import cn.shiyu.bobo.directed.WeightedGraphByDirected;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Shortest Path Fast Algorithm
 * 针对Bellman-Ford算法的优化
 */
@SuppressWarnings("all")
public class SPFA {
    private WeightedGraphByDirected g;
    private int[] dis;//最短路径
    private boolean[] visited;//访问过的顶点
    private int[] pre;//顶点前驱
    private int s;//单源

    public SPFA(WeightedGraphByDirected g, int s) {
        pre = new int[g.V()];
        this.visited = new boolean[g.V()];
        this.g = g;
        this.s = s;
        dis = new int[g.V()];

        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.addLast(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            visited[v] = false;
            for (Integer w : g.adj(v)) {
                if (dis[v] != Integer.MAX_VALUE && dis[v] + g.getWeight(v, w) < dis[w]) {
                    dis[w] = dis[v] + g.getWeight(v, w);
                    pre[w] = v;
                    if (!visited[w]) {
                        visited[w] = true;
                        if (!queue.isEmpty() && dis[w] < dis[queue.peek()]) {
                            queue.addFirst(w);
                        } else {
                            queue.add(w);
                        }
                    }
                }
            }
        }
    }

    public Iterable<Integer> path(int v) {
        int cur = v;
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraphByDirected g = new WeightedGraphByDirected("g43.txt", true);
        BellmanFord db = new BellmanFord(g, 0);
        SPFA s = new SPFA(g, 0);
        System.out.println(Arrays.toString(s.dis));
        System.out.println(s.path(1));
        System.out.println(Arrays.toString(db.dis));
    }
}
