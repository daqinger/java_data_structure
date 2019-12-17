package code.tree;
/*
* 哈夫曼树的节点类
* */
public class HuffmanNode {
    public int weight;  //节点的权值
    public int flag;    //节点是否加入哈夫曼树的标志
    public HuffmanNode parent,lChild,rChild;//父节点和左右孩子节点
    public HuffmanNode(){
        this(0);    //默认构造的节点权值为0
    }

    public HuffmanNode(int weight){
        this.weight = weight;   //构造具有权值的节点
        flag = 0;
        parent = lChild = rChild = null;
    }
}
