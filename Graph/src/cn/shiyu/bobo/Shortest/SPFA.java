package cn.shiyu.bobo.Shortest;

import cn.shiyu.bobo.WeightedGraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Shortest Path Fast Algorithm
 * 针对Bellman-Ford算法的优化
 */
@SuppressWarnings("all")
public class SPFA {
    private WeightedGraph g;
    private int[] dis;//最短路径
    private boolean[] visited;//访问过的顶点
    private int[] pre;//顶点前驱
    private int s;//单源

    public SPFA(WeightedGraph g, int s) {
        pre = new int[g.V()];
        this.visited = new boolean[g.V()];
        this.g = g;
        this.s = s;
        dis = new int[g.V()];

        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            visited[v] = false;
            for (Integer w : g.adj(v)) {

                if (dis[v] + g.getWeight(v, w) < dis[w] && dis[v] != Integer.MAX_VALUE) {
                    dis[w] = Math.min(dis[v] + g.getWeight(v, w), dis[w]);
                    if (dis[queue.peekFirst()] > dis[w]) {
                        queue.addFirst(w);
                        visited[w] = true;
                    } else {
                        queue.addLast(w);
                        visited[w] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g9.txt");
        DijkstraBest db = new DijkstraBest(g, 0);
        SPFA s = new SPFA(g, 0);
        System.out.println(Arrays.toString(db.dis));
        System.out.println(Arrays.toString(s.dis));
        System.out.println(db.paht(4));
    }
}
