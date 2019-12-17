package code.tree;
/*
* 构造哈夫曼树和哈夫曼编码的类描述如下
* */
public class HuffmanTree {

    //求哈夫曼编码的算法,W存放n个字符的权值（均 > 0）
    public int [] [] huffmanCoding(int [] W){
        int n = W.length;       //字符个数
        int m = 2*n - 1;        //哈夫曼树的节点数
        HuffmanNode [] HN = new HuffmanNode[m];     //定义一个霍夫曼节点的数组
        int i;
        for (i = 0;i < n;i++){
            HN[i] = new HuffmanNode(W[i]);      //构造n个具有权值的节点，因为并不是每一个节点都是有权值的，只有在边上的才有
        }

        //开始建立哈夫曼树
        for (i = n;i < m;i++){      //已经有了n个节点,还需要构建 m-n 个出来
            //在 HN[0..i-1] 选择不在哈夫曼树中且weight最小的两个节点 min1 和 min2
            HuffmanNode min1 = selectMin(HN,i-1);
            min1.flag = 1;
            HuffmanNode min2 = selectMin(HN,i-1);
            min2.flag = 1;

            //构造min1和min2的父节点，并修改其父节点的权值
            HN[i] = new HuffmanNode();
            min1.parent = HN[i];
            min2.parent = HN[i];
            HN[i].lChild = min1;
            HN[i].rChild = min2;
            HN[i].weight = min1.weight+min2.weight;
        }

        //从叶子到根逆向求每个字符的哈夫曼码
        int[][] HuffCode = new int[n][n];       //分配n个字符编码存储空间
        for (int j = 0;j < n;j++){
            int start = n-1;        //编码的开始位置，初始化为数组的结尾
            for (HuffmanNode c = HN[j],p = c.parent;p!=null;c = p,p = p.parent){
                //从叶子到根逆向求编码
                if (p.lChild.equals(c)){
                    //左孩子编码为0
                    HuffCode[j][start--] = 0;
                }else {
                    HuffCode[j][start--] = 1;
                }
            }
            HuffCode[j][start] = -1;    //编码的开始标志为-1,编码是-1之后的0，1序列
        }

        return HuffCode;
    }

    //在 HN[0..i-1] 选择不在哈夫曼树中且weight最小的节点
    private HuffmanNode selectMin(HuffmanNode[] HN,int end){
        HuffmanNode min = HN[end];
        for (int i = 0;i <= end;i++){
            HuffmanNode h = HN[i];
            if (h.flag == 0&& h.weight < min.weight){       //如果不在哈夫曼树中且weight最小的节点
                min = h;
            }
        }
        return min;
    }


    //测试代码
    public static void main(String [] args){
        int [] W = {23,11,5,3,29,14,7,8};       //初始化权值
        HuffmanTree T = new HuffmanTree();      //构造哈夫曼树
        int [][]HN = T.huffmanCoding(W);        //求哈夫曼编码
        System.out.println("哈夫曼编码为：");
        for (int i = 0;i<HN.length;i++){
            System.out.print(W[i]+" ");
            for (int j = 0;j<HN[i].length;j++){
                if (HN[i][j] == -1){
                    //开始标识符读到数组结尾
                    for (int k = j+1;k<HN[i].length;k++){
                        System.out.print(HN[i][k]);//输出
                    }
                    break;
                }
            }
            System.out.println();//换行
        }
    }
}
