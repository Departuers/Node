package cn.shiyu.bobo.Loop;


import cn.shiyu.bobo.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈密尔顿路径,
 * 对于起始点很重要
 */
@SuppressWarnings("all")
public class HamiltonPath {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonPath(Graph g, int start) {
        this.G = g;
        end = -1;
        visited = new boolean[g.getV()];
        pre = new int[g.getV()];
        dfs(start, start, g.getV());
    }

    /**
     * @param v
     * @param parent
     * @param left
     * @return
     */
    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        pre[v] = parent;
        left--;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) {
                    return true;
                }
            } else if (left == 0) {
                end = v;
                return true;
            }
        }
        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (end == -1) return res;
        while (end != 0) {
            res.add(end);
            end = pre[end];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g3.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());
    }
}
