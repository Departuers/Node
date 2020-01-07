package cn.shiyu.bobo.Short;

import cn.shiyu.bobo.WeightedGraph;

import java.util.Arrays;

public class BellmanFord {
    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean hasNegCycle = false;

    public BellmanFord(WeightedGraph G, int s) {
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
    }
    public boolean hasNegativeCycle(){
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v){
        G.validateVertex(v);
        if(hasNegCycle) throw new RuntimeException("exist negative cycle.");
        return dis[v];
    }
}
