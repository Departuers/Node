package cn.shiyu.bobo.CC;

import cn.shiyu.bobo.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 无向带权图,求具体联通分量
 */
public class CCByWeight {

    private WeightedGraph G;
    private int[] visited;
    private int cccount = 0;

    public CCByWeight(WeightedGraph G) {

        this.G = G;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);

        for (int v = 0; v < G.V(); v++)
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, ccid);
    }

    public int count() {
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components() {

        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++)
            res[i] = new ArrayList<Integer>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g.txt");
        CCByWeight cc = new CCByWeight(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
