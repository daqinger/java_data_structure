package code.Stack;

public interface IStack {
    public void clear();
    public boolean isEmpty();
    public int length();    //返回数据元素个数
    public Object peek();   //取栈顶元素并返回其值
    public void push(Object x)throws Exception; //将数据元素压入栈顶
    public Object pop();    //删除并返回栈顶元素
}
