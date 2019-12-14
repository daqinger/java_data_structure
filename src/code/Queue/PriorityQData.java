package code.Queue;
/*
* 优先级队列
* 一种带有优先级的队列
* 约定关键字最小的数据元素具有最高优先级，并且排在队首
* 优先级队列中的数据元素插入也不仅仅限制在队尾进行，而是顺序插入到队列的 合适位置，以确保队列的优先级顺序
*
*
* 此类为优先级队列的节点中data类的描述
* */
public class PriorityQData {
    public Object elem; //节点类的数据元素值
    public int priority;//节点类的优先数
    //构造函数
    public PriorityQData() {
        this.elem = elem;
        this.priority = priority;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
