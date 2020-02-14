package cn.shiyu.bobo.Short;

import cn.shiyu.bobo.WeightedGraph;

import java.util.Arrays;

/**
 * 弗洛伊德（Floyd）算法(动态规划)
 *   Floyd算法是一个经典的动态规划算法。其主要思想为：
 * 从任意顶点u到任意顶点v的最短路径不外乎2种可能，一是直接从u到v，二是从u经过若干个顶点k到v。
 * 所以，我们假设dist(u,v)为顶点u到顶点v的最短路径的距离，对于每一个顶点k，我们检查dist(u,k) + dist(k,v) < dist(u,v)是否成立，
 * 如果成立，证明从u到k再到v的路径比u直接到v的路径短，我们便设置dist(u,v) = dist(u,k) + dist(k,v)，
 * 这样一来，当我们遍历完所有顶点k，dist(u,v)中记录的便是u到v的最短路径的距离。
 * <p>
 * 可以求出所有点对的最短路径,可以求出图的直径
 * 图的直径:所有点对最短路径的最大值,就是图的直径
 * O(V^3)效率非常低
 */
public class Floyd {
    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegCycle = false;

    public Floyd(WeightedGraph G) {
        this.G = G;
        dis = new int[G.V()][G.V()];
        for (int[] di : dis) {
            Arrays.fill(di, Integer.MAX_VALUE);//初始化内容
        }

        for (int v = 0; v < G.V(); v++) {
            dis[v][v] = 0;
            for (Integer w : G.adj(v)) {
                dis[v][w] = G.getWeight(v, w);
            }
        }
        for (int t = 0; t < G.V(); t++) {
            for (int v = 0; v < G.V(); v++) {//枚举整个二维数组
                for (int w = 0; w < G.V(); w++) {//查找是否有中间顶点t,使得从v到w再到v比己知的路径更短
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][v] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];
                }
            }
        }

        for (int v = 0; v < G.V(); v++) {
            if (dis[v][v] < 0)
                hasNegCycle = true;
        }

    }

    public boolean hasNegativeCycle() {
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int distTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w];
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g4.txt");
        Floyd floyed = new Floyd(g);
        if (!floyed.hasNegativeCycle()) {
            for (int v = 0; v < g.V(); v++) {
                for (int w = 0; w < g.V(); w++)
                    System.out.print(floyed.distTo(v, w) + " ");
                System.out.println();
            }
        } else
            System.out.println("exist negative cycle.");
    }
}
