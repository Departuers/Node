package cn.shiyu.bobo.directed.traversal;

import cn.shiyu.bobo.directed.WeightedGraphByDirected;

import java.util.ArrayList;

/**
 * 深度优先遍历O(V+E),有向带权图
 */
public class WeightedGraphByDirectedDFS {

    private ArrayList<Integer> pre = new ArrayList<Integer>();//存储图的深度优先前序遍历
    private ArrayList<Integer> post = new ArrayList<Integer>();//存储图的深度优先后序遍历

    private WeightedGraphByDirected G;
    private boolean[] visited;

    //深度优先遍历从0开始
    public WeightedGraphByDirectedDFS(WeightedGraphByDirected G) {
        this.G = G;
        visited = new boolean[G.V()];
        // dfs(0);没有跟其他顶点联通的不会被遍历到,从节点0开始遍历

        //即使没和任何一个顶点相连,也可以遍历到
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);//深度优先前序遍历
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);//深度优先后序遍历
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }

    public static void main(String[] args) {
        WeightedGraphByDirected g = new WeightedGraphByDirected("g7.txt", true);
        WeightedGraphByDirectedDFS s = new WeightedGraphByDirectedDFS(g);
        Iterable<Integer> order = s.postOrder();
        for (Integer o : order) {
            System.out.print(o + " ");
        }
        System.out.println();
        ArrayList<Integer> pre = s.pre;
        for (Integer o : pre) {
            System.out.print(o + " ");
        }
    }
}