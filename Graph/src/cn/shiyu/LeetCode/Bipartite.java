package cn.shiyu.LeetCode;

import java.util.Arrays;

/**
 * 二分图检测
 */
public class Bipartite {
    public static int[][] graph;
    public static boolean[] visited;
    public static int[] colors;

    public static void main(String[] args) {
        int[][] g =
                {{0, 1, 1, 0, 0, 0, 0},
                        {1, 0, 0, 1, 1, 0, 0},
                        {1, 0, 0, 1, 0, 0, 1},
                        {0, 1, 1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0}};
        System.out.println(isBipartite(g));
        System.out.println(Arrays.toString(colors));
    }

    /**
     * @param graph 邻接矩阵
     * @return 是否是二分图
     */
    public static boolean isBipartite(int[][] graph) {
        Bipartite.graph = graph;
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];
        for (int v = 0; v < V; v++) {
            if (!visited[v])
                if (!dfs(v, 0)) return false;
        }
        return true;
    }

    private static boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color))
                    return false;
            } else if (colors[v] == colors[w]) {
                return true;
            }
        }
        return false;
    }
}
