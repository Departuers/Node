package cn.shiyu.bobo.directed.ShortestPath;

import cn.shiyu.bobo.directed.WeightedGraphByDirected;

import java.util.Arrays;

/**
 * 可以处理负权边,主要针对于有向图
 * 即使只求s-t的最短路径,是不可以提前终止的
 * 可能最短路径要在最后一轮松弛操作才求出来
 * <p>
 * 在整张图求出最短路径过后,再进行松弛操作就是在空转,可以通过SPFA优化
 * O(V*E)
 */
public class BellmanFord {
    private WeightedGraphByDirected G;
    private int s;
    public int[] dis;
    private boolean hasNegCycle = false;//是否存在负权环

    public BellmanFord(WeightedGraphByDirected G, int s) {
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        for (int pass = 1; pass < G.V(); pass++) {//进行V-1轮次松弛操作,每轮对所有的边松弛一次
            for (int v = 0; v < G.V(); v++) {
                for (Integer w : G.adj(v)) {
                    if (dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                    }
                }
            }
        }
        //进行V-1轮松弛操作后,再进行松弛操作dis数组不会改变,
        //如果存在负权环,则不存在最短路径
        //无向图,只要有负权边,就一定存在负权环,则不存在最短路径
        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w]) {
                    hasNegCycle = true;
                }
            }
        }


    }

    public boolean hasNegativeCycle() {
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v) {
        G.validateVertex(v);
        if (hasNegCycle) throw new RuntimeException("exist negative cycle.");
        return dis[v];
    }

    public static void main(String[] args) {
        WeightedGraphByDirected g = new WeightedGraphByDirected("g9.txt",true);

        BellmanFord b = new BellmanFord(g, 0);
        System.out.println(Arrays.toString(b.dis));
    }
}
