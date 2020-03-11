package cn.shiyu.bobo.directed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 有向(或者无向)带权图
 */
public class WeightedGraphByDirected implements Cloneable {
    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;
    private boolean directed;
    private int[] inDegrees, outDegrees;//入度,和出度数组

    public WeightedGraphByDirected(String filename, boolean directed) {
        this.directed = directed;
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            V = sc.nextInt();
            if (V < 0) throw new IllegalArgumentException("V dis");
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<Integer, Integer>();
            }
            this.inDegrees = new int[V];
            this.outDegrees = new int[V];
            E = sc.nextInt();//边
            if (E < 0) throw new IllegalArgumentException("边小于0");
            for (int i = 0; i < E; i++) {
                int a = sc.nextInt();
                validateVertex(a);
                int b = sc.nextInt();
                validateVertex(b);
                int weight = sc.nextInt();
                if (a == b) throw new IllegalArgumentException("不处理自环边");
                if (adj[a].containsKey(b)) throw new IllegalArgumentException("不处理平行边");
                adj[a].put(b, weight);
                if (!directed)
                    adj[b].put(a, weight);
                if (directed) {//如果是有向图,添加a-b这条边,a是出度,代表从a来,b是入度,
                    outDegrees[a]++;
                    inDegrees[b]++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public WeightedGraphByDirected(String fileName) {
        this(fileName, false);
    }

    public void validateVertex(int v) {
        if (v > V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    /**
     * 获取两个点之间的那条边的权重
     *
     * @param v 点v
     * @param w 点w
     * @return v和w之间的权重
     */
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) return adj[v].get(w);//首先判断这两个点是否有边
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    /**
     * 删除两个点之间的边
     *
     * @param v 点v
     * @param w 点w
     */
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);//对传来的点进行合法性判断
        if (adj[v].containsKey(w)) {
            E--;//判断有没有传来的这条边(判断有没有v和w组成的边)
            if (directed) {
                outDegrees[v]--;//出度代表从v出去的,
                inDegrees[w]--;//入度代表指向w的边,
                //删除v-w这条边,v的出度--,w的入度--
            }
        }
        adj[v].remove(w);
        if (!directed)
            adj[w].remove(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d directed: %b\n", V, E, directed));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet())
                sb.append(String.format("(%d: %d) ", entry.getKey(), entry.getValue()));
            sb.append('\n');
        }
        return sb.toString();
    }

    public int inDegree(int v) {
        if (!directed) throw new RuntimeException("无向图没有入度,只有度");
        validateVertex(v);
        return inDegrees[v];
    }

    public int outDegree(int v) {
        if (!directed) throw new RuntimeException("无向图没有出度,只有度");
        validateVertex(v);
        return outDegrees[v];
    }

    //重写拷贝(复制)方法
    @Override
    public Object clone() {

        try {
            WeightedGraphByDirected cloned = (WeightedGraphByDirected) super.clone();//对于对象的操作
            cloned.adj = new TreeMap[V];//
            for (int v = 0; v < V; v++) {//遍历每个邻接表
                cloned.adj[v] = new TreeMap<Integer, Integer>();
                for (Map.Entry<Integer, Integer> entry : adj[v].entrySet())//把邻接表的每一个键值对放进拷贝的对象里面去
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        WeightedGraphByDirected wg = new WeightedGraphByDirected("g4.txt", true);
        System.out.println(wg);

        for (int v = 0; v < wg.V(); v++) {
            System.out.println(wg.inDegree(v) + "  " + wg.outDegree(v));
        }
    }
}
