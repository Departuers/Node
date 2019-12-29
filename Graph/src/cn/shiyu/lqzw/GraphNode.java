package cn.shiyu.lqzw;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接表,个人觉得,不是很好
 */
public class GraphNode {
    public int val;
    private List<GraphNode> neighbors;//邻居
    public boolean checked = false;

    public GraphNode(int val) {
        this.val = val;
    }

    public GraphNode getNeighbor(int i) {
        return neighbors.get(i);
    }

    public void add(GraphNode node) {
        if (this.neighbors == null) {
            this.neighbors = new ArrayList<>();
        }
        this.neighbors.add(node);
    }

    public int size() {
        return this.neighbors.size();
    }
}
