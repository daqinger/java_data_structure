package code.list;
/*
*
*
* */
public interface Ilist {
    public void clear();//置空
    public boolean isEmpty();//判空
    public int length();
    public Object get(int i) throws Exception;//返回线性表中第i个值
    public void insert(int i,Object x) throws Exception;
    public void remove(int i) throws Exception;
    public void display();
}
