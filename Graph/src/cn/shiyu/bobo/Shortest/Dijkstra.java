package cn.shiyu.bobo.Shortest;

import cn.shiyu.bobo.WeightedGraph;

import java.util.Arrays;

/**
 * 最短路径之Dijkstra算法(无法处理负权边)
 * 带权图的单源最短路径是可以通过贪心选择得到全局最优解
 * 每步选择:选择一个节点,使得到达t更近
 * 选择寻找节点中距s节点最近的点,及其到s的路径,最终求得s到t的最短路径
 * 思想:从源点出发,每次选择离源点最近的一个顶点前进,然后以该顶点作为中心拓展,最终得到源点到其余所有点的路径
 */
@SuppressWarnings("all")
public class Dijkstra {
    private WeightedGraph G;
    private int s;//原点
    private int[] dis;
    private boolean[] visited;//记录已经求出最短路径的值

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        visited = new boolean[G.V()];
        dis[s] = 0;
        while (true) {
            int curdis = Integer.MAX_VALUE, cur = -1;
            for (int v = 0; v < G.V(); v++) {
                if (!visited[v] && dis[v] < curdis) {
                    curdis = dis[v];
                    cur = v;
                }
            }

            if (cur == -1) break;
            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w])//
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                }
            }
        }

    }

    /**
     * 判断是否与v连接(源点是否与v存在一条路径)
     *
     * @param v 要到达的点v
     * @return 与否存在
     */
    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    /**
     * 源点到达v所需要的距离(长度)
     *
     * @param v 要到达的点v
     * @return 距离
     */
    public int distTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g6.txt");
        Dijkstra dij = new Dijkstra(g, 0);
        for (int v = 0; v < g.V(); v++) {
            System.out.println(dij.distTo(v) + " ");
        }
    }
}