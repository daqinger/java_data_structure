package code.list;
/*
* 双向链表的节点类
* */
public class DuLNode {
    public Object data;     //数据域
    public DuLNode prior;    //存放指向前驱结点的指针域
    public DuLNode next;     //存放指向后继结点的指针域

    public DuLNode(){ }

    public DuLNode(Object data){
        this.data = data;
        this.prior = null;
        this.next = null;
    }
}
