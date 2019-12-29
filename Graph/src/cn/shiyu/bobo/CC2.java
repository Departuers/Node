package cn.shiyu.bobo;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2.具体求解无向图的联通分量,
 * 每个联通分量中有多少个节点,每个联通分量的节点分别是哪个
 * 主要是visited改成int[]
 */
public class CC2 {
    private Graph G;
    private int[] visited;
    private int ccCount = 0;

    public CC2(Graph G) {
        this.G = G;
        visited = new int[G.getV()];
        Arrays.fill(visited, -1);//值为-1代表没有遍历到
        /**
         * 实现现象:Visited使用int数组,数组中元素的值为0代表它们是一个联通分量
         * 值为1代表它们是一个联通分量,是一个整体,
         * 有点并查集思想,把一个联通分量的值,在visited数组中用一个非-1数字表示
         *
         */
        for (int v = 0; v < G.getV(); v++) {
            if (visited[v] == -1) {
                dfs(v, ccCount);//dfs(v, ccCount++)也可以
                //上面那行会把从0开始能遍历到的元素,代表的索引下标的值都赋值为ccCount
                ccCount++;
            }
        }
    }

    public int CcCount() {
//        for (int i : visited) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
        return ccCount;
    }

    /**
     * 判断是否两个顶点是否联通
     *
     * @param v 顶点v
     * @param w 顶点w
     * @return 返回值为true或者false
     */
    public boolean isConnected(int v, int w) {
        G.validateVertex(v);//判断合法性
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    /**
     * @return 返回每一个联通分量包含哪个顶点
     */
    public ArrayList<Integer>[] components() {
        //ccCount代表有多少联通分量
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.getV(); v++) {
            res[visited[v]].add(v);
            //精髓:visited中存的值,刚好可以对应返回结果res中的下标,非常精妙
        }
        return res;
    }

    /**
     * 用来表示联通增量
     *
     * @param v    从哪个节点开始
     * @param ccid 每个联通增量ID
     */
    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (Integer w : G.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public static void main(String[] args) {
        Graph s = new Graph("g.txt");
        CC2 g = new CC2(s);
        System.out.println(g.CcCount());
        System.out.println(g.isConnected(0, 5));

        System.out.println();
        ArrayList<Integer>[] ccc = g.components();
        for (int ccid = 0; ccid < ccc.length; ccid++) {
            System.out.print("联通增量"+ccid + ": ");
            for (Integer w : ccc[ccid]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
