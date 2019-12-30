package cn.shiyu.bobo;

import java.util.Arrays;

/**
 * 最短路径之Dijkstra算法
 * 带权图的单源最短路径是可以通过贪心选择得到全局最优解
 * 每步选择:选择一个节点,使得到达t更近
 * 选择寻找节点中距s节点最近的点,及其到s的路径,最终求得s到t的最短路径
 * <p>
 * 具体实现:维护一个已经被探索过的点集S,S中我们已经得到了从原点a到点u的最短路径d(u)
 * 初始化时S={a}//点集开始只包括原点   d(a)=0//原点到原点距离为0
 */
@SuppressWarnings("all")
public class Dijkstra {
    private WeightedGraph G;
    private int s;//原点
    private int[] dis;
    private boolean[] visited;//记录已经访问

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);//初始化距离为int最大值
        dis[s] = 0;
        visited = new boolean[G.V()];

        while (true) {
            int cur = -1, curdis = Integer.MAX_VALUE;
            for (int v = 0; v < G.V(); v++) {//遍历所有节点
                if (!visited[v] && dis[v] < curdis) {//如果节点没被访问过,并且
                    curdis = dis[v];
                    cur = v;
                }
            }

            if (cur == -1) break;
            visited[cur] = true;
            for (int w : G.adj(cur))
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w])
                        dis[w] = dis[cur] + G.getWeight(cur, w);
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