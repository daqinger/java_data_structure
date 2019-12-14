package code.Stack;

public class SqStack implements IStack{
    private Object[] stackElem;//对象数组
    private int top;//在非空栈中，top始终指向栈顶元素的下一个存储位置，当栈为空时，top值为0

    //构造函数
    public SqStack(int maxSize) {
        top = 0;
        stackElem = new Object[maxSize];//为栈分配maxSize个存储单元
    }

    //栈置空
    @Override
    public void clear() {
        top = 0;
    }

    //判断栈是否为空
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    //求栈中数据元素个数
    @Override
    public int length() {
        return top;
    }

    //取栈顶元素
    @Override
    public Object peek() {
        if(!isEmpty()){
            return stackElem[top-1];
        }else{
            return null;
        }
    }

    //入栈
    @Override
    public void push(Object x) throws Exception {
        if(top == stackElem.length)//栈满
            throw new Exception("栈已满");
        else
            stackElem[top++] = x;//先将X赋值给stackElem[top],再将top加一
    }

    //出栈
    @Override
    public Object pop() {
        if(isEmpty())
            return null;
        else
            return stackElem[--top];//先将top减一，再返回stackElem[top]的值
    }

    //输出栈中所有元素（从栈顶到栈底元素）
    public void display(){
        for(int i = top-1;i>=0;i--){
            System.out.print(stackElem[i].toString()+" ");//输出
        }
    }
}
