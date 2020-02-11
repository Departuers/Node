package cn.shiyu.bobo.Birdge;

import cn.shiyu.bobo.Graph;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("all")
public class HamiltonLoop {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph g) {
        this.G = g;
        end = -1;
        visited = new boolean[g.getV()];
        pre = new int[g.getV()];
        dfs(0, 0, g.getV());
    }

    /**
     *
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
            } else if (w == 0 && left == 0) {

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
