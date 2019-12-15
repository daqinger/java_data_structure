package code.Queue;
/*
* 队列接口
* */
public interface IQueue {
    public void clear();//清空队列
    public boolean isEmpty();//判空
    public int length();//返回队列元素长度
    public Object peek();//读取队首元素
    public void offer(Object x)throws Exception;//入队
    public Object poll();//出队
}
