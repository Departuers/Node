package cn.shiyu.bobo.directed.Flow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
//
public class WgraphDirected {

    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;
    private boolean directed;

    public WgraphDirected(String filename, boolean directed) {
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
            E = sc.nextInt();//边
            if (E < 0) throw new IllegalArgumentException("边小于0");
            for (int i = 0; i < E; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int weight = sc.nextInt();
                addedge(a, b, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public WgraphDirected(String fileName) {
        this(fileName, false);
    }

    public WgraphDirected(int v, boolean directed) {
        this.V = v;
        this.directed = directed;
        this.E = 0;
        adj = new TreeMap[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new TreeMap<Integer, Integer>();
        }
    }

    public void addedge(int a, int b, int v) {
        validateVertex(a);
        validateVertex(b);
        if (a == b) throw new IllegalArgumentException("自环边非法");
        if (adj[a].containsKey(b)) throw new IllegalArgumentException("没有平行边");
        adj[a].put(b, v);
        if (!directed) adj[b].put(a, v);
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

    public boolean isDirected() {
        return directed;
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

    public static void main(String[] args) {
        cn.shiyu.bobo.directed.WeightedGraphByDirected wg = new cn.shiyu.bobo.directed.WeightedGraphByDirected("g4.txt", true);
        System.out.println(wg);

        for (int v = 0; v < wg.V(); v++) {
            System.out.println(wg.inDegree(v) + "  " + wg.outDegree(v));
        }
    }
}

