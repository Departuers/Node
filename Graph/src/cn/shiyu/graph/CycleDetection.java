package cn.shiyu.graph;

/**
 * 环检测
 */
public class CycleDetection {
    private AdjSet G;
    private boolean visited[];
    private boolean haCycle;

    //深度优先遍历从0开始
    public CycleDetection(AdjSet G) {
        this.G = G;
        visited = new boolean[G.getV()];
        // dfs(0);没有跟其他顶点联通的不会被遍历到
        //即使没和任何一个顶点相连,也可以遍历到
        for (int v = 0; v < G.getV(); v++) {//保证在每一个联通分量都进行了环检测
            if (!visited[v])
                if (dfs(v, v)) {
                    this.haCycle = true;//只用找到一个环就够了
                    break;
                }
        }
    }

    /**
     * 从顶点v开始,判断图中是否有环
     *
     * @param v      需要判断的顶点
     * @param parent 记录节点的上一个节点,也就是从哪来的
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;//w的上一个节点就是v
            } else if (w != parent) {
                //说明找到了环
                return true;
            }
        }
        return false;
    }

    public boolean haCycle() {
        return haCycle;
    }

    public static void main(String[] args) {
        AdjSet s = new AdjSet("g.txt");
        CycleDetection c = new CycleDetection(s);
        System.out.println(c.haCycle());
        AdjSet s1 = new AdjSet("g2.txt");
        CycleDetection c1 = new CycleDetection(s1);
        System.out.println(c1.haCycle());
    }
}