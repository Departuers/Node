package cn.shiyu.bobo.Birdge;

public class Edge {
    private int v;
    private int w;

    @Override
    public String toString(){
        return String.format("%d-%d", v, w);
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
