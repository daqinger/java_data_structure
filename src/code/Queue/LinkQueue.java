package code.Queue;

import code.list.Node;

public class LinkQueue implements IQueue {
    private Node front;//队首指针
    private Node rear;//队尾指针

    //构造函数
    public LinkQueue() {
        front = rear = null;
    }

    //队列置空
    @Override
    public void clear() {
        front = rear = null;
    }

    //判空
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

    //取队首元素
    @Override
    public Object peek() {
        if(front!=null)
            return front.data;
        else
            return null;
    }

    //入队
    @Override
    public void offer(Object x) throws Exception {
        Node p = new Node(x);
        if(front!=null){
            rear.next = p;
            rear = p;
        }else{
            front = rear = p;
        }
    }

    //出队
    @Override
    public Object poll() {
        if(front!=null){
            Node p = front;
            front = front.next;//队首节点出列
            if(p==rear) //被删除的节点是队尾节点时候
                rear=null;
            return p.data;
        }else
            return null;
    }

}
