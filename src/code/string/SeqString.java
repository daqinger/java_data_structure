package code.string;
/*
* 串的顺序存储结构
* */
public class SeqString implements IString{

    private char [] strValue;//字符数组，存放串值
    private int curLen;//串中字符个数，即串的长度

    //构造方法
    public SeqString(){
        strValue = new char[0];
        curLen = 0;
    }

    //以一段字符串构造
    public SeqString(String str){
        char [] tempCharArray = str.toCharArray();
        strValue = tempCharArray;
        curLen = tempCharArray.length;
    }

    //以字符数组构造串对象
    public SeqString(char [] value){
        this.strValue = new char[value.length];
        for (int i = 0;i<value.length;i++){
            this.strValue[i] = value[i];
        }
        curLen = value.length;
    }

    //扩充字符串存储空间，参数指定容量,后面的插入字符串需要调用
    public void allocate(int newCapacity){
        char [] temp = strValue;
        strValue = new char[newCapacity];
        //复制数组
        for (int i = 0;i<temp.length;i++){
            strValue[i] = temp[i];
        }
    }

    @Override
    public void clear() {
        this.curLen = 0;
    }

    @Override
    public boolean isEmpty() {
        return curLen == 0;
    }

    @Override
    public int length() {
        return curLen;
    }

    //返回字符串中序号为index的字符
    @Override
    public char charAt(int index) {
        if ((index < 0)|| (index >= curLen)){
            throw new StringIndexOutOfBoundsException(index);
        }
        return strValue[index];
    }

    //截取字符串，返回当前串从 begin 到 end-1的值
    @Override
    public IString substring(int begin, int end) {
        if (begin < 0){
            throw new StringIndexOutOfBoundsException("起始位置不能小于0");
        }
        if (end > curLen){
            throw new StringIndexOutOfBoundsException("结束位置不能大于串的当前长度:"+curLen);
        }
        if (begin >= end){
            throw new StringIndexOutOfBoundsException("开始位置不能大于等于结束位置");
        }
        //截取全部字符串
        if (begin == 0 && end == curLen){
            return this;
        } else {
            char [] buff = new char[end - begin];
            for (int i = 0;i<buff.length;i++){
                buff[i] = this.strValue[i+begin];
            }
            return new SeqString(buff);
        }
    }

    //插入字符串你，在第offset之前插入串str，offset有效值 0 <= offset <= curLen
    @Override
    public IString insert(int offset, IString str) {
        if ((offset < 0)||(offset > this.curLen)){
            throw new StringIndexOutOfBoundsException("插入位置不合法");
        }
        int len = str.length();
        int newCount = this.curLen+len;
        if (newCount > strValue.length) {//如果添加新的字符串进去容量不够
            allocate(newCount);
        }
        for (int i = this.curLen - 1;i >= offset;i--){
            strValue[len+i] = strValue[i];//从offset开始向后移动len个字符
        }
        for (int i = 0;i < len;i++){
            strValue[offset+i] = str.charAt(i);
        }
        this.curLen = newCount;
        return this;
    }

    //删除，从begin 到 end-1
    @Override
    public IString delete(int begin, int end) {
        if (begin < 0){
            throw new StringIndexOutOfBoundsException("起始位置不能小于0");
        }
        if (end > curLen){
            throw new StringIndexOutOfBoundsException("结束位置不能大于串当前长度"+curLen);
        }
        if (begin >= end){
            throw new StringIndexOutOfBoundsException("开始位置不能大于等于结束位置");
        }
        //从end开始的串向前移动到从begin开始的位置
        for (int i = 0;i < curLen-end;i++){
            strValue[begin+i] = strValue[end+i];
        }
        curLen = curLen-(end - begin);
        return this;
    }

    //连接操作，把str串连接到当前串的后面
    @Override
    public IString concat(IString str) {
        return insert(curLen,str);
    }

    //将当前串与目标串进行比较，若当前串大于str，则返回一个整数，等于返回0,小于，返回-1
    @Override
    public int compareTo(IString str) {
        //求出当前串与带比较串的长度，并把较小值赋值到n
        int len1 = curLen;
        int len2 = ((SeqString) str).curLen;
        int n = Math.min(len1,len2);

        char[] s1 = strValue;
        char[] s2 = ((SeqString) str).strValue;
        int k = 0;

        while (k<n){
            char ch1 = s1[k];
            char ch2 = s2[k];
            if (ch1 != ch2){
                return ch1 - ch2;//返回不相等字符的数值差
            }
            k++;
        }
        return len1 - len2;
    }


    /*
    * Brute-Force模式匹配算法
    *
    * */
    //返回模式串t在主串中从start开始的第一次匹配位置，匹配失败时返回-1
    public int indexOf_BF(IString t,int start){
        //当主串比模式串长时进行比较
        if (this != null && t!=null && t.length()>0&&this.length()>=t.length()){
            //i表示主串中某个子串的序号
            int sLen,tLen,i = start,j = 0;
            sLen = this.length();
            tLen = t.length();

            while ((i<sLen)&&(j<tLen)){
                if (this.charAt(i)==t.charAt(j)){//如果一切安好,继续比较后面的字符
                    i++;
                    j++;
                }else {
                    i = i-j+1;//继续比较主串中的下一个子串，这边i其实只是加了1，昂，you know，细品
                    j = 0;//模式串下标归0
                }
            }
            if (j>=t.length()) {//此时从循环杀出来的时候，如果j>=t.length,说明匹配成功了
                return i - tLen;//匹配成功，返回子串序号，这里返回的是字符串首个的index
            }else {
                return -1;//匹配失败，返回负一
            }
        }
        return -1;//匹配失败，返回负一
    }


    /*
    * KMP算法
    *
    * */
    private int[] getNextVal(IString T){
        int [] nextVal = new int[T.length()];
        int j = 0;
        int k = -1;
        nextVal[0] = -1;
        while (j<T.length()-1){
            if (k == -1||T.charAt(j) == T.charAt(k)){
                j++;
                k++;
                if (T.charAt(j) != T.charAt(k))
                    nextVal[j] = k;
                else
                    nextVal[j] = nextVal[k];
            }else
                k = nextVal[k];
        }
        return nextVal;
    }

    public int index_KPM(IString T,int start){
        int [] next = getNextVal(T);//计算模式串next[]函数值
        int i = start;//主串指针
        int j = 0;//模式串指针
        //对两串从左到右逐个比较字符
        while (i<this.length()&& j<T.length()){
            if (j == -1||this.charAt(i)==T.charAt(j)){
                i++;
                j++;
            }else {
                j = next[j];//模式串右移
            }
        }
        if (j<T.length()){
            return -1;
        }else {
            return (i-T.length());//匹配成功
        }
    }

    //子串定位操作，搜索与str相等的子串，成功则返回str在当前位置
    @Override
    public int indexOf(IString str, int begin) {
        return index_KPM(str,begin);
    }
}
