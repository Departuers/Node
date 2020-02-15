package cn.shiyu.bobo.CC;

import cn.shiyu.bobo.Graph;

import java.util.Arrays;

/**
 * 二分图检测,
 * 通过染色(给colors数组赋值)来实现二分图检测
 * colors数组的值相同则他们是同一种颜色
 */
public class Bipartition {
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBirpartite = true;//初始化为true很重要

    //深度优先遍历从0开始
    public Bipartition(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
        colors = new int[G.getV()];
        Arrays.fill(colors, -1);//给颜色赋初值,也可以不写,反正都要覆盖它们的值

        for (int v = 0; v < G.getV(); v++) {//某一个联通分量不是二分图,那么整个图就不是二分图
            if (!visited[v])
                if (!dfs(v, 0)) {//起始染成0这个颜色
                    isBirpartite = false;//如果其中有一个联通分量不是二分图,那么整张图就不是二分图
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {
        colors[v] = color;
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color))
                    return false;
                //原来的颜色是1,传进去的就是0就是(1-1),原来颜色是0,那么穿进去的颜色就是1,就是(1-0)
            } else if (colors[w] == colors[v]) {//如果w这个顶点已经被访问过了,就说明w有颜色,
                //如果相连的两个顶点颜色相同,那么就不是一个二分图
                return false;
            }
        }
        return true;
    }

    public boolean isBirpartite() {
        return isBirpartite;
    }

    public static void main(String[] args) {
        Graph s = new Graph("g2.txt");
        Bipartition b = new Bipartition(s);
        System.out.println(Arrays.toString(b.colors));
        System.out.println(b.isBirpartite);
    }
}