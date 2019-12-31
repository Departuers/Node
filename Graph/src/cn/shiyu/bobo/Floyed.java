package cn.shiyu.bobo;

import java.util.Arrays;

public class Floyed {
    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegCycle = false;

    public Floyed(WeightedGraph G) {
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
            for (int v = 0; v < G.V(); v++) {
                for (int w = 0; w < G.V(); w++) {
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
        Floyed floyed = new Floyed(g);
        if(!floyed.hasNegativeCycle()){
            for(int v = 0; v < g.V(); v ++){
                for(int w = 0; w < g.V(); w ++)
                    System.out.print(floyed.distTo(v, w) + " ");
                System.out.println();
            }
        }
        else
            System.out.println("exist negative cycle.");
    }
}
