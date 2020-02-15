package code.Graph;

import java.util.Scanner;

public class ALGraph implements IGraph{

    private GraphKind kind;//图的种类标志

    private int vexNum,arcNum;//图的当前顶点数和边数

    private VNode[] vexs;//顶点

    public ALGraph() {
        this(null,0,0,null);
    }

    public ALGraph(GraphKind kind, int vexNum, int arcNum, VNode[] vexs) {
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
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

        vexs = new VNode[vexNum];//存储顶点的数组

        System.out.println("请分别输入图的各个顶点：");
        for (int v = 0;v < vexNum;v++){//构造顶点向量
            vexs[v] = new VNode(sc.next());
        }

    }

    //有向图
    private void createDG() {
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各边的顶点及其权值：");

        for (int k = 0;k < arcNum;k++){
            int v = locateVex(sc.next());//弧头
            int u = locateVex(sc.next());//弧尾
            addArc(v,u,1);
        }

    }

    //无向图
    private void createUDG() {
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各边的顶点：");

        for (int k = 0;k < arcNum;k++){
            int v = locateVex(sc.next());//弧头
            int u = locateVex(sc.next());//弧尾
            int value = 1;
            addArc(v,u,value);
            addArc(u,v,value);
        }

    }

    //无向网
    private void createUDN(){
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各边的顶点及其权值：");

        for (int k = 0;k < arcNum;k++){
            int v = locateVex(sc.next());//弧头
            int u = locateVex(sc.next());//弧尾
            int value = sc.nextInt();
            addArc(v,u,value);
            addArc(u,v,value);
        }
    }

    //有向网
    private void createDN(){
        Scanner sc = new Scanner(System.in);
        createNetworkOrGraphUnit();
        System.out.println("请输入各边的顶点及其权值：");

        for (int k = 0;k < arcNum;k++){
            int v = locateVex(sc.next());//弧头
            int u = locateVex(sc.next());//弧尾
            int value = sc.nextInt();
            addArc(v,u,value);
        }

    }

    /**
     * 在位置为v,u的顶点之间添加一条弧，其权值为value
     * */
    public void addArc(int v,int u,int value){
        ArcNode arc = new ArcNode(u,value);
        arc.setNextArc(vexs[v].firstArc);
        vexs[v].firstArc = arc;
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
            throw new Exception("第" + v + "个顶点不存在！");
        }
        return vexs[v].getData();
    }

    /**
     * 给定顶点的值vex，返回其在图中的位置，如果图中不包含此定点，返回-1
     *
     * @param vex
     */
    @Override
    public int locateVex(Object vex) {
        for (int v = 0;v<vexNum;v++){
            if (vexs[v].data.equals(vex)){
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
        VNode vex = vexs[v];
        if (vex.firstArc != null){
            return vex.firstArc.adjVex;
        }else {
            return -1;
        }
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
        VNode vex = vexs[v];
        ArcNode arcvw = null;
        for (ArcNode arc = vex.firstArc;arc != null;arc = arc.getNextArc()){
            if (arc.adjVex == w){
                arcvw = arc;
                break;
            }
        }
        if (arcvw != null && arcvw.getNextArc() != null){
            return arcvw.getNextArc().adjVex;
        }else {
                return -1;
            }

    }

    public GraphKind getKind() {
        return kind;
    }

    public VNode[] getVexs() {
        return vexs;
    }
}
