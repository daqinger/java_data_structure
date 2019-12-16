package code.tree;

public class BiTreeNode {
    public Object data;//节点的数据域
    public BiTreeNode lChild,rChild;//左右孩子域
    public BiTreeNode() {
        this(null);
    }
    public BiTreeNode(Object data) {
        this(data,null,null);
    }
    public BiTreeNode(Object data,BiTreeNode lChild,BiTreeNode rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }
}
