package cn.shiyu.bobo;

import java.util.ArrayList;

/**
 * 最小生成树之Prim算法
 * 思路:找顶点来生成最小生成树O(p^2)与顶点的数量有关,随顶点数量线性增长
 * 由于所有的顶点都要在结果边集里面,从哪个顶点开始无所谓,
 * 顶点逐步加入(边权)
 */
public class Prim {
    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph g) {
        this.G = g;
        mst = new ArrayList<>();
        CCByWeight cc = new CCByWeight(g);
        if (cc.count() > 1) return;//超过一个联通分量,不处理
        boolean[] visited = new boolean[G.V()];//记录已经访问的节点
        visited[0] = true;//从节点0开始,由于所有的顶点都要在结果边集里面,从哪个顶点开始无所谓,
        for (int i = 1; i < G.V(); i++) {
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);

            for (int v = 0; v < G.V(); v++) {
                if (visited[v]) {
                    for (Integer w : G.adj(v)) {
                        if (!visited[w] && G.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, G.getWeight(v, w));
                        }
                    }
                }
            }//根据顶点寻找权重小的边
            mst.add(minEdge);
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
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
