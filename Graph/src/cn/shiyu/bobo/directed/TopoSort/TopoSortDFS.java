package cn.shiyu.bobo.directed.TopoSort;

import cn.shiyu.bobo.directed.Cycle.CycleDetection;
import cn.shiyu.bobo.directed.GraphByDirected;

import java.util.Collections;
import java.util.LinkedList;

//拓扑排序dfs版本 O(V+E)
public class TopoSortDFS {
    private GraphByDirected g;
    private LinkedList<Integer> res;
    private boolean hasCycle = false;//是否有环
    private boolean[] visited;

    public TopoSortDFS(GraphByDirected g) {
        if (!g.isDirected()) throw new IllegalArgumentException("No Directed");
        this.g = g;
        visited = new boolean[g.V()];

        res = new LinkedList<Integer>();
        hasCycle = new CycleDetection(g).haCycle();//检测是否有环
        if (hasCycle) return;//有环则无解退出

        for (int i = 0; i < g.V(); i++) {
            if (!visited[i])
                dfs(i);
        }
        Collections.reverse(res);

    }

    public boolean hasCycle() {
        return hasCycle();
    }

    public LinkedList<Integer> result() {
        return res;
    }

    public void dfs(int v) {
        visited[v] = true;
        for (Integer w : g.adj(v)) {
            if (!visited[w])
                dfs(w);
        }
        res.add(v);
    }

    public static void main(String[] args) {
        GraphByDirected g = new GraphByDirected("g3.txt", true);
        TopoSortDFS t = new TopoSortDFS(g);
        System.out.println(t.result());
    }

}
