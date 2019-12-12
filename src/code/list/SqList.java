package code.list;
/*
* 顺序表
*
* */
public class SqList implements Ilist {
    private Object []listElme;	//线性表储存空间
    private int curLen;		//线性表当前长度

    //顺序表类的构造函数，构造一个储存空间容量为maxSize的线性表
    public SqList(int maxSize) {
        curLen = 0;		//设置顺序表当前长度为0
        listElme = new Object[maxSize];		//为顺序表分配maxSize个春村单元
    }


    //将一个以及存在 的线性表重置为空表
    @Override
    public void clear() {
        curLen = 0;
    }

    //判断线性表中的数据元素个数是否为0，若为0，则返回true；否则返回false
    public boolean isEmpty() {
        return curLen==0;
    }

    //求线性表中的数据元素个数并返回其值
    public int length() {
        return curLen;
    }


    //读取到线性表中的第i个数据元素并由函数返回其值，其中i的取值范围为：0<=i<=length()-1,若i值不在此范围则抛出异常
    public Object get(int i) throws Exception{

        if(i<0||i>curLen-1)		//i小于0或者大一表长减一
            throw new Exception("第"+i+"个元素不存在");//抛出异常

        return listElme[i];//返回顺序表中第i个数据元素
    }

    //在线性表的第i个数据元素之前插入一个值为x的数据元素
    public void insert(int i, Object x) throws Exception {

        if(curLen==listElme.length)//判断顺序表是否已经满
            throw new Exception("顺序表已满");//抛出异常
        if(i<0||i>curLen)//i不合法
            throw new Exception("插入位置不合法");//抛出异常
        for(int j = curLen;j > i;j --){
            listElme[j] = listElme[j-1];
        }//插入位置及其以后的所有数据元素后移一位
        listElme[i] = x;//插入x
        curLen++;//表长加1

    }

    //删除并返回线性表中 第i个元素
    public void remove(int i) throws Exception{

        if(i<0||i>curLen-1){//i不合法
            throw new Exception("删除位置不合法");//抛出异常
        }
        for(int j = i;j<curLen-1;j++){
            listElme[j] = listElme[j+1];//被删除元素之后的所有数据左移一个存储位置
        }
        curLen--;//表长减一

    }

    //返回线性表中首次出现制定的数据元素的位序号，若线性表中不包含次数据元素，则返回-1
    public int indexOf(Object x) {

        int j = 0;//j指示顺序表中待比较的数据元素，起初值指示顺序表中第0个元素
        while(j<curLen&&!listElme[j].equals(x))//依次比较,如果j<curLen并且此项不等于x
            j++;
        if(j<curLen)//判断j的位置是否位于顺序表中
            return j;
        else {
            return -1;//值为x的数据元素再顺序表中不存在
        }

    }

    //输出线性表中的数据元素
    public void display() {

        for(int j = 0;j<curLen;j++)
            System.out.print(listElme[j]+"");
        System.out.println();
    }

}
