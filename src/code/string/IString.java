package code.string;
/*
* 串的接口定义
*
* */
public interface IString {
    public void clear();//置空操作
    public boolean isEmpty();//判空操作
    public int length();//求字符串长度
    public char charAt(int index);//读取并返回串中第index个字符的值，范围 0 <= index < length()
    public IString substring(int begin,int end);//截取字符串，返回当前串从 begin 到 end-1的值
    public IString insert(int offset,IString str);//插入字符串你，在第offset之前插入串str
    public IString delete(int begin,int end);//删除，从begin 到 end-1
    public IString concat(IString str);//连接操作，把str串连接到当前串的后面
    public int compareTo(IString str);//将当前串与目标串进行比较，若当前串大于str，则返回一个整数，等于返回0,小于，返回-1
    public int indexOf(IString str,int begin);//子串定位操作，搜索与str相等的子串，成功则返回str在当前位置
}
