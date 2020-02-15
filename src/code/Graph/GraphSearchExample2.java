package code.Graph;
/**
 * 图的遍历方法应用举例2
 *
 * 题目：
 *      编程实现判断一个有向图中任意给定的两个顶点之间是否存在一条长度为k的简单路径
 *
 *      简单路径：指图G(V，E)中路径上的顶点都不相同的路径
 * */
public class GraphSearchExample2 {
    private boolean [] visited; //访问标志数组

    private int i = 0;  //辅助变量，在遍历过程中用于记录从起点出发的路径长度

    private boolean find = false;   //标志着是否已经找到了指定长度的路径

    /**
     * @param G 图
     * @param v 第一个任意顶点
     * @param u 第二个任意顶点
     * @param k 长度
     * */
    public void findPath(IGraph G, int u, int v, int k)throws Exception{
        visited = new boolean[G.getVexNum()];
        for (int w = 0;w<G.getVexNum();w++){
            //访问数组初始化
            visited[w] = false;
        }
        findDFS(G,u,v,k);
        if (find){
            System.out.println(G.getVex(u)+"和"+G.getVex(v)+"之间存在一条长度为"+k+"的简单路径");
        }else {
            System.out.println(G.getVex(u)+"和"+G.getVex(v)+"之间不存在一条长度为"+k+"的简单路径");
        }

    }

    /**
     * @param G 图
     * @param v 第一个任意顶点
     * @param u 第二个任意顶点
     * @param k 长度
     * */
    public void findDFS(IGraph G, int u, int v, int k)throws Exception{
        if (i == k && u == v){
            find = true;
        }else if (!find){   //如果还没有找到
            visited[u] = true;
            for (int w = G.firstAdjVex(u);w >= 0;w = G.nextAdjVex(u,w)){
                if (!visited[w]){
                    if (i<k){
                        ++i;
                        //对v的尚未访问的邻接顶点w递归调用findDFS(G,u,v,k);
                        findDFS(G,w,v,k);
                    }else{
                      //若路径长度已经达到k值而仍未找到简单路径，则不再继续对当前顶点进行深度优先搜索
                        break;
                    }
                }

            }
            --i;//回退一个顶点
        }
    }

    public static ALGraph getGraphSearch_G(){
        /*
         * 构建一个有向图
         * 其中：
         *
         *       A--->B--->C--->D--->E-->F--->A
         *
         *       F--->B--->E
         *
         * */

        ArcNode ab = new ArcNode(1);
        VNode A = new VNode("A",ab);

        ArcNode bc = new ArcNode(2);
        ArcNode be = new ArcNode(4,0,bc);
        VNode B = new VNode("B",be);

        ArcNode cd = new ArcNode(3);
        VNode C = new VNode("C",cd);

        ArcNode de = new ArcNode(4);
        VNode D = new VNode("D",de);

        ArcNode ef = new ArcNode(5);
        VNode E = new VNode("E",ef);

        ArcNode fa = new ArcNode(0);
        ArcNode fb = new ArcNode(1,0,fa);
        VNode F = new VNode("F",fb);

        VNode [] vexs = {A,B,C,D,E,F};
        return new ALGraph(GraphKind.DG,6,8,vexs);
    }

    public static void main(String [] args)throws Exception{
        GraphSearchExample2 test = new GraphSearchExample2();
        test.findPath(getGraphSearch_G(),0,5,3);

    }

}
