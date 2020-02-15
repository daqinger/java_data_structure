package code.Graph;
/**
 * 图的邻接表存储表示中的边（或弧）节点类
 *
 * */
public class ArcNode {
    public int adjVex;//该弧所指向的顶点位置

    public int value;//边或弧的权值

    private ArcNode nextArc;//指向下一条弧

    public ArcNode(int adjVex, int value, ArcNode nextArc) {
        this.adjVex = adjVex;
        this.value = value;
        this.nextArc = nextArc;
    }

    public ArcNode(int adjVex, int value) {
        this(adjVex,value,null);
    }

    public ArcNode(int adjVex) {
        this(adjVex,0,null);
    }

    public ArcNode() {
        this(-1,0,null);
    }

    public int getAdjVex() {
        return adjVex;
    }

    public int getValue() {
        return value;
    }

    public ArcNode getNextArc() {
        return nextArc;
    }

    public void setAdjVex(int adjVex) {
        this.adjVex = adjVex;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNextArc(ArcNode nextArc) {
        this.nextArc = nextArc;
    }
}
