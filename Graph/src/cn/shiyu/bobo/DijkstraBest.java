package cn.shiyu.bobo;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraBest {
    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;

    private class Node implements Comparable<Node> {
        public int v;//目标点
        public int dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis - o.dis;
        }
    }

    public DijkstraBest(WeightedGraph G, int s) {
        this.G = G;
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        visited = new boolean[G.V()];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));//源点到源点自身距离为0

        while (!pq.isEmpty()) {
            int cur = pq.remove().v;
            if (visited[cur]) continue;

            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                    }
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
        DijkstraBest dij = new DijkstraBest(g, 0);
        for (int v = 0; v < g.V(); v++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();
    }
}
