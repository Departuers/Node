package cn.shiyu.bobo.directed.TopoSort;

import cn.shiyu.bobo.directed.GraphByDirected;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//拓扑排序 O(V+E)
public class TopoSort {
    private GraphByDirected g;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;//是否有环

    public TopoSort(GraphByDirected g) {
        if (!g.isDirected()) throw new IllegalArgumentException("No Directed");
        this.g = g;

        res = new ArrayList<Integer>();
        int[] inDegrees = new int[g.V()];
        Queue<Integer> q = new LinkedList<Integer>();
        for (int v = 0; v < g.V(); v++) {
            inDegrees[v] = g.indegrees(v);
            if (inDegrees[v] == 0) q.add(v);//存储初始入度值为0的情况
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);

            for (Integer next : g.adj(cur)) {
                inDegrees[next]--;//维护相邻节点入度
                if (inDegrees[next] == 0) q.add(next);
            }
        }
        if (res.size() != g.V()) {
            hasCycle = true;
            res.clear();//无解则清除结果
        }
    }

    public boolean hasCycle() {
        return hasCycle();
    }

    public ArrayList<Integer> result() {
        return this.res;
    }

    public static void main(String[] args) {
        GraphByDirected g = new GraphByDirected("g3.txt", true);
        TopoSort t = new TopoSort(g);
        System.out.println(t.result());
    }

}
