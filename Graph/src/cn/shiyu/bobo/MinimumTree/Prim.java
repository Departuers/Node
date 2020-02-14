package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.CC.CCByWeight;
import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;

import java.util.ArrayList;

/**
 * 最小生成树之Prim算法
 * 思路:找顶点来生成最小生成树O(V^2)与顶点的数量有关,随顶点数量线性增长
 * 由于所有的顶点都要在结果边集里面,从哪个顶点开始无所谓,
 * 顶点逐步加入(边权)
 */
public class Prim {
    private ArrayList<WeightedEdge> mst;
    private WeightedGraph G;

    public Prim(WeightedGraph g) {
        this.G = g;
        mst = new ArrayList<WeightedEdge>();
        CCByWeight c = new CCByWeight(g);
        if (c.count() > 1) return;
        boolean[] visited = new boolean[g.V()];//visited代表切分,染色不同
        visited[0] = true;
        for (int i = 1; i < g.V(); i++) {//只需要找V-1条边,构成最小生成树,所以循环V-1次
            WeightedEdge minedge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < g.V(); v++) {//寻找该切分里面符合条件的边中最短的横切边
                if (visited[v])
                    for (Integer w : g.adj(v)) {
                        if (!visited[w] && g.getWeight(v, w) < minedge.getWeight()) {
                            minedge = new WeightedEdge(v, w, g.getWeight(v, w));
                        }
                    }
            }

            mst.add(minedge);
            visited[minedge.getW()] = true;//改变切分
            visited[minedge.getV()] = true;//改变切分

        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g4.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }//[WeightedEdge{v=1, w=2}, WeightedEdge{v=3, w=4}, WeightedEdge{v=0, w=1}, WeightedEdge{v=0, w=5}, WeightedEdge{v=1, w=4}, WeightedEdge{v=3, w=6}]
}
