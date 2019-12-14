package code.Queue;

import code.list.Node;

public class PriorityQueue implements IQueue {
    private Node front;//队首的引用
    private Node rear;//队尾的引用

    //构造函数
    public PriorityQueue() {
        front = rear = null;
    }

    //队列置空
    @Override
    public void clear() {
        front = rear = null;
    }

    //队列判空
    @Override
    public boolean isEmpty() {
        return front==null;
    }

    //求队列长度
    @Override
    public int length() {
        Node p = front;
        int length = 0;
        while(p!=null){
            p = p.next;
            ++length;
        }
        return length;
    }

    //读取队首元素
    @Override
    public Object peek() {
        if(front == null)
            return null;
        else
            return front.data;
    }

    //入队
    @Override
    public void offer(Object x) throws Exception {
        PriorityQData pn = (PriorityQData)x;
        Node s = new Node(pn);  //构造一个新节点
        if(front == null)   //队列为空
            front = rear = s;//修改队列的首位节点
        else{
            Node p = front,q = front;
            //新结点的数据域值与队列节点的数据域值相比较
            while(p!=null&&pn.priority>=((PriorityQData)p.data).priority){
                q = p;
                p = p.next;
            }
            if(p == null){  //p为空，表示遍历到了队列尾部
                rear.next = s;//将新节点加入到队尾
                rear = s;//修改队尾指针
            }else if(p == front){//p的优先级大于队首节点的优先级
                s.next = front;//将新结点加入到队首
                front = s;//修改队首节点
            }else{  //新结点加入队列中部
                q.next = s;
                s.next = p;
            }
        }
    }

    //出队
    @Override
    public Object poll() {
        if(front == null)
            return null;
        else{
            Node p = front;
            front = p.next;
            return p.data;
        }
    }

    //输出
    public void display(){
        if(!isEmpty()){
            Node p = front;
            while(p!=rear.next){
                PriorityQData q = (PriorityQData)p.data;
                System.out.println(q.elem+" "+q.priority);
                p = p.next;
            }
        }else{
            System.out.println("此队列为空");
        }
    }

}
