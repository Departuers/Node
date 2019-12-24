package cn.shiyu.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无权图的单源最短距离,点对点
 */
public class USSSPath {

    private AdjSet G;
    private boolean visited[];
    private int[] pre;
    private int[] dis;
    private int s;

    public USSSPath(AdjSet G, int s) {
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
        }
        System.out.println();
    }

    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while (!queue.isEmpty()) {
            Integer v = queue.remove();
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
        AdjSet s = new AdjSet("g.txt");
        USSSPath b = new USSSPath(s, 0);
        System.out.println(b.ids(6));
    }

}
