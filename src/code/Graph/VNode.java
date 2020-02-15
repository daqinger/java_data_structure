package code.Graph;
/**
 * 图的邻接表存储表示中的顶点节点类
 * */
public class VNode {
    public Object data;//顶点信息
    public ArcNode firstArc;//指向第一条依附于该顶点的弧

    public VNode(Object data) {
        this(data,null);
    }

    public VNode(Object data, ArcNode firstArc) {
        this.data = data;
        this.firstArc = firstArc;
    }

    public VNode() {
        this(null,null);
    }

    public Object getData() {
        return data;
    }

    public ArcNode getFirstArc() {
        return firstArc;
    }
}
