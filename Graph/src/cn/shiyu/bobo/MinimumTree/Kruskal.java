package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.CC.CCByWeight;
import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;
import cn.shiyu.tree.UnionFind5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 最小生成树之Kruskal算法
 * 算法复杂度O(g logg),算法复杂度随边的数量增长
 * 思路:对边集进行排序,使其符合贪心策略
 */
@SuppressWarnings("all")
public class Kruskal {
    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph g) {
        this.G = g;
        mst = new ArrayList<>();
        CCByWeight cc = new CCByWeight(g);
        if (cc.count() > 1) return;//超过一个联通分量,不处理
        ArrayList<WeightedEdge> edges = new ArrayList<>();//存储边集
        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (v < w)
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));//把每条边都加入边集
            }
        }
        Collections.sort(edges);//对边集进行排序
        UnionFind5 uf = new UnionFind5(G.V());//并查集
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {//如果并查集中这两条边没有相连
                mst.add(edge);//把使用贪心策略选出来的边,加入结果边集
                uf.unionElements(v, w);//维护并查集
            }
        }

    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g4.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
