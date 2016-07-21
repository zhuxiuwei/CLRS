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

##15.2 Matrix-chain multiplication  
#####15.2-1 Find an optimal parenthesization of a matrix-chain product whose sequence of dimensions is {5, 10, 3, 12, 5, 50, 6}  
Minimum multiply count： 2010, Solution: ((A1A2)((A3A4)(A5A6)))  

#####15.2-2设计递归算法MATRIX-CHAIN-MULTIPLY(A,s,i,j),实现矩阵链最优代价乘法计算的真正计算过程，其输入参数为矩阵序列{A1,A2,...,An},MATRIX-CHAIN-ORDER得到的表s,以及下标i和j.(初始调用应为MATRIX-CHAIN-MULTIPLY(A,s,1,n)).  
代码见[MatrixMultiplyChain之matrixChain_UpBottom()](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/MatrixMultiplyChain.java)  

#####15.2-4 对输入链长度为n的矩阵连乘法问题，描述其子问题图：它包含多少个顶点？包含多少条边？这些分别连接哪些顶点？  
n^2个顶点，n^3条边连接着n^2个顶点。具体形式化的解不做了。  

#####15.3-1 对于矩阵链乘法问题，下面两种确定最优代价的方法哪种更高效？第一种方法是穷举所有可能的括号化方案，对每种方案计算乘法运算次数，第二种方法是运行RECURSIVE-MATRIX-CHAIN。证明你的结论。  
递归更高效。书中p212说了穷举法运行时间是类似卡塔兰数的序列，为Ω(4^n/n^(3/2))。 p220说了递归运行时间是Ω(2^n)。递归算法更高效。

#####15.3-2 对一个16个元素的数组，画出2,.3-1节中MERGE-SORT过程运行的递归调用树。解释备忘技术为什么对MERGE-SORT这种分治算法无效。  
答：见图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/15.3-2.png)  
因为不存在重叠子问题。每一步递归都会产生新问题。所以不适合动态规划，适合分治算法。  

#####15.3-3 考虑矩阵链乘法问题的一个变形，目标改为最大化矩阵序列括号化方案的变量乘法运算次数，而非最小化。此问题具有最优子结构性质吗？  
具有。  
1. 具有和最小化类似的两个子矩阵链最优子结构。  
2. 子问题具有重叠性，而且两个子问题是相互独立的。    

#####15.3-4 如前所述，使用动态规划方法，我们首先求解子问题，然后选择哪些子问题用来构造原问题的最优解。Capulet教授认为，我们不必为了求原问题的最优解而总是求解出所有子问题。她建议，在求矩阵链乘法问题的最优解时，我们总是可以在求解子问题之前选定AiAi+1...Aj的划分位置Ak(选定的k使得pi-1pkpj最小)。请找出一个反例，证明这个贪心方法可能生成次优解。  
反例： {10, 1000, 9, 5, 5}。  
动态规划方法：Minimum multiply count： 90675, Solution: ((A1A2)(A3A4))  
贪心法： Minimum multiply count： 95250, Solution: ((A1(A2A3))A4)  

###-------- 思考题 ----------  
15.1 最大重叠点  