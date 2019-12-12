package code.list;
/*
* 链表
*
* */
import java.util.Scanner;

public class LinkList implements Ilist {
    public Node head;//单链表的头指针

    //单链表的构造函数
    public LinkList(){
        head = new Node();//初始化头节点
    }

    //构造一个长度为n的单链表
    public LinkList(int n,boolean Order) throws Exception{
        this();//初始化头节点
        if(Order){//用尾插法顺序建立单链表
            create1(n);
        }else {//用头插法逆序建立单链表
            create2(n);
        }
    }

    //用尾插法顺序建立单链表，其中n为单链表的结点个数
    public void create1(int n) throws Exception{
        Scanner sc = new Scanner(System.in);//构造用于输入的对象
        for(int j = 0;j<n;j++){//输入n个结点的数据域值
            insert(length(), sc.next());//生成新结点，插入到表尾
        }
    }

    //用头插法逆序建立单链表，其中n为单链表的节点个数
    public void create2(int n) throws Exception{
        Scanner sc = new Scanner(System.in);//构造用于输入的对象
        for(int j = 0;j<n;j++){//输入n个结点的数据域值
            insert(0, sc.next());//生成新结点，插入到表尾
        }

    }

    //将一个以及存在 的带头节点单链表重置为空表
    public void clear() {
        head.data = null;
        head.next = null;
    }

    //判断带头节点的单链表是否为空
    public boolean isEmpty() {
        return head.next ==null;//头结点后面是首节点
    }

    //求带头节点的单链表的长度
    public int length() {
        Node p = head.next;//初始化，p指向首节点，length为计数器
        int length = 0;
        while(p!=null){//从首节点开始向后查找，直到p为空
            p = p.next;//指向后继节点
            ++length;//长度增1
        }
        return length;
    }

    //读取到带头节点的单链表中的第i个节点（这里的第i个指的是从0开始计数）
    public Object get(int i) throws Exception {
        Node p = head.next;//初始化，p指向首节点，j为计数器
        int j = 0;
        while(p!=null&&j<i){//从首节点开始向后查找，直到p指向第i个结点或p为空
            p = p.next;//指向后继结点
            ++j;//计数器的值增1
        }
        if(j > i||p==null){
            throw new Exception("第"+i+"个元素不存在");
        }
        return p.data;
    }

    //在第i个节点前插入一个值为x的新节点
    public void insert(int i, Object x) throws Exception {
        Node p = head;//初始化p为头节点，j为计数器
        int j = -1;
        while(p!=null&&j<i-1){//寻找第i个结点的前驱
            p = p.next;
            ++j;
        }
        if(j>i-1||p==null){ //第一个条件若为真，则说明参数i的值小于0，若第二个条件为真，则说明参数i的值大于表长
            throw new Exception("插入位置不合法");
        }
        Node s = new Node(x);//生成新结点
        s.next = p.next;//修改连，使新结点插入单链表中
        p.next = s;
    }

    //删除第i个节点
    public void remove(int i) throws Exception {
        Node p = head;//初始化p指向头节点，j为计数器
        int j = -1;
        while(p.next!=null&&j<i-1){//寻找第i个结点的前驱
            p = p.next;
            ++j;
        }
        if(j>i-1||p.next == null){
            throw new Exception("删除位置不合法");
        }
        p.next = p.next.next;//修改链指针，使待删除结点从单链表中从单链表中脱离出来

    }

    //查找值为x的节点
    public int indexOf(Object x) {
        Node p = head.next;//初始化，p指向首结点，j为计数器
        int j = 0;//下面从单链表中 的首结点你开始查找，直到p.data为x或到达单链表的表尾
        while(p!=null&&!p.data.equals(x)){
            p = p.next;//指向下一个结点
            ++j;
        }
        if(p!=null){
            return j;
        }else{
            return -1;
        }
    }

    //输出所有节点
    public void display() {
        Node node = head.next;//取出带头节点的单链表中的首节点
        while(node!=null){
            System.out.print(node.data +" ");//输出节点的值
            node = node.next;//取下一个节点
        }
        System.out.println();//换行
    }

}
