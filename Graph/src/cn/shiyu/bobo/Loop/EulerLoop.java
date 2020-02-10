package cn.shiyu.bobo.Loop;

import cn.shiyu.bobo.CC.CC2;
import cn.shiyu.bobo.Graph;

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
}
