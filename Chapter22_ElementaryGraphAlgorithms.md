第22章 图基本算法
=
##22.1 图的表示  
#####22.1-1 Given an adjacency-list representation of a directed graph, how long does it take to compute the out-degree of every vertex? How long does it take to compute the in-degrees?  
G=(V,E)。  
out-degree:O(V+E)  
in-degree:O(V+E)  

#####22.1-2 Give an adjacency-list representation for a complete binary tree on 7 vertices. Give an equivalent adjacency-matrix representation. Assume that vertices are numbered from 1 to 7 as in a binary heap.  
 | 1 | 2 | 3 | 4 | 5 | 6 | 7
:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:
1 | 0 | 1 | 1 | 0 | 0 | 0 | 0
2 | 1 | 0 | 0 | 1 | 1 | 0 | 0
3 | 1 | 0 | 0 | 0 | 0 | 1 | 1
4 | 0 | 1 | 0 | 0 | 0 | 0 | 0 
5 | 0 | 1 | 0 | 0 | 0 | 0 | 0
6 | 0 | 0 | 1 | 0 | 0 | 0 | 0
7 | 0 | 0 | 1 | 0 | 0 | 0 | 0

### 22.1-3 The **transpose** of a directed graph G = (V, E) is the graph G**T** = (V, E**T**), where E**T** = {(v, u) in V × V : (u, v) in E}. Thus, G**T** is G with all its edges reversed. Describe efficient algorithms for computing G**T** from G, for both the adjacency-list and adjacency-matrix representations of G. Analyze the running times of your algorithms.  
**T** means transpose.  
链接表：准备一个新的表，然后遍历老的表所有顶点。遍历旧表的Adj[a]时，每遇到一个 vertex b，就往新表的Adj[b]链表里对应插入a。 O(V + E)  
链接矩阵：直接从上到下，从左到右地翻转矩阵。 O(V^2)  

### 22.1-4 Given an adjacency-list representation of a multigraph G = (V, E), describe an O(V + E)-time algorithm to compute the adjacency-list representation of the "equivalent" undirected graph G′ = (V, E′), where E′ consists of the edges in E with all multiple edges between two vertices replaced by a single edge and with all self-loops removed.  
1. 准备一个新表。  
2. 遍历旧表的每个顶点a的Adj[a]。  
3. 每遍历到一个顶点b，往Adj[b]的尾部插入a，如果a!=b且a不在Adj[b]里出现过。  

### 22.1-5 有向图G = (V, E)的**平方图**是图G2 = (V, E2)，该图满足下列条件：(u, w)属于E2当且仅当v属于V，有(u, v)属于E，且(v, w)属于E。亦即，如果图G中顶点u和w之间存在着一条恰包含两条边的路径时，则G2必包含该边(u, w)。针对图G邻接表和邻接矩阵两种表示，分别写出根据G产生G2的有效算法，并分析所给出算法的运行时间。  
见[代码](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_1_5_SquareGraph.java)  

### 22.1-6 如果我们用邻接矩阵来存储图，那么绝大多数图算法的运行时间都是Ω(|V|^2)（V为一个图的顶点集），但还是有些例外。比如，给定一个有向图Ｇ的邻接矩阵Ａ，我们可以在Ο(|V|)时间内判断图G是否包含一个**通用汇点(universal sink)**，即一个入度为|V|-1出度为0的顶点。请给出这样的算法。  
**O(V)思路不好想**：从Matrix[1,1]开始,如果当前位置是0,则往右走一步;如果是1,则往下走一步;一直走到最后一行或者最后一列再停下.再检查该位置是否满足universal sink的定义.  
代码见：[Prac22_1_6_UniversalSink.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_1_6_UniversalSink.java)  

