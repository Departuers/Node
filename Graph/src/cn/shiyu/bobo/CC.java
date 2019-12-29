package cn.shiyu.bobo;

/**
 * 1.求无向图的联通分量(Connected Component)个数
 * 对于无向图:一张图的所有顶点,不一定都是连接起来的,一个互相连接的一部分,就被称为一个联通分量
 * 比如在一个公路系统,每个点表示一个城市,两个节点相连表示两个城市之间有公路联通,
 * 求出联通分量就可以求出在这个地图上有多少独立区域
 * <p>
 * 实现思路:在进行DFS的时候,首先判断当前节点有没有被遍历过,以visited中没有遍历过的节点作为开头进行遍历,下面跟一行count++;
 * 就可以求出有多少联通分量了
 */
public class CC {
    private Graph G;
    private boolean[] visited;
    private int ccCount = 0;

    public CC(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
        /**
         * 即使没和任何一个顶点相连,也可以遍历到
         * 实现思路:在进行DFS的时候,首先判断当前节点有没有被遍历过,
         * 以visited中没有遍历过的节点作为开头进行遍历,下面跟一行count++;
         * 就可以求出有多少联通分量了
         */
        for (int v = 0; v < G.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
                ccCount++;
            }
        }
    }

    public int CcCount() {
        return ccCount;
    }

    private void dfs(int v) {
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public static void main(String[] args) {
        Graph s = new Graph("g.txt");
        CC g = new CC(s);
        System.out.println(g.ccCount);
    }
}
