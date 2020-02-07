package cn.shiyu.bobo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 邻接矩阵(主要处理无向无权简单图)
 * 空间复杂度O(V^2)  瓶颈!!!节点多了消耗太大资源,稀疏图
 * 时间复杂度
 * 建立图,也就是二维数组,O(E),对两个顶点,赋值为1
 * 查看两个节点是否相邻O(1)
 * 求一个点的相邻节点O(V)   瓶颈!!!
 */
public class AdjMatrix {
    private int V;//多少个顶点
    private int E;//多少条边
    private int[][] adj;//图的结构

    public AdjMatrix(String fileName) {
        File file = new File(fileName);
        //  try (Scanner sc = new Scanner(file)) {
        try {
            Scanner sc = new Scanner(file);
            V = sc.nextInt();
            if (V < 0) throw new IllegalArgumentException("V Must >=0");
            adj = new int[V][V];
            E = sc.nextInt();
            if (E < 0) throw new IllegalArgumentException("E Must >=0");
            for (int i = 0; i < E; i++) {
                int a = sc.nextInt();
                validateVertex(a);//对顶点进行合法性判断
                int b = sc.nextInt();
                validateVertex(b);//对顶点进行合法性判断
                if (a == b) throw new IllegalArgumentException("自环边非法");
                if (adj[a][b] == 1) throw new IllegalArgumentException("平行边非法");

                adj[a][b] = 1;//无向图,对称,所以两个都要赋值为1
                adj[b][a] = 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取顶点数量
    public int getV() {
        return V;
    }

    //获取边的数量
    public int getE() {
        return E;
    }

    /**
     * 查看这两个顶点之间是否有边
     *
     * @param v 顶点v
     * @param w 订单w
     * @return 是否有边
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    /**
     * 查看一个顶点,分别与哪个顶点相连
     *
     * @param v 输入顶点
     * @return res记录, 与v相连的每个顶点
     */
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < V; i++) {
            //看做邻接矩阵,第v排,有多少个1,就是
            //再res记录,与v相连的每个顶点
            if (adj[v][i] == 1)
                res.add(i);
        }
        return res;
    }

    /**
     * 查看v点有多少条边与之相连
     *
     * @param v 传入的点
     * @return 边的数量
     */
    public int degree(int v) {
        return adj(v).size();
    }

    /**
     * 判断传来的顶点是否合法
     *
     * @param v 顶点
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) throw new IllegalArgumentException(v + "is not");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("V=").append(V);
        sb.append(", E=").append(E).append('\n');
        for (int v = 0; v < V; v++) {
            sb.append('{');
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d", adj[v][j]));
                if (j != V - 1)
                    sb.append(',');
            }
            sb.append('}').append(',');
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g2.txt");
        System.out.println(adjMatrix);
    }
}