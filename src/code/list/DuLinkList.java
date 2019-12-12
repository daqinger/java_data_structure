
package code.list;

        import java.util.Scanner;

/*
 * 双向循环链表
 *
 * */
public class DuLinkList implements Ilist{
    public DuLNode head;     //双向循环链表的头结点

    //双向循环链表的构造函数，构造只含有一个头节点的双向循环链表
    public DuLinkList(){
        head = new DuLNode();
        head.prior = head;
        head.next = head;
    }

    //从表尾到表头逆向创建双向循环链表的算法，其中n为该双向循环链表的节点个数
    public DuLinkList(int n) throws Exception{
        this();
        Scanner sc = new Scanner(System.in);
        for (int j = 0;j < n;j++ ){
            insert(0,sc.next());//生成新的节点插入到表头
        }
    }

    @Override
    public void clear() {
        this.head.next = null;
        this.head.prior = null;
        this.head.data = null;
    }

    @Override
    public boolean isEmpty() {
        return head.next ==null && head.prior == null;
    }

    @Override
    public int length() {
        DuLNode p = head.next;
        int length = 0;
        while (p != head){
            p = p.next;
            length++;
        }

        return length;
    }

    @Override
    public Object get(int i) throws Exception {
        DuLNode p = head.next;//初始化，p指向首节点，j为计数器
        int j = 0;
        while(p!=head&&j<i){//从首节点开始向后查找，直到p指向第i个结点或p为head
            p = p.next;//指向后继结点
            ++j;//计数器的值增1
        }
        if(j > i||p==head){
            throw new Exception("第"+i+"个元素不存在");
        }
        return p.data;

    }

    //在带头节点的双向循环链表中的插入操作
    @Override
    public void insert(int i, Object x) throws Exception {
        DuLNode p = head.next;
        int j = 0;
        while (!p.equals(head) && j < i){//寻找到插入位置i
            p = p.next;
            ++j;
        }
        if (j != i && !p.equals(head))  //i不合法
            throw new Exception("插入位置不合法");
        DuLNode s = new DuLNode(x);//生成新节点s
        p.prior.next = s;
        s.prior = p.prior;
        s.next = p;
        p.prior = s;
    }

    //带头结点的双向循环链表中的删除操作
    @Override
    public void remove(int i) throws Exception {
        DuLNode p = head.next;
        int j = 0;
        while (!p.equals(head) && j <i){
            p = p.next;
            ++j;
        }
        if (j != i){
            throw new Exception("删除位置不合理");
        }
        p.prior.next = p.next;
        p.next.prior = p.prior;
    }

    @Override
    public void display() {
        DuLNode node = head.next;
        while (!node.equals((head))){
            System.out.println(node.data+" ");
            node = node.next;
        }
        System.out.println();
    }
}