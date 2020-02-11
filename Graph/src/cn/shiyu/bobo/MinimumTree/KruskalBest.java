package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;
import cn.shiyu.tree.UnionFind5;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 使用优先队列优化边排序
 */
public class KruskalBest {
    private WeightedGraph wg;
    private ArrayList<WeightedEdge> res;

    public KruskalBest(WeightedGraph g) {
        wg = g;
        res = new ArrayList<WeightedEdge>();
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
        for (int v = 0; v < g.V(); v++) {
            for (Integer w : g.adj(v)) {
                if (v < w)
                    pq.add(new WeightedEdge(v, w, wg.getWeight(v, w)));
            }
        }
        UnionFind5 uf = new UnionFind5(wg.V());
        for (WeightedEdge edge : pq) {
            if (!uf.isConnected(edge.getV(), edge.getW())) {
                res.add(edge);
                uf.unionElements(edge.getV(), edge.getW());
            }
        }
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("g4.txt");
        KruskalBest k = new KruskalBest(wg);
        System.out.println(k.res);
    }
}
