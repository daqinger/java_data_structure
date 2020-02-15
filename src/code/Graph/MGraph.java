package code.Graph;

import java.util.Scanner;

/**
 * @author 大青儿学习笔记
 *
 * 使用邻接矩阵表示图
 *
 * 注意点：
 *      1.无向图的邻接矩阵是对称阵，一般采用压缩存储
 *
 *      2.用邻接矩阵存储图，所需要的存储空间只和定点数有关
 *
 *      3.优点：容易判断任意俩顶点是否有边，容易求出各个顶点上的度
 *
 *      4.对于无向图，邻接矩阵第i行或i列非0元素个数正好是第i个顶点vi的度
 *        对于有向图，第i行非0元素个数为i个顶点的出度,i列为入度
 *
 *      0.图的邻接矩阵----(Adjacency Matrix)是用来表示顶点之间相邻关系的矩阵
 *        图的邻接矩阵是一个n阶矩阵，n为顶点个数
 * */
public class MGraph implements IGraph{

    public final static int INFINITY = Integer.MAX_VALUE;//这里表示网络不连通，他们之间的权值也是无穷大

    private GraphKind kind;     //图的种类标志

    private int vexNum,arcNum;  //图的当前顶点数和边数

    private Object [] vexs;     //顶点数组

    private int [][]arcs;       //邻接矩阵

    public MGraph(){
        this(null,0,0,null,null);
    }

    public MGraph(GraphKind kind, int vexNum, int arcNum, Object[] vexs, int[][] arcs) {
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
        this.arcs = arcs;
    }

    /**
     * 创建一个图
     */
    @Override
    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入图的类型：");
        GraphKind kind = GraphKind.valueOf(sc.next());
        switch (kind){
            case UDG:
                createUDG();
                return;
            case DG:
                createDG();
                return;
            case UDN:
                createUDN();
                return;
            case DN:
                createDN();
                return;
        }
    }

    //创建 图，网 的工具方法
    private void createNetworkOrGraphUnit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数，图的边数");
        vexNum = sc.nextInt();//图的顶点数
        arcNum = sc.nextInt();//图的边数

        vexs = new Object[vexNum];//存储顶点的数组

        System.out.println("请分别输入图的各个顶点：");
        for (int v = 0;v < vexNum;v++){//构造顶点向量
            vexs[v] = sc.next();
        }

        arcs = new int[vexNum][vexNum];//创建邻接矩阵
        //初始化邻接矩阵
        for (int v = 0;v < vexNum;v++){
            for (int u = 0;u < vexNum;u++){
                arcs[v][u] = INFINITY;//设初始的全部的权值为最大
            }
        }
    }

    /*
    * 创建无向图
    *   书上的方法
    *
    *   我本人更倾向于传参式的方法
    * */
    private void createUDG(){
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各个边的两个顶点及其权值:");
        for(int k = 0; k <arcNum;k++){//循环 图的边数 次
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u] = arcs[u][v] = 1;
        }

    }

    /*
     * 创建有向图
     *   书上的方法
     *
     *   我本人更倾向于传参式的方法
     * */
    private void createDG(){
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各个边的两个顶点及其权值:");
        for(int k = 0; k <arcNum;k++){//循环 图的边数 次
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u]= 1;
        }
    }

    /*
    * 创建无向网
    *   书上的方法
    *
    *   我本人更倾向于传参式的方法
    * */
    private void createUDN(){
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各个边的两个顶点及其权值:");
        for(int k = 0; k <arcNum;k++){//循环 图的边数 次
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u] = arcs[u][v] = sc.nextInt();
        }

    }

    /*
     * 创建有向网
     *   书上的方法
     *
     *   我本人更倾向于传参式的方法
     * */
    private void createDN(){
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各个边的两个顶点及其权值:");
        for(int k = 0; k <arcNum;k++){//循环 图的边数 次
            int v = locateVex(sc.next());
            int u = locateVex(sc.next());
            arcs[v][u]= sc.nextInt();
        }
    }

    /**
     * 返回图中的顶点数
     */
    @Override
    public int getVexNum() {
        return vexNum;
    }

    /**
     * 返回图中的边数
     */
    @Override
    public int getArcNum() {
        return arcNum;
    }

    /**
     * 给定点的位置v，返回其对应的顶点值，其中， 0 <=  v  < vexNum
     *
     * @param v
     */
    @Override
    public Object getVex(int v) throws Exception {
        if (v < 0 && v >= vexNum){
            throw new Exception("第"+v+"个顶点不存在");
        }
        return vexs[v];
    }

    /**
     * 给定顶点的值vex，返回其在图中的位置，如果图中不包含此定点，返回-1
     *
     * @param vex
     */
    @Override
    public int locateVex(Object vex) {
        for (int v = 0;v < vexNum;v++){
            if (vexs[v].equals(vex)){
                return v;
            }
        }

        return -1;
    }

    /**
     * 返回v的第一个邻接点，若V没有邻接点，则返回-1，其中， 0 <=  v  < vexNum
     *
     * @param v
     */
    @Override
    public int firstAdjVex(int v) throws Exception {
        if (v < 0 && v >= vexNum){
            throw new Exception("第" + v + "个顶点不存在！");
        }
        for (int j = 0;j<vexNum;j++){
            if (arcs[v][j] != 0 && arcs[v][j] < INFINITY){
                return j;
            }
        }
        return -1;
    }

    /**
     * 返回v相对于w的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中， 0 <= v,  w  < vexNum
     *
     * @param v
     * @param w
     */
    @Override
    public int nextAdjVex(int v, int w) throws Exception {
        if (v < 0 && v >= vexNum){
            throw new Exception("第" + v + "个顶点不存在！");
        }
        if (w >= vexNum){
            throw new Exception("相对顶点不存在！");
        }

        for (int j = w + 1;j < vexNum;j++){
            if (arcs[v][j] != 0 && arcs[v][j] <INFINITY){
                return  j;
            }
        }

        return -1;
    }

    public GraphKind getKind() {
        return kind;
    }

    public Object[] getVexs() {
        return vexs;
    }

    public int[][] getArcs() {
        return arcs;
    }
}
