package cn.shiyu.bobo.Shortest;

import cn.shiyu.bobo.Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 无权图的单源最短距离,点对点
 */
public class USSSPathByBFS {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int[] dis;
    private int s;

    public USSSPathByBFS(Graph G, int s) {
        this.s = s;
        this.G = G;
        visited = new boolean[G.getV()];
        pre = new int[G.getV()];
        dis = new int[G.getV()];

        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);

        bfs(s);
        for (int di : dis) {
            System.out.print(di + " ");
        }//到每一个点的距离
        System.out.println();
    }

    public void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer w : G.adj(v)) {//把与v相邻的元素入队
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;//记录访问过的元素
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }

    public int ids(int t) {
        G.validateVertex(t);
        return dis[t];
    }

    public static void main(String[] args) {
        Graph s = new Graph("g.txt");
        USSSPathByBFS b = new USSSPathByBFS(s, 0);
        System.out.println(b.ids(6));
    }

}
