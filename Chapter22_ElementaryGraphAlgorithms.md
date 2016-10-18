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

### 22.1-5 The **square** of a directed graph G=(V,E) is the graph G2 =(V,E2) such that (u,w) in E2 if and only if for some v in V, both(u,v) in E and(v,w) in E.That is,G2 contains an edge between u and w whenever G contains a path with exactly two edges between u and w. Describe efficient algorithms for computing G2 from G for both the adjacency-list and adjacency-matrix representations of G. Analyze the running times of your algorithms.  




