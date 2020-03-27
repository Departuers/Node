package cn.shiyu.bobo.directed.Flow;

public class MaxFlow {
    private WgraphDirected network;
    private int s, t;
    private int maxFlow = 0;
    private WgraphDirected rg;
    public MaxFlow(WgraphDirected network, int s, int t) {
        if (!network.isDirected()) throw new IllegalArgumentException("有向图才能求最大流");
        if (network.V() < 2) throw new IllegalArgumentException("起码有一个源点,一个汇点");
        if (s == t) throw new IllegalArgumentException("源点和汇点必须不同");

        this.s = s;
        this.t = t;
        this.network = network;

    }
}
