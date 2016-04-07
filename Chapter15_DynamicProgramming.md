第15章习题答案
=
##15.1 Rod Cutting  
#####15.1-2 举反例证明按照单价密度的贪心策略不能保证得到最优方案。  
反例：  
长度 1 2 3 4  
价格 1 1 18 20  
密度 1 0.5 6 5  
求4的切割方案，按照最大密度的解，是3 + 1，价格为19。不如直接取4的解20。  

#####15.1-3 每次切割还要付出成本的钢条切割动态规划方案。  
代码见[CutRodWithCost](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/CutRodWithCost.java)  

#####15.1-4 Modify MEMOIZED-CUT-ROD to return not only the value but the actual solution too.  
代码见[CutRodMemorizedExtended](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/CutRodMemorizedExtended.java)  

#####15.1-5 O(n)time dynamic-programming algorithm to compute the nth Fibonacci number. Draw the subproblem graph. How many vertices and edges are in the graph?  
代码见[FibonacciDynamicProgramming](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/FibonacciDynamicProgramming.java)  
subproblem graph:  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/15.1-5.png)  
vertices: n+1  
edges: n=0,0; else 2(n-1)  

###-------- 思考题 ----------  
15.1 最大重叠点  