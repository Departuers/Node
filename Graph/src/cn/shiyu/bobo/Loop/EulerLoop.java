package cn.shiyu.bobo.Loop;

import cn.shiyu.bobo.CC.CC2;
import cn.shiyu.bobo.Graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 欧拉回路
 */
public class EulerLoop {
    private Graph G;

    public EulerLoop(Graph G) {
        this.G = G;
    }

    /**
     * O(n)
     * 每个点的度数是偶数,则图存在欧拉回路
     *
     * @return 是否存在欧拉回路
     */
    public boolean hasEulerLoop() {
        CC2 cc = new CC2(G);
        if (cc.CcCount() > 1) return false;
        for (int v = 0; v < G.getV(); v++) {
            if (G.degree(v) % 2 == 1) return false;
        }
        return true;
    }

    //Hierholzer算法
    public ArrayList<Integer> result() throws CloneNotSupportedException {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (!hasEulerLoop()) return res;
        //下面开始寻找欧拉回路
        Graph g = (Graph) G.clone();
        Stack<Integer> stack = new Stack<Integer>();
        int curv = 0;
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (g.degree(curv) != 0) {
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);//栈中内容是倒序,但pop就是正序
                curv = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Graph g = new Graph("g34.txt");
        EulerLoop e = new EulerLoop(g);
        System.out.println(e.result());
    }
}
