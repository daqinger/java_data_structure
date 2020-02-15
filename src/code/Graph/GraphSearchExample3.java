package code.Graph;

import code.Stack.LinkStack;

/**
 * 图的遍历方法应用举例3
 *
 * 题目：
 *      编程实现应用深度优先搜索策略判断一个有向图是否存在环
 * */
public class GraphSearchExample3 {
    private boolean [] visited; //访问标志数组

    //按照深度优先搜索访问的先后顺序记录在一个连通分支当中的顶点元素
    private LinkStack S = new LinkStack();

    private boolean find = false;   //记录是否已经找到了环

    public void findCircle(IGraph G)throws Exception{
        visited = new boolean[G.getVexNum()];
        for (int w = 0;w<G.getVexNum();w++){
            //访问数组初始化
            visited[w] = false;
        }
        for (int v = 0;v<G.getVexNum();v++){
            if (!visited[v]){   //对尚未访问的顶点调用DFS
                findDFS(G,v);
            }
        }

        if (find){
            System.out.println("此图存在环");
        }else {
            System.out.println("此图不存在环！");
        }
    }

    public void findDFS(IGraph G,int v)throws Exception{
        if (!find){
            visited[v] = true;
            S.push(v);

            for (int w = G.firstAdjVex(v);w>=0;w = G.nextAdjVex(v,w)){
                if (visited[w]&&isDuplicate(w)){
                    find = true;
                }else {
                    //对v尚未访问的邻接顶点w递归调用DFS
                    findDFS(G,w);
                }
            }
            S.pop();
        }
    }

    /**
     * 判断栈S中是否存在值为w的数据元素
     * */
    private boolean isDuplicate(Integer w)throws Exception{
        LinkStack S1 = new LinkStack(); //辅助栈
        while (!S.isEmpty()&&!((Integer)S.peek()).equals(w)){
            //利用辅助栈S1记录出栈的数据元素
            S1.push(S.pop());
        }
        if (S.isEmpty()){   //重新把数据元素放入栈S中
            while (!S1.isEmpty()){
                S.push(S1.pop());
            }
            return false;
        }else {
            return true;
        }
    }

    public static void main(String [] args)throws Exception{
        GraphSearchExample3 test = new GraphSearchExample3();
        test.findCircle(GraphSearchExample2.getGraphSearch_G());
    }

}
