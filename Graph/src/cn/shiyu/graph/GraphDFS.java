package cn.shiyu.graph;

import java.util.ArrayList;

/**
 * 深度优先遍历
 */
public class GraphDFS {
    private ArrayList<Integer> pre = new ArrayList<>();//存储图的深度优先前序遍历
    private ArrayList<Integer> post = new ArrayList<>();//存储图的深度优先后序遍历

    private AdjSet G;
    private boolean visited[];

    //深度优先遍历从0开始
    public GraphDFS(AdjSet G) {
        this.G = G;
        visited = new boolean[G.getV()];
        // dfs(0);没有跟其他顶点联通的不会被遍历到

        //即使没和任何一个顶点相连,也可以遍历到
        for (int v = 0; v < G.getV(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }

    public static void main(String[] args) {
        AdjSet s = new AdjSet("g.txt");
        GraphDFS g = new GraphDFS(s);
        Iterable<Integer> order = g.postOrder();
        for (Integer o : order) {
            System.out.println(o);
        }
    }
}
