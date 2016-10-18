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

### 22.1-3 
