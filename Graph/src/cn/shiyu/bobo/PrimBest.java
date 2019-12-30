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
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));//由于只有一个联通分量,把与0相连的边都添加进优先队列
        }
        while (!pq.isEmpty()) {
            WeightedEdge minEdge = pq.remove();//由于是优先队列(会自动按权重从小到大排序),前面的肯定是两点之间权重最小的边
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {//
                continue;//如果这两个点都已经被标记访问,就终止此次循环,由于优先队列前面
            }
            mst.add(minEdge);//添加优先队列中权重最小的边
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();//选择minEdge中没有被标记访问过的点,由于前面两个点被标记访问的情况已经处理,所以这里肯定有1个或者2个没有被标记访问
            visited[newV] = true;//标记访问那个没有被访问的节点
            for (Integer w : G.adj(newV)) {//将与newV(也就是上面那个刚刚标记访问的点)相邻的节点,若这些节点没有访问过,则加入优先队列
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


