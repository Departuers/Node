package cn.shiyu.bobo.directed.Euler;

import cn.shiyu.bobo.directed.WeightedGraphByDirected;

/**
 * 有向图的欧拉回路的充分必要条件:每个点的入度等于出度,该图就存在欧拉回路
 */
public class EulerLoop {
    private WeightedGraphByDirected G;
    private boolean[] visited;
    private int count;//联通分量个数

    public EulerLoop(WeightedGraphByDirected wg) {
        this.G = wg;
        visited = new boolean[wg.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                DFS(v);
                count++;
            }
        }
    }

    /**
     * 有向图的欧拉回路的充分必要条件:每个点的入度等于出度,该图就存在欧拉回路
     *
     * @return 是否有欧拉回路
     */
    public boolean hasEulerLoop() {
        if (count > 1) return false;
        for (int v = 0; v < G.V(); v++) {
            if (G.inDegree(v) != G.outDegree(v)) {
                return false;
            }
        }
        return true;
    }

    public void DFS(int v) {
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w])
                DFS(w);
        }
    }
}
