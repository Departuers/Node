package cn.shiyu.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接表
 */
public class GraphList {

    public int val;
    private List<GraphList> neighbors;// 邻居
    public boolean checked = false;

    public GraphList(int val) {
        this.val = val;
    }

    public GraphList getneighbors(int i) {
        return neighbors.get(i);
    }

    public void add(GraphList node) {
        if (this.neighbors == null) {
            this.neighbors = new ArrayList<>();
        }
        this.neighbors.add(node);
    }

    public int getsize() {
        return neighbors.size();
    }

    public static void main(String[] args) {
        GraphList n1 = new GraphList(1);
        GraphList n2 = new GraphList(2);
    }
}