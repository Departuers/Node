package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.CC.CCByWeight;
import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;
import cn.shiyu.tree.UnionFind5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 最小生成树之Kruskal算法
 * 算法复杂度O(E logE),算法复杂度随边的数量增长
 * 思路:对边集进行排序,使其符合贪心策略
 */
@SuppressWarnings("all")
public class Kruskal {
    private WeightedGraph G;

    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph g) {
        this.G = g;
        mst = new ArrayList<WeightedEdge>();
        CCByWeight c = new CCByWeight(g);
        ArrayList<WeightedEdge> edges = new ArrayList<WeightedEdge>();
        if (c.count() > 1) return;
        for (int v = 0; v < g.V(); v++) {
            for (Integer w : g.adj(v)) {
                if (v < w)//v和w中有一条边,那w和v也有一条边,防止重复添加!!!!!!
                    edges.add(new WeightedEdge(v, w, g.getWeight(v, w)));
            }
        }
        Collections.sort(edges);
        UnionFind5 uf = new UnionFind5(g.V());
        for (WeightedEdge edge : edges) {
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
        WeightedGraph w = new WeightedGraph("g4.txt");
        Kruskal k = new Kruskal(w);
        System.out.println(k.mst);
    }
}
