package code.Graph;

import code.Queue.LinkQueue;

/**
 * 图的遍历方法应用举例1
 *
 * 题目：
 *      编程实现应用广度优先搜索算法确定无向图的连通分量（即：有几个互不相连的部分）
 * */
public class GraphSearchExample1 {
    public final static int INFINITY = Integer.MAX_VALUE;

    public static void CC_BFS(IGraph G)throws Exception{
        boolean [] visited = new boolean[G.getVexNum()];//实例化访问标志数组

        for (int v = 0;v < G.getVexNum();v++){//循环 顶点次数 次
            //将访问标志数组初始化
            visited[v] = false;
        }

        LinkQueue Q = new LinkQueue();//辅助队列Q
        LinkQueue P = new LinkQueue();//辅助队列P,用于记录连通分量的顶点
        int i = 0;  //用于记录连通分量的个数
        for (int v = 0;v < G.getVexNum();v++){//循环顶点数次
            P.clear();  //队列清空
            if (!visited[v]){   //如果某个顶点没有被访问到
                visited[v] = true;  //设置为已经访问
                P.offer(G.getVex(v));   //将图的第v个节点入队
                Q.offer(v);     //v入队列

                while (!Q.isEmpty()){
                    int u = (Integer)Q.poll();//队首元素出队列并赋值给u

                    for (int w = G.firstAdjVex(u);w >= 0;w = G.nextAdjVex(u,w)){
                        if (!visited[w]){   //w为u的尚未访问的邻接顶点
                            visited[w] = true;

                            P.offer(G.getVex(w));

                            Q.offer(w);
                        }
                    }
                }

                System.out.println("图的第"+ ++i + "个连通分量是：");
                while (!P.isEmpty()){
                    System.out.print(P.poll().toString()+" ");
                }
                System.out.println();
            }
        }


    }

    public static void main(String [] args)throws Exception{

        /**
         * 定义一个图，大约长这样：
         *              A
         *            |  \            E
         *           B    \          |
         *           \    D          F
         *            \  |          |
         *              C          G
         *
         * */

        Object vexs[] = {"A","B","C","D","E","F","G"};
        int [][] arcs = {
                {0, 1, INFINITY, 1, INFINITY, INFINITY, INFINITY},
                {1, 0, 1, INFINITY, INFINITY, INFINITY, INFINITY},
                {INFINITY, 1, 0, 1, INFINITY, INFINITY, INFINITY},
                {1, INFINITY, 1, 0, INFINITY, INFINITY, INFINITY},
                {INFINITY, INFINITY, INFINITY, INFINITY, 0, 1, INFINITY},
                {INFINITY, INFINITY, INFINITY, INFINITY, 1, 0, 1},
                {INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1, 0}
        };

        MGraph G = new MGraph(GraphKind.UDG,7,6,vexs,arcs);
        CC_BFS(G);
        /**
         * 输出：
         *    图的第1个连通分量是：
         *    A B D C
         *    图的第2个连通分量是：
         *    E F G
         * */
    }

}
