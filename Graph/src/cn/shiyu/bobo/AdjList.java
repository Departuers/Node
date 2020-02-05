package cn.shiyu.bobo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 邻接表
 * 空间复杂度O(V+E)
 * 时间复杂度:建立邻接表O(E*V)//需要判断平行边
 * 使用HashSet哈希表O(1),//无序
 * 或者TreeSet红黑树O(logV)//保持了节点顺序,相比(哈希表)更节省空间,logV的速度也很快
 * O(1)<O(log n)<O(n)   如果n取100万,log n=200
 * 1<20<100万
 */
public class AdjList {
    private int V;//多少个顶点
    private int E;//多少条边
    private LinkedList<Integer>[] adj;//图的结构

    public AdjList(String fileName) {
        File file = new File(fileName);
        try{
            Scanner sc = new Scanner(file);
            V = sc.nextInt();
            if (V < 0) throw new IllegalArgumentException("V Must >=0");
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<Integer>();
            }

            E = sc.nextInt();//边的数量
            if (E < 0) throw new IllegalArgumentException("E Must >=0");
            for (int i = 0; i < E; i++) {
                int a = sc.nextInt();
                validateVertex(a);//对顶点进行合法性判断
                int b = sc.nextInt();
                validateVertex(b);//对顶点进行合法性判断
                if (a == b) throw new IllegalArgumentException("自环边非法");
                if (adj[a].contains(b)) throw new IllegalArgumentException("平行边非法");//跟链表长度有关,最多可以达到V-1(节点数量-1)

                adj[a].add(b);//无向图,对称,所以两个都要赋值为1
                adj[b].add(a);
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
        return adj[v].contains(w);
    }

    /**
     * 返回一个LinkedList,记录节点v分别与哪个顶点相连
     *
     * @param v 节点
     * @return 返回那条邻接表
     */
    public LinkedList<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 查看v点有多少条边与之相连
     *
     * @param v 传入的点
     * @return 边的数量
     */
    public int degree(int v) {
        return adj(v).size();//复用方法
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
        for (int v = 0; v < V; v++) {//遍历所有顶点
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {//遍历其中每个顶点对应的链表
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjList adjList = new AdjList("g.txt");
        System.out.println(adjList);
    }
}