package cn.shiyu.bobo.MinimumTree;

import cn.shiyu.bobo.CC.CCByWeight;
import cn.shiyu.bobo.WeightedEdge;
import cn.shiyu.bobo.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Prim算法堆优化
 * O(E log E)
 */
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
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
        for (Integer w : G.adj(0)) {
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));//由于只有一个联通分量,把与0相连的边都添加进优先队列
        }
        while (!pq.isEmpty()) {
            WeightedEdge minEdge = pq.remove();//由于是优先队列(会自动按权重从小到大排序),前面的肯定是两点之间权重最小的边
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;//非法边直接扔掉,因为只有其中有一个标记才是横切边
            }
            mst.add(minEdge);//添加优先队列中权重最小的边,因为这是一条最短横切边
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();//选择minEdge中没有被标记过的点,由于前面两个点被标记的情况已经处理,所以这里肯定有且只有1个没有被标记
            visited[newV] = true;//标记访问那个没有被访问的节点,扩展切分!!!
            for (Integer w : G.adj(newV)) {//将与newV(也就是上面那个刚刚标记访问的点)相邻的横切边,若这些节点没有访问过,则加入优先队列
                if (!visited[w])//找出newV的横切边,并且是未被找到最小生成树的点
                    pq.add(new WeightedEdge(newV, w, G.getWeight(newV, w)));
            }
        }
    }

    public int result() {
        int i = 0;
        for (WeightedEdge edge : mst) {
            i += edge.getWeight();
        }
        return i;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g6.txt");
        PrimBest prim = new PrimBest(g);
        System.out.println(prim.result());
        System.out.println(prim.mst);
    }//[WeightedEdge{v=1, w=2}, WeightedEdge{v=3, w=4}, WeightedEdge{v=0, w=1}, WeightedEdge{v=0, w=5}, WeightedEdge{v=1, w=4}, WeightedEdge{v=3, w=6}]
}