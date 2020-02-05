package cn.shiyu.bobo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 无向带权图
 * Map用来存储权重
 */
public class WeightedGraph implements Cloneable {
    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;

    public WeightedGraph(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            V = sc.nextInt();
            if (V < 0) throw new IllegalArgumentException("V dis");
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<Integer, Integer>();
            }

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
                adj[b].put(a, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V)
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
        if (adj[v].containsKey(w)) E--;//判断有没有传来的这条边(判断有没有v和w组成的边)
        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet())
                sb.append(String.format("(%d: %d) ", entry.getKey(), entry.getValue()));
            sb.append('\n');
        }
        return sb.toString();
    }

    //重写拷贝(复制)方法
    @Override
    public Object clone() {

        try {
            WeightedGraph cloned = (WeightedGraph) super.clone();//对于对象的操作
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
}
