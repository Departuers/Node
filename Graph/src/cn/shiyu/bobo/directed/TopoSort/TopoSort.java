package cn.shiyu.bobo.directed.TopoSort;

import cn.shiyu.bobo.directed.GraphByDirected;

import java.util.ArrayList;

public class TopoSort {
    private GraphByDirected g;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;//是否有环

    public TopoSort(GraphByDirected g) {
        if (!g.isDirected()) throw new IllegalArgumentException("No Directed");
        this.g = g;

        res = new ArrayList<Integer>();
        int[] indegreees = new int[g.getV()];
        int[] outdegrees = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
        }

    }

}
