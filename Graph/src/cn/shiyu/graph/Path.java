package cn.shiyu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 求两点之间的路径是什么,仅仅是这两点,不需要求多余数据
 */
public class Path {
    private AdjSet G;
    private boolean visited[];
    private int s;//
    private int t;
    private int[] pre;//每个顶点需要存储它前面那个节点,也就是从哪来的

    //深度优先遍历从0开始
    public Path(AdjSet G, int s, int t) {
        G.validateVertex(t);
        G.validateVertex(s);

        this.t = t;
        this.s = s;
        this.G = G;

        visited = new boolean[G.getV()];
        pre = new int[G.getV()];
        Arrays.fill(pre, -1);
        dfs(s, s);//从起点到终点
        //源本身没有父节点,记录它自己就行了
//        for (boolean b : visited) {
//            System.out.print(b + " ");
//        }
//        System.out.println();
    }

    /**
     * @param v      开始
     * @param parent 记录节点从哪来的,
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if (v == t) return true;
        for (Integer w : G.adj(v)) {//遍历v的相邻节点
            if (!visited[w]) {
                if (dfs(w, v)) return true;
                //因为w是从v过来的,递归的看能不能从v的相邻节点到达t
            }
        }
        return false;
    }

    /**
     * 判断从起始节点能不能到达t
     *
     * @param t 要到达的节点
     * @return 返回能不能到
     */
    public boolean isConnected(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    /**
     * 寻找路径
     *
     * @return 可遍历的:存的是路径
     */
    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected(t)) return res;//无法到达节点t
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
        AdjSet s = new AdjSet("g.txt");
        Path ss = new Path(s, 0, 1);
        System.out.println(ss.path());
        System.out.println();
        Path ss1 = new Path(s, 0, 6);
        System.out.println(ss1.path());
    }
}
