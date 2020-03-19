package cn.shiyu.bobo.directed.TopoSort;

import cn.shiyu.bobo.directed.GraphByDirected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//强联通分量,有点复杂 O(V+E)
public class SCC {

    private GraphByDirected G;
    private int[] visited;
    private int sccCount;
    private ArrayList<Integer> post;
    private GraphByDirected Start;

    public SCC(GraphByDirected Ge) {
        this.Start = Ge;
        if (!Ge.isDirected()) throw new IllegalArgumentException("强联通分量针对有向图");
        visited = new int[Ge.V()];
        Arrays.fill(visited, -1);//值为-1代表没有遍历到
        post = new ArrayList<Integer>();
        sccCount = 0;

        this.G = Ge.reverseGraph();
        System.out.println(G.toString());

        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1)
                dfs(v);
        }
        Collections.reverse(post);//此时得到了反图的dfs后序遍历结果的逆序
        //下一部求取强联通分量

        Arrays.fill(visited, -1);//复用visited
        // 对于有向图,求该图的反图dfs后序遍历的逆序,也就是post数组,
        // 使用该post顺序遍历原图,即可求出强联通分量
        for (Integer v : post) {
            if (visited[v] == -1) {
                dfs(v, sccCount);//dfs(v, ccCount++)也可以
                //上面那行会把从0开始能遍历到的元素,代表的索引下标的值都赋值为ccCount
                sccCount++;
            }
        }
    }

    public int CcCount() {
//        for (int i : visited) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
        return sccCount;
    }

    /**
     * 判断是否两个顶点是否联通
     *
     * @param v 顶点v
     * @param w 顶点w
     * @return 返回值为true或者false
     */
    public boolean isStronglyConnected(int v, int w) {
        G.validateVertex(v);//判断合法性
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    /**
     * @return 返回每一个联通分量包含哪个顶点
     */
    public ArrayList<Integer>[] components() {
        //ccCount代表有多少联通分量
        ArrayList<Integer>[] res = new ArrayList[sccCount];
        for (int i = 0; i < sccCount; i++) {
            res[i] = new ArrayList<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
            //精髓:visited中存的值,刚好可以对应返回结果res中的下标,非常精妙
        }
        return res;
    }

    /**
     * DFS用来表示联通分量
     *
     * @param v    从哪个节点开始
     * @param ccid 每个联通增量ID
     */
    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (Integer w : Start.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = 1;
        for (Integer w : G.adj(v)) {
            if (visited[w] == -1)
                dfs(w);
        }
        post.add(v);
    }

    public static void main(String[] args) {
        GraphByDirected s = new GraphByDirected("g3.txt", true);
        SCC g = new SCC(s);
        System.out.println(g.CcCount());

        System.out.println();
        System.out.println(Arrays.toString(g.visited));
        ArrayList<Integer>[] ccc = g.components();
        for (int ccid = 0; ccid < ccc.length; ccid++) {
            System.out.print("强联通分量" + ccid + ": ");
            for (Integer w : ccc[ccid]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}