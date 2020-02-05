package cn.shiyu.bobo.traversal;

import cn.shiyu.bobo.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * O(V+E)
 */
public class GraphBFS {
    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> order = new ArrayList<Integer>();//存储遍历结果

    public GraphBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
        for (int v = 0; v < G.getV(); v++) {//完整遍历整个图
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            order.add(v);
            for (Integer w : G.adj(v)) {//把与v相邻的元素入队
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;//记录访问过的元素
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Graph s = new Graph("g2.txt");
        GraphBFS b = new GraphBFS(s);
        System.out.println(b.order());
    }

}