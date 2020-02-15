package code.Graph;
/*
 * 图的存储结构除了存储图中各个顶点的信息外，还要存储与顶点相关联的边的信息，
 * 图的常见存储结构有邻接矩阵，邻接表，邻接多重表，十字链表
 *
 * 图的接口
 *
 * */
public interface IGraph {

    /**创建一个图*/
    public void createGraph();

    /**返回图中的顶点数*/
    public int getVexNum();

    /**返回图中的边数*/
    public int getArcNum();

    /**
    * 给定点的位置v，返回其对应的顶点值，其中， 0 <=  v  < vexNum
     * @param v
    * */
    public Object getVex(int v) throws Exception;

    /**
    * 给定顶点的值vex，返回其在图中的位置，如果图中不包含此定点，返回-1
     * @param vex
    */
    public int locateVex(Object vex);

    /**
    * 返回v的第一个邻接点，若V没有邻接点，则返回-1，其中， 0 <=  v  < vexNum
     * @param v
    */
    public int firstAdjVex(int v) throws Exception;

    /**
    * 返回v相对于w的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中， 0 <= v,  w  < vexNum
     * @param v
     * @param w
    */
    public int nextAdjVex(int v,int w) throws Exception;
}
