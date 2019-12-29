package cn.shiyu.bobo;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimBest {
    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public PrimBest(WeightedGraph g) {
        this.G = g;
        mst = new ArrayList<WeightedEdge>();
        CCByWeight cc = new CCByWeight(g);
        if (cc.count() > 1) return;//超过一个联通分量,不处理

        boolean[] visited = new boolean[G.V()];//记录已经访问的节点
        visited[0] = true;//从节点0开始,由于所有的顶点都要在结果边集里面,从哪个顶点开始无所谓,
        Queue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
        for (Integer w : G.adj(0)) {
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));//由于只有一个联通分量,把所有的边都添加进优先队列
        }
        while (!pq.isEmpty()) {
            WeightedEdge minEdge = pq.remove();
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }
            mst.add(minEdge);
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;
            for (Integer w : G.adj(newV)) {
                if (!visited[w])
                    pq.add(new WeightedEdge(newV, w, G.getWeight(newV, w)));
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
    }//[WeightedEdge{v=1, w=2}, WeightedEdge{v=3, w=4}, WeightedEdge{v=0, w=1}, WeightedEdge{v=0, w=5}, WeightedEdge{v=1, w=4}, WeightedEdge{v=3, w=6}]
}


