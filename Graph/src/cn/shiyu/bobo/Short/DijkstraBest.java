package cn.shiyu.bobo.Short;

import cn.shiyu.bobo.WeightedGraph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247485151&idx=1&sn=619590d77d933d06679c3bb8d72490eb&chksm=fa0e695ecd79e048ed993c28d3ed5779a9d0fd03c342009fabc1cddf735a4407f239103ff28c&scene=21#wechat_redirect
 * DijkstraBest(无法处理负权边)(优先队列实现)
 * 思想:从源点出发,每次选择离源点最近的一个顶点前进,
 * 然后以该顶点作为中心拓展(中转),最终得到源点到其余所有点的路径
 * <p>
 * （1）将所有的顶点分为两部分：已知最短路程的顶点集合P和未知最短路径的顶点集合Q。最开始，已知最短路径的顶点集合P中只有源点s一个顶点。我们这里用一个book[i]数组来记录哪些点在集合P中。例如对于某个顶点i，如果book[i] = 1则表示这个顶点在集合P中，如果book[i] = 0则表示这个顶点在集合Q中。
 * （2）设置源点s到自己的最短路径为0即dist = 0。若存在源点有能直接到达的顶点i，则把dist[i]设为e[s][i]。同时把所有其它（即源点不能直接到达的）顶点的最短路径为设为∞。
 * （3）在Q中选择一个离源点s最近的顶点u（即dist[u]最小）加入到P中。并考察所有以点u为起点的边，对每一条边进行松弛操作。
 * （4）重复第3步，如果集合Q为空，算法结束。最终dist数组中的值就是源点到所有顶点的最短路径。
 */
@SuppressWarnings("all")
public class DijkstraBest {
    private WeightedGraph G;
    private int s;//单源的那个源点
    private int[] dis;
    private boolean[] visited;//用来记录已知最短路程的顶点集合P,也就是已经求出最短路径的集合

    private class Node implements Comparable<Node> {
        public int v;//目标点
        public int dis;//距离长度

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
        WeightedGraph g = new WeightedGraph("g4.txt");
        DijkstraBest dij = new DijkstraBest(g, 0);
        for (int v = 0; v < g.V(); v++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();
    }
}
