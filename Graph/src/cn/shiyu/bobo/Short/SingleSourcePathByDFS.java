package cn.shiyu.bobo.Short;

import cn.shiyu.bobo.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 单源路径(使用DFS是实现)
 */
public class SingleSourcePathByDFS {
    private Graph G;
    private boolean visited[];
    private int s;//单源路径,s就代表源
    private int[] pre;//每个顶点需要存储它前面那个节点,也就是从哪来的

    //深度优先遍历从0开始
    public SingleSourcePathByDFS(Graph G, int s) {
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        visited = new boolean[G.getV()];
        pre = new int[G.getV()];
        dfs(s, s);//要解决单源路径问题,所以只从源走就够了
        //源本身没有父节点,记录它自己就行了
    }

    /**
     * @param v      开始
     * @param parent 记录节点从哪来的,
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);//因为w是从v过来的
            }
        }
    }

    /**
     * 判断从源节点能不能到达t
     *
     * @param t 要到达的节点
     * @return 返回能不能到
     */
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) return res;//无法到达节点t
        int cur = t;//用来暂存,用目标顶点往回走
        while (cur != s) {//从目标顶点走到源顶点,直到找到源,
            res.add(cur);
            cur = pre[cur];//赋值为前一个节点
        }
        res.add(s);//因为当cur==s时循环结束,没有添加源,所以手动添加
        Collections.reverse(res);//是从目标顶点往前放的,所以要反转
        return res;
    }

    public static void main(String[] args) {
        Graph s = new Graph("g.txt");
        SingleSourcePathByDFS ssp = new SingleSourcePathByDFS(s, 0);
        Iterable<Integer> path = ssp.path(6);
        System.out.println(path);
    }
}
