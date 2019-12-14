package code.Stack;

import code.list.Node;

public class LinkStack implements IStack {
    private Node top;//栈顶元素的引用

    //将栈置空
    @Override
    public void clear() {
        top = null;
    }

    //判断栈是否为空
    @Override
    public boolean isEmpty() {
        return top==null;
    }

    //求栈的长度
    @Override
    public int length() {
        Node p = top;
        int length = 0;
        while(p!=null){
            p  = p.next;
            ++length;
        }
        return length;
    }

    //取栈顶元素并返回其值
    @Override
    public Object peek() {
        if(!isEmpty())
            return top.data;
        else
            return null;
    }

    //入栈
    @Override
    public void push(Object x) throws Exception {
        Node p = new Node(x);
        p.next = top;
        top = p;
    }

    //出栈
    @Override
    public Object pop() {
        if(isEmpty()){
            return null;
        }else{
            Node p = top;
            top = top.next;
            return p.data;
        }
    }

    //输出栈中所有数据元素（顶到底）
    public void display(){
        Node p = top;
        while(p!=null){
            System.out.print(p.data.toString()+" ");
            p = p.next;//指针后移
        }
    }
}

