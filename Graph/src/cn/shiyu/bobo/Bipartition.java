package cn.shiyu.bobo;

/**
 * 二分图
 */
public class Bipartition {
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBirpartite = true;

    //深度优先遍历从0开始
    public Bipartition(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
        colors = new int[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            colors[i] = -1;
        }

        for (int v = 0; v < G.getV(); v++) {//某一个联通分量不是二分图,那么整个图就不是二分图
            if (!visited[v])
                if (!dfs(v, 0)) {           //起始染成0这个颜色
                    isBirpartite = false;
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {
        colors[v] = color;
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
                //原来的颜色是1,传进去的就是0就是(1-1),原来颜色是0,那么穿进去的颜色就是1,就是(1-0)
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBirpartite() {
        return isBirpartite;
    }

    public static void main(String[] args) {
        Graph s = new Graph("g3.txt");
        Bipartition b = new Bipartition(s);
        System.out.println(b.isBirpartite);
    }
}
