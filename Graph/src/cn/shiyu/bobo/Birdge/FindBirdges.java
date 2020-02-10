package cn.shiyu.bobo.Birdge;

import cn.shiyu.bobo.Graph;

import java.util.ArrayList;

/**
 * 寻桥
 * 使用DFS就可以寻桥
 */
@SuppressWarnings("all")
public class FindBirdges {
    private Graph G;
    private boolean[] visited;
    private int[] ord;
    private int[] low;
    private ArrayList<Edge> res;
    private int cnt;//记录节点访问顺序!!!

    public FindBirdges(Graph g) {
        this.G = g;
        visited = new boolean[g.getV()];
        res = new ArrayList<Edge>();
        ord = new int[G.getV()];
        low = new int[G.getV()];
        cnt = 0;
        for (int v = 0; v < G.getV(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];//low[v]初始化为ord[v],实际语义为最小ord值
        cnt++;//cnt记录节点访问顺序,是第几个访问,(从第0个开始)
        // 每访问一个cnt++;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > low[v]) {
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result() {
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        FindBirdges f = new FindBirdges(g);
        System.out.println(f.res);
    }
}
