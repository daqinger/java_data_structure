package code.Graph;

/**
 * 使用普里姆算法构造最小生成树的类描述
 *
 * 书上的解释：
 *      普里姆算法的基本思想：
 *
 *      假设 G = (V, E)是一个具有n个顶点的连通网， T = (V, TE)是网的最小生成树。
 *      其中，V是T的顶点集，TE是T的边集, 则最小生成树的构造步骤为：从 U = {u_0},
 *      TE = null开始，必然存在一条边(u*, v*),u*∈U,v*∈V-U，使得u*与v*之间距离=U与V-U之间距离,
 *      将(u*,v*)加入集合TE中，同时将顶点v*加入顶点集U中，直到U = v为止，此时TE中必有n-1条边，
 *      最小生成树T构造完毕
 *
 * 我的极简理解：
 *      普里姆算法的基本思想：
 *
 *      将图的所有顶点分为两类，一类是已经是树的一部分，一类还未成为树的一部分，
 *      每一次从未成为树的一部分挑一个离树最近的加入树，以此往复
 *
 * 问题的难点：
 *      计算从集合 U 到集合 V-U 的最短距离
 *
 *      时间复杂度：O(n*n)
 *
 *
 * 克鲁斯卡尔求最小生成树(未实现)
 *      基本思想：
 *
 *       根据边的权值递增的方式，依此找出权值最小的边建立的最小生成树
 *       并且规定每次新增的边不能造成生成树有回路，直到找到n-1条边为止
 * */
public class MiniSpanTreePRIM {
    /**
     * 内部类，辅助记录从顶点集 U 到 V-U 的代价最小的边
     *
     * */
    private class CloseEdge{
        Object adjVex;  //该边依附在U的顶点
        int lowCost;    //此边的权，该顶点到U的距离

        public CloseEdge(Object adjVex,int lowCost){
            this.adjVex = adjVex;
            this.lowCost = lowCost;
        }

    }


    //用普里姆算法从第u个顶点出发构造网G的最小生成树T,返回由生成树边组成的二维数组
    public Object [][] PRIM(MGraph G,Object u)throws Exception{
        //tree是一个二维数组，第一个维度存边，共有n-1条边，n为顶点个数，第二个维度存边的起点和终点
        Object [] [] tree = new Object[G.getVexNum()-1][2];
        int count = 0;//tree的第一个维度

        //辅助数组（有顶点个数个）
        CloseEdge [] closeEdge = new CloseEdge[G.getVexNum()];
        int k = G.locateVex(u); //返回顶点 u 的编号

        for (int j = 0;j < G.getVexNum();j++){    //辅助数组初始化
            if (j != k){    //如果数组循环的不是起始顶点
                closeEdge[j] = new CloseEdge(u,G.getArcs()[k][j]);  //获得权
            }
        }

        //然后处理初始点，初始点距离U距离为0
        closeEdge[k] = new CloseEdge(u,0);  //初始状态，U = {u}

        for (int i = 1;i < G.getVexNum();i++){    //选择其余G.vexNum - 1个顶点
            k = getMinMUm(closeEdge);   //求出 T 的下一个点：第 K 个顶点

            tree[count][0] = closeEdge[k].adjVex;//生成树的边放入数组中
            tree[count][1] = G.getVexs()[k];
            count++;
            closeEdge[k].lowCost = 0;   //第k个顶点并入U集
            for (int j = 0;j<G.getVexNum();j++){    //新顶点并入U后重新选择最小边
                if (G.getArcs()[k][j] < closeEdge[j].lowCost){
                    closeEdge[j] = new CloseEdge(G.getVex(k),G.getArcs()[k][j]);
                }
            }

        }

        return tree;
    }

    //在closeEdge中选出lowCost最小且不为0的顶点
    private int getMinMUm(CloseEdge[] closeEdge){
        int min = Integer.MAX_VALUE;
        int v = -1;
        for (int i = 0;i<closeEdge.length;i++){
            if (closeEdge[i].lowCost != 0 && closeEdge[i].lowCost<min){
                min = closeEdge[i].lowCost;
                v = i;
            }
        }

        return v;
    }


    /**
     * 生成一张图类似于：
     *      A---7---B---3---E---7---F---2---D---5---A
     *
     *      A---1---C
     *
     *      B---6---C
     *
     *      E---6---C
     *
     *      F---4---C
     *
     *      D---7---C
     *
     */
    public static MGraph getMiniSpanTree_G()throws Exception{
        int INFINITY = Integer.MAX_VALUE;
        Object vexs[] = {"A","B","C","D","E","F"};
        int [][] arcs = {
                {INFINITY,7,1,5,INFINITY,INFINITY},
                {7,INFINITY,6,INFINITY,3,INFINITY},
                {1,6,INFINITY,7,6,4},
                {5,INFINITY,7,INFINITY,INFINITY,2},
                {INFINITY,3,6,INFINITY,INFINITY,7},
                {INFINITY,INFINITY,4,2,7,INFINITY}
        };

        return new MGraph(GraphKind.UDG,6,10,vexs,arcs);
    }

    public static void main(String [] args)throws Exception{
        Object [][] tree = new MiniSpanTreePRIM().PRIM(MiniSpanTreePRIM.getMiniSpanTree_G(),"A");

        for (int i = 0;i<tree.length;i++){
            System.out.println(tree[i][0]+"---"+tree[i][1]);
        }

    }


}
