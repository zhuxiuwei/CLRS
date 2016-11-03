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

#####22.1-3 The **transpose** of a directed graph G = (V, E) is the graph G**T** = (V, E**T**), where E**T** = {(v, u) in V × V : (u, v) in E}. Thus, G**T** is G with all its edges reversed. Describe efficient algorithms for computing G**T** from G, for both the adjacency-list and adjacency-matrix representations of G. Analyze the running times of your algorithms.  
**T** means transpose.  
链接表：准备一个新的表，然后遍历老的表所有顶点。遍历旧表的Adj[a]时，每遇到一个 vertex b，就往新表的Adj[b]链表里对应插入a。 O(V + E)  
链接矩阵：直接从上到下，从左到右地翻转矩阵。 O(V^2)  

#####22.1-4 Given an adjacency-list representation of a multigraph G = (V, E), describe an O(V + E)-time algorithm to compute the adjacency-list representation of the "equivalent" undirected graph G′ = (V, E′), where E′ consists of the edges in E with all multiple edges between two vertices replaced by a single edge and with all self-loops removed.  
1. 准备一个新表。  
2. 遍历旧表的每个顶点a的Adj[a]。  
3. 每遍历到一个顶点b，往Adj[b]的尾部插入a，如果a!=b且a不在Adj[b]里出现过。  

#####22.1-5 有向图G = (V, E)的**平方图**是图G2 = (V, E2)，该图满足下列条件：(u, w)属于E2当且仅当v属于V，有(u, v)属于E，且(v, w)属于E。亦即，如果图G中顶点u和w之间存在着一条恰包含两条边的路径时，则G2必包含该边(u, w)。针对图G邻接表和邻接矩阵两种表示，分别写出根据G产生G2的有效算法，并分析所给出算法的运行时间。  
见[代码](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_1_5_SquareGraph.java)  

#####22.1-6 如果我们用邻接矩阵来存储图，那么绝大多数图算法的运行时间都是Ω(|V|^2)（V为一个图的顶点集），但还是有些例外。比如，给定一个有向图Ｇ的邻接矩阵Ａ，我们可以在Ο(|V|)时间内判断图G是否包含一个**通用汇点(universal sink)**，即一个入度为|V|-1出度为0的顶点。请给出这样的算法。  
**O(V)思路不好想**：从Matrix[1,1]开始,如果当前位置是0,则往右走一步;如果是1,则往下走一步;一直走到最后一行或者最后一列再停下.再检查该位置是否满足universal sink的定义.  
代码见：[Prac22_1_6_UniversalSink.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_1_6_UniversalSink.java)  

#####22.1-7 The incidence matrix of a directed graph G = (V, E) is a |V| × |E| matrix B = (bij) such that bij=-1 if edge j leaves vertex i， bij=1 if edge j enters vertex i, bij=0 otherwise. Describe what the entries of the matrix product BBT represent, where BT is the transpose of B.  
以图{2<-1, 3<-1}为例。 B为：{1,1}{-1,0}{0,-1}, BT为{1,-1,0}{1,0,-1}, BxBT为{2,-1,-1}{-1,1,0}{-1,0,1}。则BBT[i,j]矩阵的含义为：  
* i=j时，表示Vertex i的degree；  
* i!=j时，表示顶点i和顶点j之间边的数量的负数。  

#####22.1-8 Suppose that instead of a linked list, each array entry Adj[u] is a hash table containing the vertices v for which (u, v) in E. If all edge lookups are equally likely, what is the expected time to determine whether an edge is in the graph? What disadvantages does this scheme have? Suggest an alternate data structure for each edge list that solves these problems. Does your alternative have disadvantages compared to the hash table?  
我习题[22.1-5的代码](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_1_5_SquareGraph.java#L43)就是用的HashTable.  
1. 时间是O(1)。  
2. 缺点是HashTable更占空间？以及需要需要解决散列冲突问题？  
3. 用搜索树替代，缺点是搜索时间效率降低。  

##22.2  广度优先搜索(BFS)  
#####22.2-1 Show the d and π values that result from running breadth-first search on the directed graph of Figure 22.2(a), using vertex 3 as the source.  
Vertex: 1, d:∞, π:bull  
Vertex: 2, d:3, π:4  
Vertex: 3, d:0, π:bull  
Vertex: 4, d:2, π:5  
Vertex: 5, d:1, π:3  
Vertex: 6, d:1, π:3  
See [Code](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/BFS.java#L20)  

#####22.2-2 Show the d and π values that result from running breadth-first search on the undirected graph of Figure 22.3, using vertex u as the source.  
Vertex: r, d:1, π:s  
Vertex: s, d:0, π: null  
Vertex: t, d:2, π:w  
Vertex: u, d:3, π:t  
Vertex: v, d:2, π:r  
Vertex: w, d:1, π:s  
Vertex: x, d:2, π:w  
Vertex: y, d:3, π:x  
See [Code](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/BFS.java#L40)  

#####22.2-4 What is the running time of BFS if its input graph is represented by an adjacency matrix and the algorithm is modified to handle this form of input?  
从邻接表的O(V+E)变成O(v + v^2)，因为扫描所有邻居的时间从O(E)变成O(V^2)。  

#####22.2-5 Argue that in a breadth-first search, the value d[u] assigned to a vertex u is independent of the order in which the vertices in each adjacency list are given. Using Figure 22.3 as an example, show that the breadth-first tree computed by BFS can depend on the ordering within adjacency lists.  
See [Code](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/BFS.java#L62)  
The order in adjacency list is different from [practice 22.2-2](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/BFS.java#L39), but the d in output are the same. π can be changed.  
Output:  
Vertex: r, d:1, π:s  
Vertex: s, d:0, π: null  
Vertex: t, d:2, π:w  
Vertex: u, d:3, π:x <- π here is different from 22.2-2 output, but d still remains the same.  
Vertex: v, d:2, π:r  
Vertex: w, d:1, π:s  
Vertex: x, d:2, π:w  
Vertex: y, d:3, π:x  

#####22.2-7 There are two types of professional wrestlers: "good guys" and "bad guys." Between any pair of professional wrestlers, there may or may not be a rivalry. Suppose we have n professional wrestlers and we have a list of r pairs of wrestlers for which there are rivalries. Give an O(n + r)-time algorithm that determines whether it is possible to designate some of the wrestlers as good guys and the remainder as bad guys such that each rivalry is between a good guy and a bad guy. If is it possible to perform such a designation, your algorithm should produce it.  
代码见[Prac22_2_7_WrestlersPartition摔跤手分组](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_2_7_WrestlersPartition.java)，有一个注意点。  

#####22.2-8 The diameter of a tree T =(V, E) is given by max d(u,v), that is, the diameter is the largest of all shortest-path distances in the tree. Give an efficient algorithm to compute the diameter of a tree, and analyze the running time of your algorithm.  
See [Code](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/BFS.java#L85). O(V x (V + E))  

##22.3  深度优先搜索(DFS)  


