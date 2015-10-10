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

#####4.2-1 用Strassen算法给出矩阵的计算过程。矩阵： [1 3 | 7 5], [6 8 | 4 2]('|'表示换行)
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

#####4.2-2 Strassen伪代码  
代码见[MatrixMultipleStranssen.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04/MatrixMultipleStranssen.java)  
