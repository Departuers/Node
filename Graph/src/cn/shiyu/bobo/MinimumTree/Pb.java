package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Pb {
    private WeightedGraph g;
    private ArrayList<WeightedEdge> mst;

    public Pb(WeightedGraph G) {
        this.g = G;
        mst = new ArrayList<WeightedEdge>();
        boolean[] visited = new boolean[g.V()];
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
        for (Integer v : g.adj(0)) {
            pq.add(new WeightedEdge(0, v, g.getWeight(v, 0)));
        }
        visited[0] = true;
        while (!pq.isEmpty()) {
            WeightedEdge minedge = pq.remove();
            if (visited[minedge.getW()] && visited[minedge.getV()]) {
                continue;
            }
            mst.add(minedge);
            int newv = visited[minedge.getV()] ? minedge.getW() : minedge.getV();
            for (Integer w : g.adj(newv)) {
                if (!visited[w])//
                    pq.add(new WeightedEdge(newv, w, g.getWeight(newv, w)));
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g4.txt");
        PrimBest prim = new PrimBest(g);
        System.out.println(prim.result());
    }
}
