package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.CC.CCByWeight;
import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;
import cn.shiyu.tree.UnionFind5;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 使用优先队列优化边排序
 */
public class KruskalBest {
    private WeightedGraph g;
    private ArrayList<WeightedEdge> mst;

    public KruskalBest(WeightedGraph g) {
        mst = new ArrayList<WeightedEdge>();
        this.g = g;
        CCByWeight c = new CCByWeight(g);
        if (c.count() > 1) return;
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
        for (int v = 0; v < g.V(); v++) {
            for (Integer w : g.adj(v)) {
                if (v < w)
                    pq.add(new WeightedEdge(v, w, g.getWeight(v, w)));
            }
        }
        UnionFind5 uf = new UnionFind5(g.V());
        while (!pq.isEmpty()) {
            WeightedEdge edge = pq.poll();
            if (!uf.isConnected(edge.getV(), edge.getW())) {
                mst.add(edge);
                uf.unionElements(edge.getV(), edge.getW());
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g4.txt");
        KruskalBest kb = new KruskalBest(g);
        System.out.println(kb.result());
    }
}
