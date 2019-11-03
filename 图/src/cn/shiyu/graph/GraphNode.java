package cn.shiyu.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接表
 */
public class GraphNode {

    public int val;
    private List<GraphNode> neighbors;// 邻居
    public boolean checked = false;

    public GraphNode(int val) {
        this.val = val;
    }

    public GraphNode getneighbors(int i) {
        return neighbors.get(i);
    }

    public void add(GraphNode node) {
        if (this.neighbors == null) {
            this.neighbors = new ArrayList<>();
        }
        this.neighbors.add(node);
    }

    public int getsize() {
        return neighbors.size();
    }

    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
    }
}