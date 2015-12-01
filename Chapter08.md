第8章习题答案
=
#####8.1-1 比较排序算法的**决策树**，叶节点可能的最小深度是？  
n-1  

#####8.2-1 记数排序在A=⟨6,0,2,0,1,3,4,6,1,3,2⟩的过程  
操作过程见下图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/8.2-1.jpg)  

#####8.2-2 证明记数排序是稳定的。  
以8.2-1为例，任何出现两次的数，都是先把在A中靠后的数，放在B中也相对靠后的位置。  
例如B数组的第一次赋值2，是把A[11]赋值给B[6]。后来，在第(i)次赋值的2的时候，是把A[3]赋值给B[5]，**两次的2，在A、B中相对位置不变**。  

#####8.2-3 把第10行循环改成* for j = 1 to A.length *，算法依然正确，但是还稳定吗？  
不稳定。举一个反例，依然用8.2-1的例子。第一次赋值，A[1]=6, c[6]=11，故把B[11]赋值成A[1]=6。  
而对于6这个出现2次数，如果想保持稳定，**应该把A[8]=6赋值给B[11]，A[1]=6赋值给B[10]**。  
依据以上反例，不稳定.  

#####8.2-4 Describe an algorithm that, given n integers in the range 0 to k, preprocesses its input and then answers any query 
about how many of the n integers fall into a range [a..b] in O(1) time. Your algorithm should use Θ(n+k) preprocessing time.  
代码见[CountNumberInRange](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap08/CountNumberInRange.java)。  

###-------- 思考题 ----------  
