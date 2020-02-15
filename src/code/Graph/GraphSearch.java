package code.Graph;

import code.Queue.LinkQueue;

/**
 * 图的搜索算法，包括广度优先搜索算法和深度优先搜索算法
 * */
public class GraphSearch {
    private static boolean [] visited;//访问标志数组

    /**
     * 广度优先遍历----(Breadth First Search)
     * */
    public static void BFSTraverse(IGraph G)throws Exception{
        visited = new boolean[G.getVexNum()];//实例化访问标志数组

        for (int v = 0;v < G.getVexNum();v++){//循环 顶点次数 次
            //将访问标志数组初始化
            visited[v] = false;
        }

        for (int v = 0;v < G.getVexNum();v++){
            if (!visited[v]){   //顶点尚未被访问
                BFS(G,v);
            }
        }

    }

    private static void BFS(IGraph G,int v)throws Exception{
        visited[v] = true;
        System.out.print(G.getVex(v).toString()+", ");
        LinkQueue Q = new LinkQueue();//辅助队列Q
        Q.offer(v);

        while (!Q.isEmpty()){
            int u = (Integer)Q.poll();//队首元素出队列并赋值给u

            for (int w = G.firstAdjVex(u);w >= 0;w = G.nextAdjVex(u,w)){
                if (!visited[w]){   //w为u的尚未访问的邻接顶点
                    visited[w] = true;
                    System.out.print(G.getVex(w).toString()+", ");
                    Q.offer(w);
                }
            }
        }
    }

    /**
     * 深度优先遍历----(Depth First Search)
     * */
    public static void DFSTraverse(IGraph G) throws Exception{
        visited = new boolean[G.getVexNum()];//实例化访问标志数组

        for (int v = 0;v < G.getVexNum();v++){//循环 顶点次数 次
            //将访问标志数组初始化
            visited[v] = false;
        }

        for (int v = 0;v < G.getVexNum();v++){
            if (!visited[v]){   //顶点尚未被访问
                DFS(G,v);
            }
        }
    }

    public static void DFS(IGraph G,int v)throws Exception{
        //从第v个顶点出发递归地深度优先遍历图G
        visited[v] = true;
        System.out.print(G.getVex(v).toString()+", ");//访问第v个顶点

        for (int w = G.firstAdjVex(v);w >= 0;w = G.nextAdjVex(v,w)){
            if (!visited[w]){
                DFS(G,w);   //对v的尚未访问的邻接顶点w递归调用DFS
            }
        }
    }

}
