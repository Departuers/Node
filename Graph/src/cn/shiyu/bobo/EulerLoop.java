package cn.shiyu.bobo;

/**
 * 欧拉回路
 */
public class EulerLoop {
    private Graph G;

    public EulerLoop(Graph G) {
        this.G = G;
    }

    public boolean hasEulerLoop() {
        CC2 cc = new CC2(G);
        if (cc.CcCount() > 1) return false;
        for (int v = 0; v < G.getV(); v++) {
            if (G.degree(v) % 2 == 1) return false;
        }
        return true;
    }
}
