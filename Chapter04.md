第4章习题答案
=
#####4.1-1  
只返回绝对值最小的一个元素

#####4.1-2  
见[MaxSubArray_Violence.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04/MaxSubArray_Violence.java)  

#####4.1-3  
代码见[MaxSubArray_Recursive.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04/MaxSubArray_Recursive.java)  
n0边界值问题，没有考虑明白。不会。  

#####4.1-4  
没做  

#####4.1-5  
代码见[MaxSubArray_Lineary.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04/MaxSubArray_Lineary.java)  

#####4.2-1 用Strassen算法给出矩阵的计算过程。矩阵： [1 3 / 7 5], [6 8 / 4 2]  其中'/'表示换行
步骤1: 矩阵分解为
A: A11=1 A12=3 A21=7 A22=5 
B: B11=6 B12=8 B21=4 B22=2
C: C11=? C12=? C21=? C22=?  
步骤2:通过加减法计算10个常数项的矩阵。  
S1=B12-B22=6, S2=A11+A12=4, S3=A21+A22=12, S4=B21-B11=-2, S5=A11+A22=6  
S6=B11+B22=8, S7=A12-A22=-2, S8=B21+B22=6, S9=A11-A21=-6, S10=B11+B12=14  
步骤3：递归地计算7次n/2*n/2矩阵乘法  
P1=A11*S1=6, P2=S2*B22=8, P3=S3*B11=72,  
P4=A22*S4=-10, P5=S5*S6=48, P6=S7*S8=-12, P7=S9*S10=-84  
步骤四：计算出C各个值  
C11=P5+P4-P2+P6=18, C12=P1+P2=14, C21=P3+P4=62, C22=P5+P1-P3-P7=66  
结果和手算的结果一致。

#####4.2-2 Strassen算法伪代码  
完整能运行的代码：[矩阵乘法的Strassen算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04/MatrixMultipleStrassen.java)  

#####4.2-6 Strassen算法作为子进程计算knxn 和 nxkn矩阵相乘的最短时间，以及nxkn和knxn相乘呢？  
knxn 乘以 nxkn，相当于进行了k^2次 nxn的矩阵乘法，因此时间复杂度是k^2xθ(n^lg7)  
nxkn乘以knxn，相当于运行k次nxn矩阵乘法，再给这k个nxn矩阵加和。乘法时间复杂度是kxθ(n^lg7)，加和时间复杂度是θ(n^2)，总计kxθ(n^lg7) +  θ(n^2)  


#####4.3-1 证明T(n)=T(n-1)+n解为O(n^2)  
代入法要求证明，存在常数c，使得T(n)<=cn^2成立。  
我们假设边界值是1，即对于n>=1归纳法成立。  
将猜测代入递归式，有T(n)<=c(n-1)^2+n=cn^2-2cn+c+n=cn^2+(1-2c)n+c>=cn^2
最后一个不等式在c>=1时都成立。下面证明边际值。  
n=1时，T(1)=T(0)+1=1<=c1^2=c，成立。证毕。  

#####4.3-2 证明T(n)=T(⌈n/2⌉ )+1 解为 O(lgn)
假设边界值为n>0都成立。  
将猜测代入，有T(n)<=T(n/2)+1<=clg(n/2)+1=clg(n)-clg(2)+1=clng(n)+1-c<=clg(n)  
对于c>1，最后一步都成立。下面证明边界值  
n=1时，T(1)<=lg1=0,与t(1)=1矛盾。适当扩大边界到n=2.则T(2)=2，T(2)<=clg2=c，只要c>=2都成立。证毕。  

#####4.4-1 对递归式T(n)=3T(⌊n/2⌋)+n，利用递归式确定一个好的上界，并用代入法验证。  
层数：lgn+1  
从根节点（第1层）到倒数第二层（第lgn层），第k层每层代价：(3/2)^(k-1)*n  
叶子节点，有3^lgn=n^lg3个节点。  
故总代价为：  
n*(1-3/2^lgn)/(1-3/2) + Θ(n^lg3)  
=2n*(3/2^lgn - 1) + Θ(n^lg3)， 引入一些不精确，  
<2n*2^lgn + cn^lg4  
=2n^2 + cn^2 = c*n^2
故猜测T(n)=O(n^2)  
下面用 代入法验证  
T(n)<=d*n^2，其中d是一个适当的正常数。代入递归式，有：  
T(n)<=3d*(n/2)^2+n  
=3/4d x n^2 + n < 3/4d x n^2 + n^2 =(1+3/4)d x n^2 <= d x n^2  
只要d>=4即可满足。  



