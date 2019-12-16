package code.tree;

import code.Queue.LinkQueue;
import code.Stack.LinkStack;

/*
* 基于二叉链式存储结构的二叉树类的描述
* */
public class BiTree {
    private BiTreeNode root;//根节点
    public BiTree() {
        this.root = null;
    }
    public BiTree(BiTreeNode root) {
        this.root = root;
    }

    public BiTreeNode getRoot() {
        return root;
    }

    public void setRoot(BiTreeNode root) {
        this.root = root;
    }

    //由标明空子树的先根遍历序列建立一颗二叉树的操作算法
    private int index = 0;//用于记录perStr的索引值
    public BiTree(String preStr){
        char c = preStr.charAt(index++);//取出字符串索引为index 的字符，且index增1
        if (c != '#'){
            root = new BiTreeNode(c);
            root.lChild = new BiTree(preStr).root;//建立左子树
            root.rChild = new BiTree(preStr).root;//建立右子树

        }else {
            root = null;
        }
    }

    //由先根遍历和中根遍历序列创建一颗二叉树算法
    public BiTree(String preOrder,String inOrder,int preIndex,int inIndex,int count) {
        if(count>0) {
            char r = preOrder.charAt(preIndex) ;
            int i = 0;
            for(;i<count;i++)
                if( r == inOrder.charAt(i+inIndex))
                    break;
            root = new BiTreeNode(r);
            root.lChild = new BiTree(preOrder,inOrder,preIndex+1,inIndex,i).root;
            root.rChild = new BiTree(preOrder,inOrder,preIndex+i+1,inIndex+i+1,count - i - 1).root;
        }

    }
    //前序递归遍历算法
    public void preRootTraverse(BiTreeNode T) {
        if (T != null) {
            System.out.print(T.data);
            preRootTraverse(T.lChild);
            preRootTraverse(T.rChild);
        }
    }

    //前序遍历非递归算法
    public void preRootTraverse() throws Exception {
        BiTreeNode T = root;
        if (T != null){
            LinkStack s = new LinkStack();//构建栈
            s.push(T);//根节点入栈
            while (!s.isEmpty()){//如果栈不是空的
                T = (BiTreeNode)s.pop();    //移除栈顶结点，并返回其值
                System.out.println(T.data);//访问节点
                while (T != null){
                    if (T.lChild != null)//访问左孩子
                        System.out.println(T.lChild.data);
                    if (T.rChild != null)
                        s.push(T.rChild);//右孩子非空入栈
                    T = T.lChild;
                }
            }
        }

    }

    //中序递归遍历算法
    public void inRootTraverse(BiTreeNode T) {
        if (T != null) {
            preRootTraverse(T.lChild);
            System.out.print(T.data);
            preRootTraverse(T.rChild);
        }
    }

    //中序遍历操作实现的非递归算法
    public void inRootTraverse() throws Exception {
        BiTreeNode T = root;
        if (T != null){
            LinkStack s = new LinkStack();//构造链栈
            s.push(T);//根节点入栈
            while ((!s.isEmpty())){
                while (s.peek() != null)
                    //将栈顶节点的左孩子节点相继入栈
                    s.push(((BiTreeNode)s.peek()).lChild);
                s.pop();//空节点退栈
                if (!s.isEmpty()){
                    T = (BiTreeNode)s.pop();//移除栈顶节点，并返回其值
                    System.out.println(T.data);//访问节点
                    s.push(T.rChild);//节点的右孩子入栈
                }
            }
        }
    }

    //后序递归遍历算法
    public void postRootTraverse(BiTreeNode T) {
        if (T != null) {
            preRootTraverse(T.lChild);
            preRootTraverse(T.rChild);
            System.out.print(T.data);
        }
    }

    //后序遍历操作实现的非递归算法
    public void postRootTraverse() throws Exception {
        BiTreeNode T = root;
        if (T != null){
            LinkStack S = new LinkStack();//构造链栈
            S.push(T);//根节点入栈
            Boolean flag;//访问标记
            BiTreeNode p = null;//p指向刚被访问的节点
            while (!S.isEmpty()){
                while (S.peek() != null)
                    //将栈顶节点的左孩子相继入栈
                    S.push(((BiTreeNode)S.peek()).lChild);
                S.pop();//空节点退栈
                while (!S.isEmpty()){
                    T = (BiTreeNode)S.peek();//查看栈顶元素
                    if (T.rChild == null||T.rChild == p){
                        System.out.println(T.data);//访问节点
                        S.pop();//移除栈顶元素
                        p = T;//p指向刚被访问的节点
                        flag = true;//设置访问标记
                    }else {
                        S.push(T.rChild);//右孩子节点入栈
                        flag = false;//设置未被访问标记
                    }
                    if (!flag)
                        break;
                }
            }
        }
    }

    //层次遍历操作实现的非递归算法
    public void levelTraverse() throws Exception {
        BiTreeNode T = root;
        if (T != null){
            LinkQueue L = new LinkQueue();//构造队列
            L.offer(T);
            while (!L.isEmpty()){
                T = (BiTreeNode)L.poll();
                System.out.println(T.data);//访问节点
                if (T.lChild != null)
                    L.offer(T.lChild);//左孩子节点非空，入队列
                if (T.rChild != null)
                    L.offer(T.rChild);//右孩子节点非空，入队列
            }
        }
    }

    //二叉树上的查找算法
    public BiTreeNode searchNode(BiTreeNode T,Object x){
        if (T != null){
            if (T.data.equals(x))
                return T;//如果是根节点
            else {
                BiTreeNode lResult = searchNode(T.lChild,x);//查找左子树
                //若在左子树中查找到值为x的节点，则返回该节点，否在则，在右子树中查找到该节点并返回结果
                return  lResult != null?lResult:searchNode(T.rChild,x);
            }
        }
        return null;
    }


    //判断两颗二叉树是否相等的算法
    public boolean isEqual(BiTreeNode T1,BiTreeNode T2){
        if (T1 == null&&T2 == null){
            return true;
        }
        if (T1 != null&& T2 != null){
            if (T1.data.equals(T2.data))
                if (isEqual(T1.lChild,T2.lChild))
                    if (isEqual(T1.rChild,T2.rChild))
                        return true;
        }
        return false;
    }

    //求二叉树深度的算法
    public int getDepth(BiTreeNode T) {
        if(T != null) {
            int lDepth = getDepth(T.lChild);
            int rDepth = getDepth(T.rChild);
            return 1 + (lDepth>rDepth?lDepth : rDepth) ;
        }
        return 0;
    }

    //求二叉树叶子节点个数的算法
    public int countLeafNode (BiTreeNode T) {
        int count = 0;
        if(T!=null) {
            if(T.lChild == null&&T.rChild == null) {
                ++count;
            }
            else {
                count += countLeafNode(T.lChild);
                count +=countLeafNode(T.rChild);
            }
        }
        return count;
    }

    //求二叉树节点个数算法(递归算法)
    public int countNode(BiTreeNode T){
        if (T == null)
            return 0;
        else
            return countLeafNode(T.lChild)+countNode(T.rChild)+1;
    }
}
