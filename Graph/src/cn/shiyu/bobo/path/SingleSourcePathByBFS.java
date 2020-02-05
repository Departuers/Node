package cn.shiyu.bobo.path;

import cn.shiyu.bobo.Graph;

import java.util.*;

/**
 *
 */
public class SingleSourcePathByBFS {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int s;

    public SingleSourcePathByBFS(Graph G, int s) {
        this.G = G;
        this.s = s;
        visited = new boolean[G.getV()];
        pre = new int[G.getV()];
        Arrays.fill(pre, -1);
        bfs(s);//从s出发,遍历这个联通分量
    }

    public void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer w : G.adj(v)) {//把与v相邻的元素入队
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;//记录访问过的元素
                    pre[w] = v;
                }
            }
        }
    }

    /**
     * 看从单源s能不能到t
     *
     * @param t 需要到达的节点
     * @return 判断能否
     */
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (!isConnectedTo(t)) return res;//如果不能到达
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph s = new Graph("g.txt");
        SingleSourcePathByBFS ssp = new SingleSourcePathByBFS(s, 0);

        System.out.println(ssp.path(6));
    }

}
