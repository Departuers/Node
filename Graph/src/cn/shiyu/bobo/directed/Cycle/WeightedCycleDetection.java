package cn.shiyu.bobo.directed.Cycle;

import cn.shiyu.bobo.directed.WeightedGraphByDirected;

/**
 * 有向图带权图的环检测
 */
public class WeightedCycleDetection {
    private WeightedGraphByDirected G;
    private boolean[] visited;
    private boolean haCycle;
    private boolean[] onPath;//记录当前路径,就像贪吃蛇一样,

    //深度优先遍历从0开始
    public WeightedCycleDetection(WeightedGraphByDirected G) {
        this.onPath = new boolean[G.V()];
        this.G = G;
        visited = new boolean[G.V()];
        // dfs(0);没有跟其他顶点联通的不会被遍历到
        //即使没和任何一个顶点相连,也可以遍历到
        for (int v = 0; v < G.V(); v++) {//保证在每一个联通分量都进行了环检测
            if (!visited[v])
                if (dfs(v, v)) {
                    this.haCycle = true;//只用找到一个环就够了
                    break;
                }
        }
    }

    /**
     * 在有向图中:即使找到了已经遍历过的节点也不代表形成环
     * 从顶点v开始,判断图中是否有环
     * 在当前路径上添加一个标记:onPath[]
     *
     * @param v      需要判断的顶点
     * @param parent 记录节点的上一个节点,也就是从哪来的
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        onPath[v] = true;//添加标记
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            } else if (onPath[w]) {
                //对于有向图,从v到w,再从w回到v,虽然只有两个顶点两条边,也算是一个环
                return true;
            }
        }
        onPath[v] = false;//抹除路径
        return false;
    }

    public boolean haCycle() {
        return haCycle;
    }

    public static void main(String[] args) {
        WeightedGraphByDirected s = new WeightedGraphByDirected("g.txt");
        WeightedCycleDetection c = new WeightedCycleDetection(s);
        System.out.println(c.haCycle());
        WeightedGraphByDirected s1 = new WeightedGraphByDirected("g2.txt");
        WeightedCycleDetection c1 = new WeightedCycleDetection(s1);
        System.out.println(c1.haCycle());
    }
}
