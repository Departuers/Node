package cn.shiyu.bobo.CC;

import cn.shiyu.bobo.Graph;

/**
 * 无向图环检测
 */
public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean haCycle;

    //深度优先遍历从0开始
    public CycleDetection(Graph G) {
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
                if (dfs(w, v)) return true;//w的上一个节点就是v,提前终止,巧妙!
            } else if (w != parent) {
                //DFS搜索到了已经访问过的点
                //若这个点不是上一个点(构成环最小要3个顶点),则就构成环了,
                return true;
            }
        }
        return false;
    }

    public boolean haCycle() {
        return haCycle;
    }

    public static void main(String[] args) {
        Graph s = new Graph("g.txt");
        CycleDetection c = new CycleDetection(s);
        System.out.println(c.haCycle());
        Graph s1 = new Graph("g2.txt");
        CycleDetection c1 = new CycleDetection(s1);
        System.out.println(c1.haCycle());
    }
}