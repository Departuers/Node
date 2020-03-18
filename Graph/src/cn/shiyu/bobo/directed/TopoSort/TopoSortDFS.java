package cn.shiyu.bobo.directed.TopoSort;

import cn.shiyu.bobo.directed.GraphByDirected;

import java.util.LinkedList;

public class TopoSortDFS {
    private GraphByDirected g;
    private LinkedList<Integer> res;
    private boolean hasCycle = false;//是否有环

    public TopoSortDFS(GraphByDirected g) {
        if (!g.isDirected()) throw new IllegalArgumentException("No Directed");
        this.g = g;

//        res = new LinkedList<Integer>();
//        hasCycle = new CycleDetection(g).haCycle();


    }

    public boolean hasCycle() {
        return hasCycle();
    }

    public LinkedList<Integer> result() {
        return this.res;
    }

    static void dfs(int v) {

    }

    public static void main(String[] args) {

    }

}
