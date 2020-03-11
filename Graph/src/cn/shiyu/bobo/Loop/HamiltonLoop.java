package cn.shiyu.bobo.Loop;

import cn.shiyu.bobo.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈密尔顿回路
 */
@SuppressWarnings("all")
public class HamiltonLoop {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph g) {
        this.G = g;
        end = -1;//通过end值判断是否存在哈密尔顿回路
        visited = new boolean[g.getV()];
        pre = new int[g.getV()];
        //从0开始,是因为哈密尔顿回路一定会经过0,
        //每个顶点都会经过,从开始都可以
        dfs(0, 0, g.getV());
    }

    /**
     * @param v
     * @param parent
     * @param left
     * @return
     */
    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        pre[v] = parent;
        left--;
        if (left == 0 && G.hasEdge(v, 0)) {
            end = v;//找到路径,v就是最后一个顶点
            return true;
        }
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) {
                    return true;
                }
            }
//            else if (w == 0 && left == 0) {
//                end = v;//找到路径,v就是最后一个顶点
//                return true;
//            }都是正确的
        }
        //left++;left作为函数参数,递归过程中维持函数状态,
        // 返回上一层自动使用上一层函数的left,
        // 这一层left的改变,不影响上一层left,是一个值传递,没有对地址进行操作
        //如果这里的left是一个成员变量,那么left需要++回去,回溯
        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (end == -1) return res;
        while (end != 0) {
            res.add(end);
            end = pre[end];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g3.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());
    }
}
